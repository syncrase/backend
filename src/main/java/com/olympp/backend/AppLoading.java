package com.olympp.backend;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.olympp.backend.domain.ClassificationCronquist;
import com.olympp.backend.domain.Ensoleillement;
import com.olympp.backend.domain.Espece;
import com.olympp.backend.domain.Famille;
import com.olympp.backend.domain.Genre;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.domain.Ordre;
import com.olympp.backend.domain.RichesseSol;
import com.olympp.backend.domain.Strate;
import com.olympp.backend.domain.TypeFeuillage;
import com.olympp.backend.domain.TypeRacine;
import com.olympp.backend.domain.TypeTerre;
import com.olympp.backend.domain.VitesseCroissance;
import com.olympp.backend.repository.EnsoleillementRepository;
import com.olympp.backend.repository.EspeceRepository;
import com.olympp.backend.repository.FamilleRepository;
import com.olympp.backend.repository.GenreRepository;
import com.olympp.backend.repository.MoisRepository;
import com.olympp.backend.repository.OrdreRepository;
import com.olympp.backend.repository.RichesseSolRepository;
import com.olympp.backend.repository.StrateRepository;
import com.olympp.backend.repository.TypeFeuillageRepository;
import com.olympp.backend.repository.TypeRacineRepository;
import com.olympp.backend.repository.TypeTerreRepository;
import com.olympp.backend.repository.VitesseCroissanceRepository;

