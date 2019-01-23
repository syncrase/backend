package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.Recolte;
import com.olympp.backend.domain.Plante;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.repository.RecolteRepository;
import com.olympp.backend.service.RecolteService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;
import com.olympp.backend.service.dto.RecolteCriteria;
import com.olympp.backend.service.RecolteQueryService;

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
 * Test class for the RecolteResource REST controller.
 *
 * @see RecolteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class RecolteResourceIntTest {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private RecolteService recolteService;

    @Autowired
    private RecolteQueryService recolteQueryService;

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

    private MockMvc restRecolteMockMvc;

    private Recolte recolte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecolteResource recolteResource = new RecolteResource(recolteService, recolteQueryService);
        this.restRecolteMockMvc = MockMvcBuilders.standaloneSetup(recolteResource)
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
    public static Recolte createEntity(EntityManager em) {
        Recolte recolte = new Recolte();
        return recolte;
    }

    @Before
    public void initTest() {
        recolte = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecolte() throws Exception {
        int databaseSizeBeforeCreate = recolteRepository.findAll().size();

        // Create the Recolte
        restRecolteMockMvc.perform(post("/api/recoltes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recolte)))
            .andExpect(status().isCreated());

        // Validate the Recolte in the database
        List<Recolte> recolteList = recolteRepository.findAll();
        assertThat(recolteList).hasSize(databaseSizeBeforeCreate + 1);
        Recolte testRecolte = recolteList.get(recolteList.size() - 1);
    }

    @Test
    @Transactional
    public void createRecolteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recolteRepository.findAll().size();

        // Create the Recolte with an existing ID
        recolte.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecolteMockMvc.perform(post("/api/recoltes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recolte)))
            .andExpect(status().isBadRequest());

        // Validate the Recolte in the database
        List<Recolte> recolteList = recolteRepository.findAll();
        assertThat(recolteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRecoltes() throws Exception {
        // Initialize the database
        recolteRepository.saveAndFlush(recolte);

        // Get all the recolteList
        restRecolteMockMvc.perform(get("/api/recoltes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recolte.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getRecolte() throws Exception {
        // Initialize the database
        recolteRepository.saveAndFlush(recolte);

        // Get the recolte
        restRecolteMockMvc.perform(get("/api/recoltes/{id}", recolte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recolte.getId().intValue()));
    }

    @Test
    @Transactional
    public void getAllRecoltesByPlanteIsEqualToSomething() throws Exception {
        // Initialize the database
        Plante plante = PlanteResourceIntTest.createEntity(em);
        em.persist(plante);
        em.flush();
        recolte.setPlante(plante);
        recolteRepository.saveAndFlush(recolte);
        Long planteId = plante.getId();

        // Get all the recolteList where plante equals to planteId
        defaultRecolteShouldBeFound("planteId.equals=" + planteId);

        // Get all the recolteList where plante equals to planteId + 1
        defaultRecolteShouldNotBeFound("planteId.equals=" + (planteId + 1));
    }


    @Test
    @Transactional
    public void getAllRecoltesByMoisIsEqualToSomething() throws Exception {
        // Initialize the database
        Mois mois = MoisResourceIntTest.createEntity(em);
        em.persist(mois);
        em.flush();
        recolte.setMois(mois);
        recolteRepository.saveAndFlush(recolte);
        Long moisId = mois.getId();

        // Get all the recolteList where mois equals to moisId
        defaultRecolteShouldBeFound("moisId.equals=" + moisId);

        // Get all the recolteList where mois equals to moisId + 1
        defaultRecolteShouldNotBeFound("moisId.equals=" + (moisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRecolteShouldBeFound(String filter) throws Exception {
        restRecolteMockMvc.perform(get("/api/recoltes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recolte.getId().intValue())));

        // Check, that the count call also returns 1
        restRecolteMockMvc.perform(get("/api/recoltes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRecolteShouldNotBeFound(String filter) throws Exception {
        restRecolteMockMvc.perform(get("/api/recoltes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRecolteMockMvc.perform(get("/api/recoltes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRecolte() throws Exception {
        // Get the recolte
        restRecolteMockMvc.perform(get("/api/recoltes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecolte() throws Exception {
        // Initialize the database
        recolteService.save(recolte);

        int databaseSizeBeforeUpdate = recolteRepository.findAll().size();

        // Update the recolte
        Recolte updatedRecolte = recolteRepository.findById(recolte.getId()).get();
        // Disconnect from session so that the updates on updatedRecolte are not directly saved in db
        em.detach(updatedRecolte);

        restRecolteMockMvc.perform(put("/api/recoltes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecolte)))
            .andExpect(status().isOk());

        // Validate the Recolte in the database
        List<Recolte> recolteList = recolteRepository.findAll();
        assertThat(recolteList).hasSize(databaseSizeBeforeUpdate);
        Recolte testRecolte = recolteList.get(recolteList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingRecolte() throws Exception {
        int databaseSizeBeforeUpdate = recolteRepository.findAll().size();

        // Create the Recolte

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecolteMockMvc.perform(put("/api/recoltes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recolte)))
            .andExpect(status().isBadRequest());

        // Validate the Recolte in the database
        List<Recolte> recolteList = recolteRepository.findAll();
        assertThat(recolteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecolte() throws Exception {
        // Initialize the database
        recolteService.save(recolte);

        int databaseSizeBeforeDelete = recolteRepository.findAll().size();

        // Get the recolte
        restRecolteMockMvc.perform(delete("/api/recoltes/{id}", recolte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recolte> recolteList = recolteRepository.findAll();
        assertThat(recolteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recolte.class);
        Recolte recolte1 = new Recolte();
        recolte1.setId(1L);
        Recolte recolte2 = new Recolte();
        recolte2.setId(recolte1.getId());
        assertThat(recolte1).isEqualTo(recolte2);
        recolte2.setId(2L);
        assertThat(recolte1).isNotEqualTo(recolte2);
        recolte1.setId(null);
        assertThat(recolte1).isNotEqualTo(recolte2);
    }
}
