package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.PlantCommonName;
import com.olympp.backend.domain.Plante;
import com.olympp.backend.repository.PlantCommonNameRepository;
import com.olympp.backend.service.PlantCommonNameService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;
import com.olympp.backend.service.dto.PlantCommonNameCriteria;
import com.olympp.backend.service.PlantCommonNameQueryService;

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
 * Test class for the PlantCommonNameResource REST controller.
 *
 * @see PlantCommonNameResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class PlantCommonNameResourceIntTest {

    private static final String DEFAULT_COMMON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMMON_NAME = "BBBBBBBBBB";

    @Autowired
    private PlantCommonNameRepository plantCommonNameRepository;

    @Autowired
    private PlantCommonNameService plantCommonNameService;

    @Autowired
    private PlantCommonNameQueryService plantCommonNameQueryService;

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

    private MockMvc restPlantCommonNameMockMvc;

    private PlantCommonName plantCommonName;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlantCommonNameResource plantCommonNameResource = new PlantCommonNameResource(plantCommonNameService, plantCommonNameQueryService);
        this.restPlantCommonNameMockMvc = MockMvcBuilders.standaloneSetup(plantCommonNameResource)
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
    public static PlantCommonName createEntity(EntityManager em) {
        PlantCommonName plantCommonName = new PlantCommonName()
            .commonName(DEFAULT_COMMON_NAME);
        return plantCommonName;
    }

    @Before
    public void initTest() {
        plantCommonName = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlantCommonName() throws Exception {
        int databaseSizeBeforeCreate = plantCommonNameRepository.findAll().size();

        // Create the PlantCommonName
        restPlantCommonNameMockMvc.perform(post("/api/plant-common-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantCommonName)))
            .andExpect(status().isCreated());

        // Validate the PlantCommonName in the database
        List<PlantCommonName> plantCommonNameList = plantCommonNameRepository.findAll();
        assertThat(plantCommonNameList).hasSize(databaseSizeBeforeCreate + 1);
        PlantCommonName testPlantCommonName = plantCommonNameList.get(plantCommonNameList.size() - 1);
        assertThat(testPlantCommonName.getCommonName()).isEqualTo(DEFAULT_COMMON_NAME);
    }

    @Test
    @Transactional
    public void createPlantCommonNameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = plantCommonNameRepository.findAll().size();

        // Create the PlantCommonName with an existing ID
        plantCommonName.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlantCommonNameMockMvc.perform(post("/api/plant-common-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantCommonName)))
            .andExpect(status().isBadRequest());

        // Validate the PlantCommonName in the database
        List<PlantCommonName> plantCommonNameList = plantCommonNameRepository.findAll();
        assertThat(plantCommonNameList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlantCommonNames() throws Exception {
        // Initialize the database
        plantCommonNameRepository.saveAndFlush(plantCommonName);

        // Get all the plantCommonNameList
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plantCommonName.getId().intValue())))
            .andExpect(jsonPath("$.[*].commonName").value(hasItem(DEFAULT_COMMON_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getPlantCommonName() throws Exception {
        // Initialize the database
        plantCommonNameRepository.saveAndFlush(plantCommonName);

        // Get the plantCommonName
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names/{id}", plantCommonName.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(plantCommonName.getId().intValue()))
            .andExpect(jsonPath("$.commonName").value(DEFAULT_COMMON_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllPlantCommonNamesByCommonNameIsEqualToSomething() throws Exception {
        // Initialize the database
        plantCommonNameRepository.saveAndFlush(plantCommonName);

        // Get all the plantCommonNameList where commonName equals to DEFAULT_COMMON_NAME
        defaultPlantCommonNameShouldBeFound("commonName.equals=" + DEFAULT_COMMON_NAME);

        // Get all the plantCommonNameList where commonName equals to UPDATED_COMMON_NAME
        defaultPlantCommonNameShouldNotBeFound("commonName.equals=" + UPDATED_COMMON_NAME);
    }

    @Test
    @Transactional
    public void getAllPlantCommonNamesByCommonNameIsInShouldWork() throws Exception {
        // Initialize the database
        plantCommonNameRepository.saveAndFlush(plantCommonName);

        // Get all the plantCommonNameList where commonName in DEFAULT_COMMON_NAME or UPDATED_COMMON_NAME
        defaultPlantCommonNameShouldBeFound("commonName.in=" + DEFAULT_COMMON_NAME + "," + UPDATED_COMMON_NAME);

        // Get all the plantCommonNameList where commonName equals to UPDATED_COMMON_NAME
        defaultPlantCommonNameShouldNotBeFound("commonName.in=" + UPDATED_COMMON_NAME);
    }

    @Test
    @Transactional
    public void getAllPlantCommonNamesByCommonNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        plantCommonNameRepository.saveAndFlush(plantCommonName);

        // Get all the plantCommonNameList where commonName is not null
        defaultPlantCommonNameShouldBeFound("commonName.specified=true");

        // Get all the plantCommonNameList where commonName is null
        defaultPlantCommonNameShouldNotBeFound("commonName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlantCommonNamesByPlanteIsEqualToSomething() throws Exception {
        // Initialize the database
        Plante plante = PlanteResourceIntTest.createEntity(em);
        em.persist(plante);
        em.flush();
        plantCommonName.addPlante(plante);
        plantCommonNameRepository.saveAndFlush(plantCommonName);
        Long planteId = plante.getId();

        // Get all the plantCommonNameList where plante equals to planteId
        defaultPlantCommonNameShouldBeFound("planteId.equals=" + planteId);

        // Get all the plantCommonNameList where plante equals to planteId + 1
        defaultPlantCommonNameShouldNotBeFound("planteId.equals=" + (planteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlantCommonNameShouldBeFound(String filter) throws Exception {
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plantCommonName.getId().intValue())))
            .andExpect(jsonPath("$.[*].commonName").value(hasItem(DEFAULT_COMMON_NAME.toString())));

        // Check, that the count call also returns 1
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlantCommonNameShouldNotBeFound(String filter) throws Exception {
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlantCommonName() throws Exception {
        // Get the plantCommonName
        restPlantCommonNameMockMvc.perform(get("/api/plant-common-names/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlantCommonName() throws Exception {
        // Initialize the database
        plantCommonNameService.save(plantCommonName);

        int databaseSizeBeforeUpdate = plantCommonNameRepository.findAll().size();

        // Update the plantCommonName
        PlantCommonName updatedPlantCommonName = plantCommonNameRepository.findById(plantCommonName.getId()).get();
        // Disconnect from session so that the updates on updatedPlantCommonName are not directly saved in db
        em.detach(updatedPlantCommonName);
        updatedPlantCommonName
            .commonName(UPDATED_COMMON_NAME);

        restPlantCommonNameMockMvc.perform(put("/api/plant-common-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlantCommonName)))
            .andExpect(status().isOk());

        // Validate the PlantCommonName in the database
        List<PlantCommonName> plantCommonNameList = plantCommonNameRepository.findAll();
        assertThat(plantCommonNameList).hasSize(databaseSizeBeforeUpdate);
        PlantCommonName testPlantCommonName = plantCommonNameList.get(plantCommonNameList.size() - 1);
        assertThat(testPlantCommonName.getCommonName()).isEqualTo(UPDATED_COMMON_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPlantCommonName() throws Exception {
        int databaseSizeBeforeUpdate = plantCommonNameRepository.findAll().size();

        // Create the PlantCommonName

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlantCommonNameMockMvc.perform(put("/api/plant-common-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantCommonName)))
            .andExpect(status().isBadRequest());

        // Validate the PlantCommonName in the database
        List<PlantCommonName> plantCommonNameList = plantCommonNameRepository.findAll();
        assertThat(plantCommonNameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlantCommonName() throws Exception {
        // Initialize the database
        plantCommonNameService.save(plantCommonName);

        int databaseSizeBeforeDelete = plantCommonNameRepository.findAll().size();

        // Get the plantCommonName
        restPlantCommonNameMockMvc.perform(delete("/api/plant-common-names/{id}", plantCommonName.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlantCommonName> plantCommonNameList = plantCommonNameRepository.findAll();
        assertThat(plantCommonNameList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlantCommonName.class);
        PlantCommonName plantCommonName1 = new PlantCommonName();
        plantCommonName1.setId(1L);
        PlantCommonName plantCommonName2 = new PlantCommonName();
        plantCommonName2.setId(plantCommonName1.getId());
        assertThat(plantCommonName1).isEqualTo(plantCommonName2);
        plantCommonName2.setId(2L);
        assertThat(plantCommonName1).isNotEqualTo(plantCommonName2);
        plantCommonName1.setId(null);
        assertThat(plantCommonName1).isNotEqualTo(plantCommonName2);
    }
}
