package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.Ensoleillement;
import com.olympp.backend.repository.EnsoleillementRepository;
import com.olympp.backend.service.EnsoleillementService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;
import com.olympp.backend.service.dto.EnsoleillementCriteria;
import com.olympp.backend.service.EnsoleillementQueryService;

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
 * Test class for the EnsoleillementResource REST controller.
 *
 * @see EnsoleillementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class EnsoleillementResourceIntTest {

    private static final String DEFAULT_ENSOLEILLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENSOLEILLEMENT = "BBBBBBBBBB";

    @Autowired
    private EnsoleillementRepository ensoleillementRepository;

    @Autowired
    private EnsoleillementService ensoleillementService;

    @Autowired
    private EnsoleillementQueryService ensoleillementQueryService;

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

    private MockMvc restEnsoleillementMockMvc;

    private Ensoleillement ensoleillement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnsoleillementResource ensoleillementResource = new EnsoleillementResource(ensoleillementService, ensoleillementQueryService);
        this.restEnsoleillementMockMvc = MockMvcBuilders.standaloneSetup(ensoleillementResource)
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
    public static Ensoleillement createEntity(EntityManager em) {
        Ensoleillement ensoleillement = new Ensoleillement()
            .ensoleillement(DEFAULT_ENSOLEILLEMENT);
        return ensoleillement;
    }

    @Before
    public void initTest() {
        ensoleillement = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnsoleillement() throws Exception {
        int databaseSizeBeforeCreate = ensoleillementRepository.findAll().size();

        // Create the Ensoleillement
        restEnsoleillementMockMvc.perform(post("/api/ensoleillements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensoleillement)))
            .andExpect(status().isCreated());

        // Validate the Ensoleillement in the database
        List<Ensoleillement> ensoleillementList = ensoleillementRepository.findAll();
        assertThat(ensoleillementList).hasSize(databaseSizeBeforeCreate + 1);
        Ensoleillement testEnsoleillement = ensoleillementList.get(ensoleillementList.size() - 1);
        assertThat(testEnsoleillement.getEnsoleillement()).isEqualTo(DEFAULT_ENSOLEILLEMENT);
    }

    @Test
    @Transactional
    public void createEnsoleillementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ensoleillementRepository.findAll().size();

        // Create the Ensoleillement with an existing ID
        ensoleillement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnsoleillementMockMvc.perform(post("/api/ensoleillements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensoleillement)))
            .andExpect(status().isBadRequest());

        // Validate the Ensoleillement in the database
        List<Ensoleillement> ensoleillementList = ensoleillementRepository.findAll();
        assertThat(ensoleillementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEnsoleillements() throws Exception {
        // Initialize the database
        ensoleillementRepository.saveAndFlush(ensoleillement);

        // Get all the ensoleillementList
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ensoleillement.getId().intValue())))
            .andExpect(jsonPath("$.[*].ensoleillement").value(hasItem(DEFAULT_ENSOLEILLEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getEnsoleillement() throws Exception {
        // Initialize the database
        ensoleillementRepository.saveAndFlush(ensoleillement);

        // Get the ensoleillement
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements/{id}", ensoleillement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ensoleillement.getId().intValue()))
            .andExpect(jsonPath("$.ensoleillement").value(DEFAULT_ENSOLEILLEMENT.toString()));
    }

    @Test
    @Transactional
    public void getAllEnsoleillementsByEnsoleillementIsEqualToSomething() throws Exception {
        // Initialize the database
        ensoleillementRepository.saveAndFlush(ensoleillement);

        // Get all the ensoleillementList where ensoleillement equals to DEFAULT_ENSOLEILLEMENT
        defaultEnsoleillementShouldBeFound("ensoleillement.equals=" + DEFAULT_ENSOLEILLEMENT);

        // Get all the ensoleillementList where ensoleillement equals to UPDATED_ENSOLEILLEMENT
        defaultEnsoleillementShouldNotBeFound("ensoleillement.equals=" + UPDATED_ENSOLEILLEMENT);
    }

    @Test
    @Transactional
    public void getAllEnsoleillementsByEnsoleillementIsInShouldWork() throws Exception {
        // Initialize the database
        ensoleillementRepository.saveAndFlush(ensoleillement);

        // Get all the ensoleillementList where ensoleillement in DEFAULT_ENSOLEILLEMENT or UPDATED_ENSOLEILLEMENT
        defaultEnsoleillementShouldBeFound("ensoleillement.in=" + DEFAULT_ENSOLEILLEMENT + "," + UPDATED_ENSOLEILLEMENT);

        // Get all the ensoleillementList where ensoleillement equals to UPDATED_ENSOLEILLEMENT
        defaultEnsoleillementShouldNotBeFound("ensoleillement.in=" + UPDATED_ENSOLEILLEMENT);
    }

    @Test
    @Transactional
    public void getAllEnsoleillementsByEnsoleillementIsNullOrNotNull() throws Exception {
        // Initialize the database
        ensoleillementRepository.saveAndFlush(ensoleillement);

        // Get all the ensoleillementList where ensoleillement is not null
        defaultEnsoleillementShouldBeFound("ensoleillement.specified=true");

        // Get all the ensoleillementList where ensoleillement is null
        defaultEnsoleillementShouldNotBeFound("ensoleillement.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultEnsoleillementShouldBeFound(String filter) throws Exception {
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ensoleillement.getId().intValue())))
            .andExpect(jsonPath("$.[*].ensoleillement").value(hasItem(DEFAULT_ENSOLEILLEMENT.toString())));

        // Check, that the count call also returns 1
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultEnsoleillementShouldNotBeFound(String filter) throws Exception {
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEnsoleillement() throws Exception {
        // Get the ensoleillement
        restEnsoleillementMockMvc.perform(get("/api/ensoleillements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnsoleillement() throws Exception {
        // Initialize the database
        ensoleillementService.save(ensoleillement);

        int databaseSizeBeforeUpdate = ensoleillementRepository.findAll().size();

        // Update the ensoleillement
        Ensoleillement updatedEnsoleillement = ensoleillementRepository.findById(ensoleillement.getId()).get();
        // Disconnect from session so that the updates on updatedEnsoleillement are not directly saved in db
        em.detach(updatedEnsoleillement);
        updatedEnsoleillement
            .ensoleillement(UPDATED_ENSOLEILLEMENT);

        restEnsoleillementMockMvc.perform(put("/api/ensoleillements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnsoleillement)))
            .andExpect(status().isOk());

        // Validate the Ensoleillement in the database
        List<Ensoleillement> ensoleillementList = ensoleillementRepository.findAll();
        assertThat(ensoleillementList).hasSize(databaseSizeBeforeUpdate);
        Ensoleillement testEnsoleillement = ensoleillementList.get(ensoleillementList.size() - 1);
        assertThat(testEnsoleillement.getEnsoleillement()).isEqualTo(UPDATED_ENSOLEILLEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingEnsoleillement() throws Exception {
        int databaseSizeBeforeUpdate = ensoleillementRepository.findAll().size();

        // Create the Ensoleillement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnsoleillementMockMvc.perform(put("/api/ensoleillements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ensoleillement)))
            .andExpect(status().isBadRequest());

        // Validate the Ensoleillement in the database
        List<Ensoleillement> ensoleillementList = ensoleillementRepository.findAll();
        assertThat(ensoleillementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnsoleillement() throws Exception {
        // Initialize the database
        ensoleillementService.save(ensoleillement);

        int databaseSizeBeforeDelete = ensoleillementRepository.findAll().size();

        // Get the ensoleillement
        restEnsoleillementMockMvc.perform(delete("/api/ensoleillements/{id}", ensoleillement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ensoleillement> ensoleillementList = ensoleillementRepository.findAll();
        assertThat(ensoleillementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ensoleillement.class);
        Ensoleillement ensoleillement1 = new Ensoleillement();
        ensoleillement1.setId(1L);
        Ensoleillement ensoleillement2 = new Ensoleillement();
        ensoleillement2.setId(ensoleillement1.getId());
        assertThat(ensoleillement1).isEqualTo(ensoleillement2);
        ensoleillement2.setId(2L);
        assertThat(ensoleillement1).isNotEqualTo(ensoleillement2);
        ensoleillement1.setId(null);
        assertThat(ensoleillement1).isNotEqualTo(ensoleillement2);
    }
}
