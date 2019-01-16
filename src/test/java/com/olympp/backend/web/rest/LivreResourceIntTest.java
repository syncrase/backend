package com.olympp.backend.web.rest;

import com.olympp.backend.BackendApp;

import com.olympp.backend.domain.Livre;
import com.olympp.backend.repository.LivreRepository;
import com.olympp.backend.service.LivreService;
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
 * Test class for the LivreResource REST controller.
 *
 * @see LivreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class LivreResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTEUR = "AAAAAAAAAA";
    private static final String UPDATED_AUTEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer UPDATED_PAGE = 2;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private LivreService livreService;

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

    private MockMvc restLivreMockMvc;

    private Livre livre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LivreResource livreResource = new LivreResource(livreService);
        this.restLivreMockMvc = MockMvcBuilders.standaloneSetup(livreResource)
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
    public static Livre createEntity(EntityManager em) {
        Livre livre = new Livre()
            .description(DEFAULT_DESCRIPTION)
            .isbn(DEFAULT_ISBN)
            .auteur(DEFAULT_AUTEUR)
            .page(DEFAULT_PAGE);
        return livre;
    }

    @Before
    public void initTest() {
        livre = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivre() throws Exception {
        int databaseSizeBeforeCreate = livreRepository.findAll().size();

        // Create the Livre
        restLivreMockMvc.perform(post("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isCreated());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeCreate + 1);
        Livre testLivre = livreList.get(livreList.size() - 1);
        assertThat(testLivre.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLivre.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testLivre.getAuteur()).isEqualTo(DEFAULT_AUTEUR);
        assertThat(testLivre.getPage()).isEqualTo(DEFAULT_PAGE);
    }

    @Test
    @Transactional
    public void createLivreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livreRepository.findAll().size();

        // Create the Livre with an existing ID
        livre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivreMockMvc.perform(post("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isBadRequest());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLivres() throws Exception {
        // Initialize the database
        livreRepository.saveAndFlush(livre);

        // Get all the livreList
        restLivreMockMvc.perform(get("/api/livres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livre.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN.toString())))
            .andExpect(jsonPath("$.[*].auteur").value(hasItem(DEFAULT_AUTEUR.toString())))
            .andExpect(jsonPath("$.[*].page").value(hasItem(DEFAULT_PAGE)));
    }
    
    @Test
    @Transactional
    public void getLivre() throws Exception {
        // Initialize the database
        livreRepository.saveAndFlush(livre);

        // Get the livre
        restLivreMockMvc.perform(get("/api/livres/{id}", livre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(livre.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN.toString()))
            .andExpect(jsonPath("$.auteur").value(DEFAULT_AUTEUR.toString()))
            .andExpect(jsonPath("$.page").value(DEFAULT_PAGE));
    }

    @Test
    @Transactional
    public void getNonExistingLivre() throws Exception {
        // Get the livre
        restLivreMockMvc.perform(get("/api/livres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivre() throws Exception {
        // Initialize the database
        livreService.save(livre);

        int databaseSizeBeforeUpdate = livreRepository.findAll().size();

        // Update the livre
        Livre updatedLivre = livreRepository.findById(livre.getId()).get();
        // Disconnect from session so that the updates on updatedLivre are not directly saved in db
        em.detach(updatedLivre);
        updatedLivre
            .description(UPDATED_DESCRIPTION)
            .isbn(UPDATED_ISBN)
            .auteur(UPDATED_AUTEUR)
            .page(UPDATED_PAGE);

        restLivreMockMvc.perform(put("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLivre)))
            .andExpect(status().isOk());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeUpdate);
        Livre testLivre = livreList.get(livreList.size() - 1);
        assertThat(testLivre.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLivre.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testLivre.getAuteur()).isEqualTo(UPDATED_AUTEUR);
        assertThat(testLivre.getPage()).isEqualTo(UPDATED_PAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingLivre() throws Exception {
        int databaseSizeBeforeUpdate = livreRepository.findAll().size();

        // Create the Livre

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivreMockMvc.perform(put("/api/livres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livre)))
            .andExpect(status().isBadRequest());

        // Validate the Livre in the database
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLivre() throws Exception {
        // Initialize the database
        livreService.save(livre);

        int databaseSizeBeforeDelete = livreRepository.findAll().size();

        // Get the livre
        restLivreMockMvc.perform(delete("/api/livres/{id}", livre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Livre> livreList = livreRepository.findAll();
        assertThat(livreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Livre.class);
        Livre livre1 = new Livre();
        livre1.setId(1L);
        Livre livre2 = new Livre();
        livre2.setId(livre1.getId());
        assertThat(livre1).isEqualTo(livre2);
        livre2.setId(2L);
        assertThat(livre1).isNotEqualTo(livre2);
        livre1.setId(null);
        assertThat(livre1).isNotEqualTo(livre2);
    }
}
