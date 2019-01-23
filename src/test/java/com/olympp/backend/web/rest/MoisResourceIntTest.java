package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.Mois;
import com.olympp.backend.domain.Recolte;
import com.olympp.backend.domain.Floraison;
import com.olympp.backend.repository.MoisRepository;
import com.olympp.backend.service.MoisService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;
import com.olympp.backend.service.dto.MoisCriteria;
import com.olympp.backend.service.MoisQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.olympp.backend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MoisResource REST controller.
 *
 * @see MoisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class MoisResourceIntTest {

    private static final String DEFAULT_MOIS = "AAAAAAAAAA";
    private static final String UPDATED_MOIS = "BBBBBBBBBB";

    @Autowired
    private MoisRepository moisRepository;

    @Autowired
    private MoisService moisService;

    @Autowired
    private MoisQueryService moisQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMoisMockMvc;

    private Mois mois;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MoisResource moisResource = new MoisResource(moisService, moisQueryService);
        this.restMoisMockMvc = MockMvcBuilders.standaloneSetup(moisResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mois createEntity(EntityManager em) {
        Mois mois = new Mois()
            .mois(DEFAULT_MOIS);
        return mois;
    }

    @Before
    public void initTest() {
        mois = createEntity(em);
    }

    @Test
    @Transactional
    public void createMois() throws Exception {
        int databaseSizeBeforeCreate = moisRepository.findAll().size();

        // Create the Mois
        restMoisMockMvc.perform(post("/api/mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mois)))
            .andExpect(status().isCreated());

        // Validate the Mois in the database
        List<Mois> moisList = moisRepository.findAll();
        assertThat(moisList).hasSize(databaseSizeBeforeCreate + 1);
        Mois testMois = moisList.get(moisList.size() - 1);
        assertThat(testMois.getMois()).isEqualTo(DEFAULT_MOIS);
    }

    @Test
    @Transactional
    public void createMoisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moisRepository.findAll().size();

        // Create the Mois with an existing ID
        mois.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMoisMockMvc.perform(post("/api/mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mois)))
            .andExpect(status().isBadRequest());

        // Validate the Mois in the database
        List<Mois> moisList = moisRepository.findAll();
        assertThat(moisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMois() throws Exception {
        // Initialize the database
        moisRepository.saveAndFlush(mois);

        // Get all the moisList
        restMoisMockMvc.perform(get("/api/mois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mois.getId().intValue())))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS.toString())));
    }
    
    @Test
    @Transactional
    public void getMois() throws Exception {
        // Initialize the database
        moisRepository.saveAndFlush(mois);

        // Get the mois
        restMoisMockMvc.perform(get("/api/mois/{id}", mois.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mois.getId().intValue()))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS.toString()));
    }

    @Test
    @Transactional
    public void getAllMoisByMoisIsEqualToSomething() throws Exception {
        // Initialize the database
        moisRepository.saveAndFlush(mois);

        // Get all the moisList where mois equals to DEFAULT_MOIS
        defaultMoisShouldBeFound("mois.equals=" + DEFAULT_MOIS);

        // Get all the moisList where mois equals to UPDATED_MOIS
        defaultMoisShouldNotBeFound("mois.equals=" + UPDATED_MOIS);
    }

    @Test
    @Transactional
    public void getAllMoisByMoisIsInShouldWork() throws Exception {
        // Initialize the database
        moisRepository.saveAndFlush(mois);

        // Get all the moisList where mois in DEFAULT_MOIS or UPDATED_MOIS
        defaultMoisShouldBeFound("mois.in=" + DEFAULT_MOIS + "," + UPDATED_MOIS);

        // Get all the moisList where mois equals to UPDATED_MOIS
        defaultMoisShouldNotBeFound("mois.in=" + UPDATED_MOIS);
    }

    @Test
    @Transactional
    public void getAllMoisByMoisIsNullOrNotNull() throws Exception {
        // Initialize the database
        moisRepository.saveAndFlush(mois);

        // Get all the moisList where mois is not null
        defaultMoisShouldBeFound("mois.specified=true");

        // Get all the moisList where mois is null
        defaultMoisShouldNotBeFound("mois.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoisByRecolteIsEqualToSomething() throws Exception {
        // Initialize the database
        Recolte recolte = RecolteResourceIntTest.createEntity(em);
        em.persist(recolte);
        em.flush();
        mois.addRecolte(recolte);
        moisRepository.saveAndFlush(mois);
        Long recolteId = recolte.getId();

        // Get all the moisList where recolte equals to recolteId
        defaultMoisShouldBeFound("recolteId.equals=" + recolteId);

        // Get all the moisList where recolte equals to recolteId + 1
        defaultMoisShouldNotBeFound("recolteId.equals=" + (recolteId + 1));
    }


    @Test
    @Transactional
    public void getAllMoisByFloraisonIsEqualToSomething() throws Exception {
        // Initialize the database
        Floraison floraison = FloraisonResourceIntTest.createEntity(em);
        em.persist(floraison);
        em.flush();
        mois.addFloraison(floraison);
        moisRepository.saveAndFlush(mois);
        Long floraisonId = floraison.getId();

        // Get all the moisList where floraison equals to floraisonId
        defaultMoisShouldBeFound("floraisonId.equals=" + floraisonId);

        // Get all the moisList where floraison equals to floraisonId + 1
        defaultMoisShouldNotBeFound("floraisonId.equals=" + (floraisonId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMoisShouldBeFound(String filter) throws Exception {
        restMoisMockMvc.perform(get("/api/mois?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mois.getId().intValue())))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS.toString())));

        // Check, that the count call also returns 1
        restMoisMockMvc.perform(get("/api/mois/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMoisShouldNotBeFound(String filter) throws Exception {
        restMoisMockMvc.perform(get("/api/mois?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMoisMockMvc.perform(get("/api/mois/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMois() throws Exception {
        // Get the mois
        restMoisMockMvc.perform(get("/api/mois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMois() throws Exception {
        // Initialize the database
        moisService.save(mois);

        int databaseSizeBeforeUpdate = moisRepository.findAll().size();

        // Update the mois
        Mois updatedMois = moisRepository.findById(mois.getId()).get();
        // Disconnect from session so that the updates on updatedMois are not directly saved in db
        em.detach(updatedMois);
        updatedMois
            .mois(UPDATED_MOIS);

        restMoisMockMvc.perform(put("/api/mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMois)))
            .andExpect(status().isOk());

        // Validate the Mois in the database
        List<Mois> moisList = moisRepository.findAll();
        assertThat(moisList).hasSize(databaseSizeBeforeUpdate);
        Mois testMois = moisList.get(moisList.size() - 1);
        assertThat(testMois.getMois()).isEqualTo(UPDATED_MOIS);
    }

    @Test
    @Transactional
    public void updateNonExistingMois() throws Exception {
        int databaseSizeBeforeUpdate = moisRepository.findAll().size();

        // Create the Mois

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMoisMockMvc.perform(put("/api/mois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mois)))
            .andExpect(status().isBadRequest());

        // Validate the Mois in the database
        List<Mois> moisList = moisRepository.findAll();
        assertThat(moisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMois() throws Exception {
        // Initialize the database
        moisService.save(mois);

        int databaseSizeBeforeDelete = moisRepository.findAll().size();

        // Get the mois
        restMoisMockMvc.perform(delete("/api/mois/{id}", mois.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mois> moisList = moisRepository.findAll();
        assertThat(moisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mois.class);
        Mois mois1 = new Mois();
        mois1.setId(1L);
        Mois mois2 = new Mois();
        mois2.setId(mois1.getId());
        assertThat(mois1).isEqualTo(mois2);
        mois2.setId(2L);
        assertThat(mois1).isNotEqualTo(mois2);
        mois1.setId(null);
        assertThat(mois1).isNotEqualTo(mois2);
    }
}