@Component
public class AppLoading implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = LoggerFactory.getLogger(AppLoading.class);

	private MoisRepository moisRepository;
	private TypeRacineRepository typeRacineRepository;
	private StrateRepository strateRepository;
	private TypeTerreRepository typeTerreRepository;
	private TypeFeuillageRepository typeFeuillageRepository;
	private RichesseSolRepository richesseSolRepository;
	private EnsoleillementRepository ensoleillementRepository;
	private VitesseCroissanceRepository vitesseCroissanceRepository;
	private OrdreRepository ordreRepository;
	private FamilleRepository familleRepository;
	private GenreRepository genreRepository;
	private EspeceRepository especeRepository;

	@Autowired
	public void setMoisRepository(MoisRepository moisRepository) {
		this.moisRepository = moisRepository;
	}

	@Autowired
	public void setTypeRacineRepository(TypeRacineRepository typeRacineRepository) {
		this.typeRacineRepository = typeRacineRepository;
	}

	@Autowired
	public void setStrateRepository(StrateRepository strateRepository) {
		this.strateRepository = strateRepository;
	}

	@Autowired
	public void setTypeTerreRepository(TypeTerreRepository typeTerreRepository) {
		this.typeTerreRepository = typeTerreRepository;
	}

	@Autowired
	public void setTypeFeuillageRepository(TypeFeuillageRepository typeFeuillageRepository) {
		this.typeFeuillageRepository = typeFeuillageRepository;
	}

	@Autowired
	public void setRichesseSolRepository(RichesseSolRepository richesseSolRepository) {
		this.richesseSolRepository = richesseSolRepository;
	}

	@Autowired
	public void setEnsoleillementRepository(EnsoleillementRepository ensoleillementRepository) {
		this.ensoleillementRepository = ensoleillementRepository;
	}

	@Autowired
	public void setVitesseCroissanceRepository(VitesseCroissanceRepository vitesseCroissanceRepository) {
		this.vitesseCroissanceRepository = vitesseCroissanceRepository;
	}

	@Autowired
	public void setOrdreRepository(OrdreRepository ordreRepository) {
		this.ordreRepository = ordreRepository;
	}

	@Autowired
	public void setFamilleRepository(FamilleRepository familleRepository) {
		this.familleRepository = familleRepository;
	}

	@Autowired
	public void setGenreRepository(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Autowired
	public void setEspeceRepository(EspeceRepository especeRepository) {
		this.especeRepository = especeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		try {
			// Populate the 'Mois' table
			// JANVIER, FEVRIER, MARS, AVRIL, MAI, JUIN, JUILLET, AOUT, SEPTEMBRE, OCTOBRE,
			// NOVEMBRE, DECEMBRE
//		List<Mois> moisList = new ArrayList<>();
//		moisList.add(e);
			Mois janvier = new Mois("Janvier");
			Mois fevrier = new Mois("fevrier");
			Mois mars = new Mois("mars");
			Mois avril = new Mois("avril");
			Mois mai = new Mois("mai");
			Mois juin = new Mois("juin");
			Mois juillet = new Mois("juillet");
			Mois aout = new Mois("aout");
			Mois septembre = new Mois("septembre");
			Mois octobre = new Mois("octobre");
			Mois novembre = new Mois("novembre");
			Mois decembre = new Mois("decembre");
			moisRepository.save(janvier);
			moisRepository.save(fevrier);
			moisRepository.save(mars);
			moisRepository.save(avril);
			moisRepository.save(mai);
			moisRepository.save(juin);
			moisRepository.save(juillet);
			moisRepository.save(aout);
			moisRepository.save(septembre);
			moisRepository.save(octobre);
			moisRepository.save(novembre);
			moisRepository.save(decembre);

//		log.info("Saved all month of year");

			// Populate the 'Racine' table
			// PIVOTANTE, FASCICULAIRE, ADVENTICE, TRACANTE, CONTREFORT, CRAMPON, ECHASSE,
			// AERIENNE, LIANE, VENTOUSE, PNEUMATOPHORE
			TypeRacine pivotante = new TypeRacine("pivotante");
			TypeRacine fasciculaire = new TypeRacine("fasciculaire");
			TypeRacine adventice = new TypeRacine("adventice");
			TypeRacine tracante = new TypeRacine("tracante");
			TypeRacine contrefort = new TypeRacine("contrefort");
			TypeRacine crampon = new TypeRacine("crampon");
			TypeRacine echasse = new TypeRacine("echasse");
			TypeRacine aerienne = new TypeRacine("aerienne");
			TypeRacine liane = new TypeRacine("liane");
			TypeRacine ventouse = new TypeRacine("ventouse");
			TypeRacine pneumatophore = new TypeRacine("pneumatophore");
			typeRacineRepository.save(pivotante);
			typeRacineRepository.save(fasciculaire);
			typeRacineRepository.save(adventice);
			typeRacineRepository.save(tracante);
			typeRacineRepository.save(contrefort);
			typeRacineRepository.save(crampon);
			typeRacineRepository.save(echasse);
			typeRacineRepository.save(aerienne);
			typeRacineRepository.save(liane);
			typeRacineRepository.save(ventouse);
			typeRacineRepository.save(pneumatophore);

//		log.info("Saved all types of roots");

			// Populate the 'Strate' table
			// HYPOGEE, MUSCINALE, HERBACEE, ARBUSTIVE, ARBOREE
			Strate hypogee = new Strate("hypogee");
			Strate muscinale = new Strate("muscinale");
			Strate herbacee = new Strate("herbacee");
			Strate arbustive = new Strate("arbustive");
			Strate arboree = new Strate("arboree");
			strateRepository.save(hypogee);
			strateRepository.save(muscinale);
			strateRepository.save(herbacee);
			strateRepository.save(arbustive);
			strateRepository.save(arboree);

//		log.info("Saved all strates");

			// Populate the 'TypeFeuillage' table
			// PERSISTANT, SEMI_PERSISTANT, MARCESCENT, CADUC
			TypeFeuillage persistant = new TypeFeuillage("persistant");
			TypeFeuillage semiPersistant = new TypeFeuillage("semiPersistant");
			TypeFeuillage marcescent = new TypeFeuillage("marcescent");
			TypeFeuillage caduc = new TypeFeuillage("caduc");
			typeFeuillageRepository.save(persistant);
			typeFeuillageRepository.save(semiPersistant);
			typeFeuillageRepository.save(marcescent);
			typeFeuillageRepository.save(caduc);

//		log.info("Saved all types of leafs");

			// Populate the 'TypeTerre' table
			// ARGILEUSE, CALCAIRE, HUMIFERE, SABLEUSE
			TypeTerre argileuse = new TypeTerre("argileuse");
			TypeTerre calcaire = new TypeTerre("calcaire");
			TypeTerre humifere = new TypeTerre("humifere");
			TypeTerre sableuse = new TypeTerre("sableuse");
			typeTerreRepository.save(argileuse);
			typeTerreRepository.save(calcaire);
			typeTerreRepository.save(humifere);
			typeTerreRepository.save(sableuse);

//		log.info("Saved all types of clays");

			// Populate the 'RichesseSol' table
			// TRES_PAUVRE, PAUVRE, NORMALE, RICHE, TRES_RICHE
			RichesseSol tresPauvre = new RichesseSol("tresPauvre");
			RichesseSol pauvre = new RichesseSol("pauvre");
			RichesseSol normale = new RichesseSol("normale");
			RichesseSol riche = new RichesseSol("riche");
			RichesseSol tresRiche = new RichesseSol("tresRiche");
			richesseSolRepository.save(tresPauvre);
			richesseSolRepository.save(pauvre);
			richesseSolRepository.save(normale);
			richesseSolRepository.save(riche);
			richesseSolRepository.save(tresRiche);

//		log.info("Saved all types of soil richness");

			// Populate the 'Ensoleillement' table
			// SOLEIL, MI_OMBRE, OMBRE
			Ensoleillement soleil = new Ensoleillement("soleil");
			Ensoleillement miOmbre = new Ensoleillement("miOmbre");
			Ensoleillement ombre = new Ensoleillement("ombre");
			ensoleillementRepository.save(soleil);
			ensoleillementRepository.save(miOmbre);
			ensoleillementRepository.save(ombre);

//		log.info("Saved all intensities of sunshines");

			// Populate the 'VitesseCroissance' table
			// TRES_LENTE, LENTE, NORMALE, RAPIDE, TRES_RAPIDE
			VitesseCroissance tresLente = new VitesseCroissance("tresLente");
			VitesseCroissance lente = new VitesseCroissance("lente");
			VitesseCroissance vcNormale = new VitesseCroissance("normale");
			VitesseCroissance rapide = new VitesseCroissance("rapide");
			VitesseCroissance tresRapide = new VitesseCroissance("tresRapide");
			vitesseCroissanceRepository.save(tresLente);
			vitesseCroissanceRepository.save(lente);
			vitesseCroissanceRepository.save(vcNormale);
			vitesseCroissanceRepository.save(rapide);
			vitesseCroissanceRepository.save(tresRapide);

//		log.info("Saved all speed of growth");

			Ordre liliales = new Ordre("liliales");
			ordreRepository.save(liliales);

			Famille liliaceae = new Famille("liliaceae");
			familleRepository.save(liliaceae);

			Genre allium = new Genre("allium");
			genreRepository.save(allium);

			Espece alliumSativum = new Espece("allium sativum");
			especeRepository.save(alliumSativum);
			log.info("Saved alliumSativum : { id: " + alliumSativum.getId() + "; name: " + alliumSativum.getName()
					+ "}");

			ClassificationCronquist classif = new ClassificationCronquist(liliales, liliaceae, allium, alliumSativum);
//		log.info("Saved all allium with its classification : " + classif.getId());

			//
			//

			// Création d'un ordre, d'une famille, d'une espèce

			// Création de la classification associée

			// Création d'une plante
		} catch (ConstraintViolationException cve) {
			log.warn("Constraint violation when loading app: " + cve.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
