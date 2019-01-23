package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.Floraison;
import com.olympp.backend.domain.Plante;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.repository.FloraisonRepository;
import com.olympp.backend.service.FloraisonService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;
import com.olympp.backend.service.dto.FloraisonCriteria;
import com.olympp.backend.service.FloraisonQueryService;

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
 * Test class for the FloraisonResource REST controller.
 *
 * @see FloraisonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class FloraisonResourceIntTest {

    @Autowired
    private FloraisonRepository floraisonRepository;

    @Autowired
    private FloraisonService floraisonService;

    @Autowired
    private FloraisonQueryService floraisonQueryService;

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

    private MockMvc restFloraisonMockMvc;

    private Floraison floraison;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FloraisonResource floraisonResource = new FloraisonResource(floraisonService, floraisonQueryService);
        this.restFloraisonMockMvc = MockMvcBuilders.standaloneSetup(floraisonResource)
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
    public static Floraison createEntity(EntityManager em) {
        Floraison floraison = new Floraison();
        return floraison;
    }

    @Before
    public void initTest() {
        floraison = createEntity(em);
    }

    @Test
    @Transactional
    public void createFloraison() throws Exception {
        int databaseSizeBeforeCreate = floraisonRepository.findAll().size();

        // Create the Floraison
        restFloraisonMockMvc.perform(post("/api/floraisons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(floraison)))
            .andExpect(status().isCreated());

        // Validate the Floraison in the database
        List<Floraison> floraisonList = floraisonRepository.findAll();
        assertThat(floraisonList).hasSize(databaseSizeBeforeCreate + 1);
        Floraison testFloraison = floraisonList.get(floraisonList.size() - 1);
    }

    @Test
    @Transactional
    public void createFloraisonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = floraisonRepository.findAll().size();

        // Create the Floraison with an existing ID
        floraison.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFloraisonMockMvc.perform(post("/api/floraisons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(floraison)))
            .andExpect(status().isBadRequest());

        // Validate the Floraison in the database
        List<Floraison> floraisonList = floraisonRepository.findAll();
        assertThat(floraisonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFloraisons() throws Exception {
        // Initialize the database
        floraisonRepository.saveAndFlush(floraison);

        // Get all the floraisonList
        restFloraisonMockMvc.perform(get("/api/floraisons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(floraison.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getFloraison() throws Exception {
        // Initialize the database
        floraisonRepository.saveAndFlush(floraison);

        // Get the floraison
        restFloraisonMockMvc.perform(get("/api/floraisons/{id}", floraison.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(floraison.getId().intValue()));
    }

    @Test
    @Transactional
    public void getAllFloraisonsByPlanteIsEqualToSomething() throws Exception {
        // Initialize the database
        Plante plante = PlanteResourceIntTest.createEntity(em);
        em.persist(plante);
        em.flush();
        floraison.setPlante(plante);
        floraisonRepository.saveAndFlush(floraison);
        Long planteId = plante.getId();

        // Get all the floraisonList where plante equals to planteId
        defaultFloraisonShouldBeFound("planteId.equals=" + planteId);

        // Get all the floraisonList where plante equals to planteId + 1
        defaultFloraisonShouldNotBeFound("planteId.equals=" + (planteId + 1));
    }


    @Test
    @Transactional
    public void getAllFloraisonsByMoisIsEqualToSomething() throws Exception {
        // Initialize the database
        Mois mois = MoisResourceIntTest.createEntity(em);
        em.persist(mois);
        em.flush();
        floraison.setMois(mois);
        floraisonRepository.saveAndFlush(floraison);
        Long moisId = mois.getId();

        // Get all the floraisonList where mois equals to moisId
        defaultFloraisonShouldBeFound("moisId.equals=" + moisId);

        // Get all the floraisonList where mois equals to moisId + 1
        defaultFloraisonShouldNotBeFound("moisId.equals=" + (moisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFloraisonShouldBeFound(String filter) throws Exception {
        restFloraisonMockMvc.perform(get("/api/floraisons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(floraison.getId().intValue())));

        // Check, that the count call also returns 1
        restFloraisonMockMvc.perform(get("/api/floraisons/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFloraisonShouldNotBeFound(String filter) throws Exception {
        restFloraisonMockMvc.perform(get("/api/floraisons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFloraisonMockMvc.perform(get("/api/floraisons/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFloraison() throws Exception {
        // Get the floraison
        restFloraisonMockMvc.perform(get("/api/floraisons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFloraison() throws Exception {
        // Initialize the database
        floraisonService.save(floraison);

        int databaseSizeBeforeUpdate = floraisonRepository.findAll().size();

        // Update the floraison
        Floraison updatedFloraison = floraisonRepository.findById(floraison.getId()).get();
        // Disconnect from session so that the updates on updatedFloraison are not directly saved in db
        em.detach(updatedFloraison);

        restFloraisonMockMvc.perform(put("/api/floraisons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFloraison)))
            .andExpect(status().isOk());

        // Validate the Floraison in the database
        List<Floraison> floraisonList = floraisonRepository.findAll();
        assertThat(floraisonList).hasSize(databaseSizeBeforeUpdate);
        Floraison testFloraison = floraisonList.get(floraisonList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFloraison() throws Exception {
        int databaseSizeBeforeUpdate = floraisonRepository.findAll().size();

        // Create the Floraison

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFloraisonMockMvc.perform(put("/api/floraisons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(floraison)))
            .andExpect(status().isBadRequest());

        // Validate the Floraison in the database
        List<Floraison> floraisonList = floraisonRepository.findAll();
        assertThat(floraisonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFloraison() throws Exception {
        // Initialize the database
        floraisonService.save(floraison);

        int databaseSizeBeforeDelete = floraisonRepository.findAll().size();

        // Get the floraison
        restFloraisonMockMvc.perform(delete("/api/floraisons/{id}", floraison.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Floraison> floraisonList = floraisonRepository.findAll();
        assertThat(floraisonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Floraison.class);
        Floraison floraison1 = new Floraison();
        floraison1.setId(1L);
        Floraison floraison2 = new Floraison();
        floraison2.setId(floraison1.getId());
        assertThat(floraison1).isEqualTo(floraison2);
        floraison2.setId(2L);
        assertThat(floraison1).isNotEqualTo(floraison2);
        floraison1.setId(null);
        assertThat(floraison1).isNotEqualTo(floraison2);
    }
}
