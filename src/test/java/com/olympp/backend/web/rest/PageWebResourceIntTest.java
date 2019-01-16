package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.PageWeb;
import com.olympp.backend.repository.PageWebRepository;
import com.olympp.backend.service.PageWebService;
import com.olympp.backend.web.rest.errors.ExceptionTranslator;

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
 * Test class for the PageWebResource REST controller.
 *
 * @see PageWebResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class PageWebResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private PageWebRepository pageWebRepository;

    @Autowired
    private PageWebService pageWebService;

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

    private MockMvc restPageWebMockMvc;

    private PageWeb pageWeb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PageWebResource pageWebResource = new PageWebResource(pageWebService);
        this.restPageWebMockMvc = MockMvcBuilders.standaloneSetup(pageWebResource)
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
    public static PageWeb createEntity(EntityManager em) {
        PageWeb pageWeb = new PageWeb()
            .description(DEFAULT_DESCRIPTION)
            .url(DEFAULT_URL);
        return pageWeb;
    }

    @Before
    public void initTest() {
        pageWeb = createEntity(em);
    }

    @Test
    @Transactional
    public void createPageWeb() throws Exception {
        int databaseSizeBeforeCreate = pageWebRepository.findAll().size();

        // Create the PageWeb
        restPageWebMockMvc.perform(post("/api/page-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageWeb)))
            .andExpect(status().isCreated());

        // Validate the PageWeb in the database
        List<PageWeb> pageWebList = pageWebRepository.findAll();
        assertThat(pageWebList).hasSize(databaseSizeBeforeCreate + 1);
        PageWeb testPageWeb = pageWebList.get(pageWebList.size() - 1);
        assertThat(testPageWeb.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPageWeb.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createPageWebWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pageWebRepository.findAll().size();

        // Create the PageWeb with an existing ID
        pageWeb.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPageWebMockMvc.perform(post("/api/page-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageWeb)))
            .andExpect(status().isBadRequest());

        // Validate the PageWeb in the database
        List<PageWeb> pageWebList = pageWebRepository.findAll();
        assertThat(pageWebList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPageWebs() throws Exception {
        // Initialize the database
        pageWebRepository.saveAndFlush(pageWeb);

        // Get all the pageWebList
        restPageWebMockMvc.perform(get("/api/page-webs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pageWeb.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getPageWeb() throws Exception {
        // Initialize the database
        pageWebRepository.saveAndFlush(pageWeb);

        // Get the pageWeb
        restPageWebMockMvc.perform(get("/api/page-webs/{id}", pageWeb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pageWeb.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPageWeb() throws Exception {
        // Get the pageWeb
        restPageWebMockMvc.perform(get("/api/page-webs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePageWeb() throws Exception {
        // Initialize the database
        pageWebService.save(pageWeb);

        int databaseSizeBeforeUpdate = pageWebRepository.findAll().size();

        // Update the pageWeb
        PageWeb updatedPageWeb = pageWebRepository.findById(pageWeb.getId()).get();
        // Disconnect from session so that the updates on updatedPageWeb are not directly saved in db
        em.detach(updatedPageWeb);
        updatedPageWeb
            .description(UPDATED_DESCRIPTION)
            .url(UPDATED_URL);

        restPageWebMockMvc.perform(put("/api/page-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPageWeb)))
            .andExpect(status().isOk());

        // Validate the PageWeb in the database
        List<PageWeb> pageWebList = pageWebRepository.findAll();
        assertThat(pageWebList).hasSize(databaseSizeBeforeUpdate);
        PageWeb testPageWeb = pageWebList.get(pageWebList.size() - 1);
        assertThat(testPageWeb.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPageWeb.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingPageWeb() throws Exception {
        int databaseSizeBeforeUpdate = pageWebRepository.findAll().size();

        // Create the PageWeb

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPageWebMockMvc.perform(put("/api/page-webs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageWeb)))
            .andExpect(status().isBadRequest());

        // Validate the PageWeb in the database
        List<PageWeb> pageWebList = pageWebRepository.findAll();
        assertThat(pageWebList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePageWeb() throws Exception {
        // Initialize the database
        pageWebService.save(pageWeb);

        int databaseSizeBeforeDelete = pageWebRepository.findAll().size();

        // Get the pageWeb
        restPageWebMockMvc.perform(delete("/api/page-webs/{id}", pageWeb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PageWeb> pageWebList = pageWebRepository.findAll();
        assertThat(pageWebList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PageWeb.class);
        PageWeb pageWeb1 = new PageWeb();
        pageWeb1.setId(1L);
        PageWeb pageWeb2 = new PageWeb();
        pageWeb2.setId(pageWeb1.getId());
        assertThat(pageWeb1).isEqualTo(pageWeb2);
        pageWeb2.setId(2L);
        assertThat(pageWeb1).isNotEqualTo(pageWeb2);
        pageWeb1.setId(null);
        assertThat(pageWeb1).isNotEqualTo(pageWeb2);
    }
}
