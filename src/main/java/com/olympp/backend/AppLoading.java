package com.olympp.backend;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.olympp.backend.domain.ClassificationCronquist;
import com.olympp.backend.domain.Ensoleillement;
import com.olympp.backend.domain.Espece;
import com.olympp.backend.domain.Famille;
import com.olympp.backend.domain.Genre;
import com.olympp.backend.domain.InteractionPlantePlante;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.domain.Ordre;
import com.olympp.backend.domain.Plante;
import com.olympp.backend.domain.RichesseSol;
import com.olympp.backend.domain.Strate;
import com.olympp.backend.domain.TypeFeuillage;
import com.olympp.backend.domain.TypeRacine;
import com.olympp.backend.domain.TypeTerre;
import com.olympp.backend.domain.VitesseCroissance;
import com.olympp.backend.repository.ClassificationCronquistRepository;
import com.olympp.backend.repository.EnsoleillementRepository;
import com.olympp.backend.repository.EspeceRepository;
import com.olympp.backend.repository.FamilleRepository;
import com.olympp.backend.repository.GenreRepository;
import com.olympp.backend.repository.MoisRepository;
import com.olympp.backend.repository.OrdreRepository;
import com.olympp.backend.repository.PlanteRepository;
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
	private PlanteRepository planteRepository;
	private ClassificationCronquistRepository classificationCronquistRepository;

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

	@Autowired
	public void setPlanteRepository(PlanteRepository planteRepository) {
		this.planteRepository = planteRepository;
	}

	@Autowired
	public void setClassificationCronquistRepository(
			ClassificationCronquistRepository classificationCronquistRepository) {
		this.classificationCronquistRepository = classificationCronquistRepository;
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

			ClassificationCronquist alliumSativumC = new ClassificationCronquist(liliales, liliaceae, allium,
					alliumSativum);
			classificationCronquistRepository.save(alliumSativumC);
			Plante ail = new Plante("ail", null, null, null, null, alliumSativumC, null, null, null, null, null, null,
					null, null, null);
			planteRepository.save(ail);

//		log.info("Saved all allium with its classification : " + classif.getId());

			Ordre apiales = new Ordre("apiales");
			ordreRepository.save(apiales);
			Famille apiaceae = new Famille("apiaceae");
			familleRepository.save(apiaceae);
			Genre daucus = new Genre("daucus");
			genreRepository.save(daucus);
			Espece daucusCarota = new Espece("daucus carota");
			especeRepository.save(daucusCarota);
			ClassificationCronquist daucusCarotaC = new ClassificationCronquist(apiales, apiaceae, daucus,
					daucusCarota);
			classificationCronquistRepository.save(daucusCarotaC);
			Plante carotte = new Plante("carotte", null, null, null, null, daucusCarotaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(carotte);

			Genre anethum = new Genre("anethum");
			genreRepository.save(allium);
			Espece anethumGraveolens = new Espece("Anethum graveolens");
			especeRepository.save(anethumGraveolens);
			ClassificationCronquist anethumGraveolensC = new ClassificationCronquist(apiales, apiaceae, anethum,
					anethumGraveolens);
			classificationCronquistRepository.save(anethumGraveolensC);
			Plante aneth = new Plante("aneth", null, null, null, null, anethumGraveolensC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(aneth);

			Espece alliumCepa = new Espece("allium cepa");
			especeRepository.save(alliumCepa);
			ClassificationCronquist alliumCepaC = new ClassificationCronquist(liliales, liliaceae, allium, alliumCepa);
			classificationCronquistRepository.save(alliumCepaC);
			Plante echalotte = new Plante("echalotte", null, null, null, null, alliumCepaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(echalotte);
			Plante oignon = new Plante("oignon", null, null, null, null, alliumCepaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(oignon);

			Famille alliaceae = new Famille("alliaceae");
			familleRepository.save(alliaceae);
			Espece alliumPorrum = new Espece("allium porrum");
			especeRepository.save(alliumPorrum);
			ClassificationCronquist alliumPorrumC = new ClassificationCronquist(liliales, alliaceae, allium,
					alliumPorrum);
			classificationCronquistRepository.save(alliumPorrumC);
			Plante poireau = new Plante("poireau", null, null, null, null, alliumPorrumC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(poireau);

			Ordre brassicales = new Ordre("brassicales");
			ordreRepository.save(brassicales);
			Famille brassicaceae = new Famille("brassicaceae");
			familleRepository.save(brassicaceae);
			Genre brassica = new Genre("brassica");
			genreRepository.save(brassica);
			Espece brassicaOleracea = new Espece("brassica oleracea");
			especeRepository.save(brassicaOleracea);
			ClassificationCronquist brassicaOleraceaC = new ClassificationCronquist(brassicales, brassicaceae, brassica,
					brassicaOleracea);
			classificationCronquistRepository.save(brassicaOleraceaC);
			Plante chouCommun = new Plante("chou commun", null, null, null, null, brassicaOleraceaC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(chouCommun);
			Plante chouRave = new Plante("chou-rave", null, null, null, null, brassicaOleraceaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(chouRave);
			Plante brocoli = new Plante("brocoli", null, null, null, null, brassicaOleraceaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(brocoli);
			Plante chouFleur = new Plante("chou fleur", null, null, null, null, brassicaOleraceaC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(chouFleur);

			Ordre lamiales = new Ordre("lamiales");
			ordreRepository.save(lamiales);
			Famille lamiaceae = new Famille("lamiaceae");
			familleRepository.save(lamiaceae);
			Genre satureja = new Genre("satureja");
			genreRepository.save(satureja);
			Espece saturejaHortensis = new Espece("satureja hortensis");
			especeRepository.save(saturejaHortensis);
			ClassificationCronquist saturejaHortensisC = new ClassificationCronquist(lamiales, lamiaceae, satureja,
					saturejaHortensis);
			classificationCronquistRepository.save(saturejaHortensisC);
			Plante sarriette = new Plante("sarriette", null, null, null, null, saturejaHortensisC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(sarriette);

			Ordre caryophyllales = new Ordre("caryophyllales");
			ordreRepository.save(caryophyllales);
			Famille amaranthaceae = new Famille("amaranthaceae");
			familleRepository.save(amaranthaceae);
			Genre beta = new Genre("beta");
			genreRepository.save(beta);
			Espece betaVulgaris = new Espece("beta vulgaris");
			especeRepository.save(betaVulgaris);
			ClassificationCronquist betaVulgarisC = new ClassificationCronquist(caryophyllales, amaranthaceae, beta,
					betaVulgaris);
			classificationCronquistRepository.save(betaVulgarisC);
			Plante betterave = new Plante("betterave", null, null, null, null, betaVulgarisC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(betterave);
			Plante bette = new Plante("bette", null, null, null, null, betaVulgarisC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(bette);

			Ordre solanales = new Ordre("solanales");
			ordreRepository.save(solanales);
			Famille solanaceae = new Famille("solanaceae");
			familleRepository.save(solanaceae);
			Genre solanum = new Genre("solanum");
			genreRepository.save(solanum);
			Espece solanumLycopersicum = new Espece("solanum lycopersicum");
			especeRepository.save(solanumLycopersicum);
			ClassificationCronquist solanumLycopersicumC = new ClassificationCronquist(solanales, solanaceae, solanum,
					solanumLycopersicum);
			classificationCronquistRepository.save(solanumLycopersicumC);
			Plante tomate = new Plante("tomate", null, null, null, null, solanumLycopersicumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(tomate);

			Ordre asterales = new Ordre("asterales");
			ordreRepository.save(asterales);
			Famille asteraceae = new Famille("asteraceae");
			familleRepository.save(asteraceae);
			Genre lactuca = new Genre("lactuca");
			genreRepository.save(lactuca);
			Espece lactucaSativa = new Espece("lactuca sativa");
			especeRepository.save(lactucaSativa);
			ClassificationCronquist lactucaSativaC = new ClassificationCronquist(asterales, asteraceae, lactuca,
					lactucaSativa);
			classificationCronquistRepository.save(lactucaSativaC);
			Plante laitue = new Plante("laitue", null, null, null, null, lactucaSativaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(laitue);

			Ordre violales = new Ordre("violales");
			ordreRepository.save(violales);
			Famille cucurbitaceae = new Famille("cucurbitaceae");
			familleRepository.save(cucurbitaceae);
			Genre cucumis = new Genre("cucumis");
			genreRepository.save(cucumis);
			Espece cucumisSativus = new Espece("cucumis sativus");
			especeRepository.save(cucumisSativus);
			ClassificationCronquist cucumisSativusC = new ClassificationCronquist(violales, cucurbitaceae, cucumis,
					cucumisSativus);
			classificationCronquistRepository.save(cucumisSativusC);
			Plante concombre = new Plante("concombre", null, null, null, null, cucumisSativusC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(concombre);

			Genre ocimum = new Genre("ocimum");
			genreRepository.save(ocimum);
			Espece ocimumBasilicum = new Espece("ocimum basilicum");
			especeRepository.save(ocimumBasilicum);
			ClassificationCronquist ocimumBasilicumC = new ClassificationCronquist(lamiales, lamiaceae, ocimum,
					ocimumBasilicum);
			classificationCronquistRepository.save(ocimumBasilicumC);
			Plante basilic = new Plante("basilic", null, null, null, null, ocimumBasilicumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(basilic);

			Ordre fabales = new Ordre("fabales");
			ordreRepository.save(fabales);
			Famille fabaceae = new Famille("fabaceae");
			familleRepository.save(fabaceae);
			Genre phaseolus = new Genre("phaseolus");
			genreRepository.save(phaseolus);
			Espece phaseolusVulgaris = new Espece("phaseolus vulgaris");
			especeRepository.save(phaseolusVulgaris);
			ClassificationCronquist phaseolusVulgarisC = new ClassificationCronquist(fabales, fabaceae, phaseolus,
					phaseolusVulgaris);
			classificationCronquistRepository.save(phaseolusVulgarisC);
			Plante haricot = new Plante("haricot", null, null, null, null, phaseolusVulgarisC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(haricot);

			Genre pisum = new Genre("pisum");
			genreRepository.save(pisum);
			Espece pisumSativum = new Espece("pisum sativum");
			especeRepository.save(pisumSativum);
			ClassificationCronquist pisumSativumC = new ClassificationCronquist(fabales, fabaceae, pisum, pisumSativum);
			classificationCronquistRepository.save(pisumSativumC);
			Plante pois = new Plante("pois", null, null, null, null, pisumSativumC, null, null, null, null, null, null,
					null, null, null);
			planteRepository.save(pois);

			Genre vicia = new Genre("vicia");
			genreRepository.save(vicia);
			Espece viciaFaba = new Espece("vicia faba");
			especeRepository.save(viciaFaba);
			ClassificationCronquist viciaFabaC = new ClassificationCronquist(fabales, fabaceae, vicia, viciaFaba);
			classificationCronquistRepository.save(viciaFabaC);
			Plante feve = new Plante("fève", null, null, null, null, viciaFabaC, null, null, null, null, null, null,
					null, null, null);
			planteRepository.save(feve);

			Espece solanumTuberosum = new Espece("solanum tuberosum");
			especeRepository.save(solanumTuberosum);
			ClassificationCronquist solanumTuberosumC = new ClassificationCronquist(solanales, solanaceae, solanum,
					solanumTuberosum);
			classificationCronquistRepository.save(solanumTuberosumC);
			Plante pommeDeTerre = new Plante("pomme de terre", null, null, null, null, solanumTuberosumC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(pommeDeTerre);

			Genre petroselinum = new Genre("petroselinum");
			genreRepository.save(petroselinum);
			Espece petroselinumCrispum = new Espece("petroselinum crispum");
			especeRepository.save(petroselinumCrispum);
			ClassificationCronquist petroselinumCrispumC = new ClassificationCronquist(apiales, apiaceae, petroselinum,
					petroselinumCrispum);
			classificationCronquistRepository.save(petroselinumCrispumC);
			Plante persil = new Plante("persil", null, null, null, null, petroselinumCrispumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(persil);

			Genre coriandrum = new Genre("coriandrum");
			genreRepository.save(coriandrum);
			Espece coriandrumSativum = new Espece("coriandrum sativum");
			especeRepository.save(coriandrumSativum);
			ClassificationCronquist coriandrumSativumC = new ClassificationCronquist(apiales, apiaceae, coriandrum,
					coriandrumSativum);
			classificationCronquistRepository.save(coriandrumSativumC);
			Plante coriandre = new Plante("coriandre", null, null, null, null, coriandrumSativumC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(coriandre);

			Ordre cyperales = new Ordre("cyperales");
			ordreRepository.save(cyperales);
			Famille poaceae = new Famille("poaceae");
			familleRepository.save(poaceae);
			Genre zea = new Genre("zea");
			genreRepository.save(zea);
			Espece zeaMays = new Espece("zea mays");
			especeRepository.save(zeaMays);
			ClassificationCronquist zeaMaysC = new ClassificationCronquist(cyperales, poaceae, zea, zeaMays);
			classificationCronquistRepository.save(zeaMaysC);
			Plante mais = new Plante("maïs", null, null, null, null, zeaMaysC, null, null, null, null, null, null, null,
					null, null);
			planteRepository.save(mais);

			Genre cucurbita = new Genre("cucurbita");
			genreRepository.save(cucurbita);
			Espece cucurbitaMaxima = new Espece("cucurbita maxima");
			especeRepository.save(cucurbitaMaxima);
			ClassificationCronquist cucurbitaMaximaC = new ClassificationCronquist(violales, cucurbitaceae, cucurbita,
					cucurbitaMaxima);
			classificationCronquistRepository.save(cucurbitaMaximaC);
			Plante courge = new Plante("courge", null, null, null, null, cucurbitaMaximaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(courge);
			Plante potiron = new Plante("potiron", null, null, null, null, cucurbitaMaximaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(potiron);
			Plante courgette = new Plante("courgette", null, null, null, null, cucurbitaMaximaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(courgette);

			Ordre rosales = new Ordre("rosales");
			ordreRepository.save(rosales);
			Famille rosaceae = new Famille("rosaceae");
			familleRepository.save(rosaceae);
			Genre rubus = new Genre("rubus");
			genreRepository.save(rubus);
			Espece rubusIdaeus = new Espece("rubus idaeus");
			especeRepository.save(rubusIdaeus);
			ClassificationCronquist rubusIdaeusC = new ClassificationCronquist(rosales, rosaceae, rubus, rubusIdaeus);
			classificationCronquistRepository.save(rubusIdaeusC);
			Plante framboisier = new Plante("framboisier", null, null, null, null, rubusIdaeusC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(framboisier);

//			Ordre  = new Ordre("");
//			ordreRepository.save();
			Famille boraginaceae = new Famille("boraginaceae");
			familleRepository.save(boraginaceae);
			Genre myosotis = new Genre("myosotis");
			genreRepository.save(myosotis);
			Espece myosotisArvensis = new Espece("myosotis arvensis");
			especeRepository.save(myosotisArvensis);
			ClassificationCronquist myosotisArvensisC = new ClassificationCronquist(lamiales, boraginaceae, myosotis,
					myosotisArvensis);
			classificationCronquistRepository.save(myosotisArvensisC);
			Plante myosotisDesChamps = new Plante("myosotis des champs", null, null, null, null, myosotisArvensisC,
					null, null, null, null, null, null, null, null, null);
			planteRepository.save(myosotisDesChamps);

			Ordre geraniales = new Ordre("geraniales");
			ordreRepository.save(geraniales);
			Famille tropaeolaceae = new Famille("tropaeolaceae");
			familleRepository.save(tropaeolaceae);
			Genre tropaeolum = new Genre("tropaeolum");
			genreRepository.save(tropaeolum);
			Espece tropaeolumMajus = new Espece("tropaeolum majus");
			especeRepository.save(tropaeolumMajus);
			ClassificationCronquist tropaeolumMajusC = new ClassificationCronquist(geraniales, tropaeolaceae,
					tropaeolum, tropaeolumMajus);
			classificationCronquistRepository.save(tropaeolumMajusC);
			Plante grandeCapucine = new Plante("grande capucine", null, null, null, null, tropaeolumMajusC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(grandeCapucine);

			Genre tagetes = new Genre("tagetes");
			genreRepository.save(tagetes);
			Espece tagetesPatula = new Espece("tagetes patula");
			especeRepository.save(tagetesPatula);
			ClassificationCronquist tagetesPatulaC = new ClassificationCronquist(asterales, asteraceae, tagetes,
					tagetesPatula);
			classificationCronquistRepository.save(tagetesPatulaC);
			Plante oeilletsDInde = new Plante("oeillets d'inde", null, null, null, null, tagetesPatulaC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(oeilletsDInde);

			Espece alliumSchoenoprasum = new Espece("allium schoenoprasum");
			especeRepository.save(alliumSchoenoprasum);
			ClassificationCronquist alliumSchoenoprasumC = new ClassificationCronquist(liliales, liliaceae, allium,
					alliumSchoenoprasum);
			classificationCronquistRepository.save(alliumSchoenoprasumC);
			Plante ciboulette = new Plante("ciboulette", null, null, null, null, alliumSchoenoprasumC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(ciboulette);

			Genre rosa = new Genre("rosa");
			genreRepository.save(rosa);
			Espece rosaGallica = new Espece("rosa gallica");
			especeRepository.save(rosaGallica);
			ClassificationCronquist rosaGallicaC = new ClassificationCronquist(rosales, rosaceae, rosa, rosaGallica);
			classificationCronquistRepository.save(rosaGallicaC);
			Plante rosierDeFrance = new Plante("rosier de france", null, null, null, null, rosaGallicaC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(rosierDeFrance);

			Genre rosmarinus = new Genre("rosmarinus");
			genreRepository.save(rosmarinus);
			Espece rosmarinusOfficinalis = new Espece("rosmarinus officinalis");
			especeRepository.save(rosmarinusOfficinalis);
			ClassificationCronquist rosmarinusOfficinalisC = new ClassificationCronquist(lamiales, lamiaceae,
					rosmarinus, rosmarinusOfficinalis);
			classificationCronquistRepository.save(rosmarinusOfficinalisC);
			Plante romarin = new Plante("romarin", null, null, null, null, rosmarinusOfficinalisC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(romarin);

			Genre thymus = new Genre("thymus");
			genreRepository.save(thymus);
			Espece thymusVulgaris = new Espece("thymus vulgaris");
			especeRepository.save(thymusVulgaris);
			ClassificationCronquist thymusVulgarisC = new ClassificationCronquist(lamiales, lamiaceae, thymus,
					thymusVulgaris);
			classificationCronquistRepository.save(thymusVulgarisC);
			Plante thymCommun = new Plante("thym commun", null, null, null, null, thymusVulgarisC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(thymCommun);

			Genre asparagus = new Genre("asparagus");
			genreRepository.save(asparagus);
			Espece asparagusOfficinalis = new Espece("asparagus officinalis");
			especeRepository.save(asparagusOfficinalis);
			ClassificationCronquist asparagusOfficinalisC = new ClassificationCronquist(liliales, liliaceae, asparagus,
					asparagusOfficinalis);
			classificationCronquistRepository.save(asparagusOfficinalisC);
			Plante asperge = new Plante("asperge", null, null, null, null, asparagusOfficinalisC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(asperge);

			Espece solanumMelongena = new Espece("solanum melongena");
			especeRepository.save(solanumMelongena);
			ClassificationCronquist solanumMelongenaC = new ClassificationCronquist(solanales, solanaceae, solanum,
					solanumMelongena);
			classificationCronquistRepository.save(solanumMelongenaC);
			Plante aubergine = new Plante("aubergine", null, null, null, null, solanumMelongenaC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(aubergine);

			Ordre capparales = new Ordre("capparales");
			ordreRepository.save(capparales);
			Genre raphanus = new Genre("raphanus");
			genreRepository.save(raphanus);
			Espece raphanusSativus = new Espece("raphanus sativus");
			especeRepository.save(raphanusSativus);
			ClassificationCronquist raphanusSativusC = new ClassificationCronquist(capparales, brassicaceae, raphanus,
					raphanusSativus);
			classificationCronquistRepository.save(raphanusSativusC);
			Plante radis = new Plante("radis", null, null, null, null, raphanusSativusC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(radis);

			Genre salvia = new Genre("salvia");
			genreRepository.save(salvia);
			Espece salviaOfficinalis = new Espece("salvia officinalis");
			especeRepository.save(salviaOfficinalis);
			ClassificationCronquist salviaOfficinalisC = new ClassificationCronquist(lamiales, lamiaceae, salvia,
					salviaOfficinalis);
			classificationCronquistRepository.save(salviaOfficinalisC);
			Plante sauge = new Plante("sauge", null, null, null, null, salviaOfficinalisC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(sauge);

			Genre mentha = new Genre("mentha");
			genreRepository.save(mentha);
			Espece menthaXpiperita = new Espece("mentha xpiperita");
			especeRepository.save(menthaXpiperita);
			ClassificationCronquist menthaXpiperitaC = new ClassificationCronquist(lamiales, lamiaceae, mentha,
					menthaXpiperita);
			classificationCronquistRepository.save(menthaXpiperitaC);
			Plante menthePoivree = new Plante("menthe poivrée", null, null, null, null, menthaXpiperitaC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(menthePoivree);

			Genre origanum = new Genre("origanum");
			genreRepository.save(origanum);
			Espece origanumVulgare = new Espece("origanum vulgare");
			especeRepository.save(origanumVulgare);
			ClassificationCronquist origanumVulgareC = new ClassificationCronquist(lamiales, lamiaceae, origanum,
					origanumVulgare);
			classificationCronquistRepository.save(origanumVulgareC);
			Plante origan = new Plante("origan", null, null, null, null, origanumVulgareC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(origan);

			Genre helianthus = new Genre("helianthus");
			genreRepository.save(helianthus);
			Espece helianthusAnnuus = new Espece("helianthus annuus");
			especeRepository.save(helianthusAnnuus);
			ClassificationCronquist helianthusAnnuusC = new ClassificationCronquist(asterales, asteraceae, helianthus,
					helianthusAnnuus);
			classificationCronquistRepository.save(helianthusAnnuusC);
			Plante tournesol = new Plante("tournesol", null, null, null, null, helianthusAnnuusC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(tournesol);

			Genre fragaria = new Genre("fragaria");
			genreRepository.save(fragaria);
			Espece fragariaVesca = new Espece("fragaria vesca");
			especeRepository.save(fragariaVesca);
			ClassificationCronquist fragariaVescaC = new ClassificationCronquist(rosales, rosaceae, fragaria,
					fragariaVesca);
			classificationCronquistRepository.save(fragariaVescaC);
			Plante fraisier = new Plante("fraisier des bois", null, null, null, null, fragariaVescaC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(fraisier);

			Famille chenopodiaceae = new Famille("chenopodiaceae");
			familleRepository.save(chenopodiaceae);
			Genre spinacia = new Genre("spinacia");
			genreRepository.save(spinacia);
			Espece spinaciaOleracea = new Espece("spinacia oleracea");
			especeRepository.save(spinaciaOleracea);
			ClassificationCronquist spinaciaOleraceaC = new ClassificationCronquist(caryophyllales, chenopodiaceae,
					spinacia, spinaciaOleracea);
			classificationCronquistRepository.save(spinaciaOleraceaC);
			Plante epinard = new Plante("epinard", null, null, null, null, spinaciaOleraceaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(epinard);

			Genre borago = new Genre("borago");
			genreRepository.save(borago);
			Espece boragoOfficinalis = new Espece("borago officinalis");
			especeRepository.save(boragoOfficinalis);
			ClassificationCronquist boragoOfficinalisC = new ClassificationCronquist(lamiales, boraginaceae, borago,
					boragoOfficinalis);
			classificationCronquistRepository.save(boragoOfficinalisC);
			Plante bourrache = new Plante("bourrache", null, null, null, null, boragoOfficinalisC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(bourrache);

			Genre calendula = new Genre("calendula");
			genreRepository.save(calendula);
			Espece calendulaOfficinalis = new Espece("calendula officinalis");
			especeRepository.save(calendulaOfficinalis);
			ClassificationCronquist calendulaOfficinalisC = new ClassificationCronquist(asterales, asteraceae,
					calendula, calendulaOfficinalis);
			classificationCronquistRepository.save(calendulaOfficinalisC);
			Plante souci = new Plante("souci officinal", null, null, null, null, calendulaOfficinalisC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(souci);

			Espece cucumisMelo = new Espece("cucumis melo");
			especeRepository.save(cucumisMelo);
			ClassificationCronquist cucumisMeloC = new ClassificationCronquist(violales, cucurbitaceae, cucumis,
					cucumisMelo);
			classificationCronquistRepository.save(cucumisMeloC);
			Plante melon = new Plante("melon", null, null, null, null, cucumisMeloC, null, null, null, null, null, null,
					null, null, null);
			planteRepository.save(melon);

			Genre tanacetum = new Genre("tanacetum");
			genreRepository.save(tanacetum);
			Espece tanacetumVulgare = new Espece("tanacetum vulgare");
			especeRepository.save(tanacetumVulgare);
			ClassificationCronquist tanacetumVulgareC = new ClassificationCronquist(asterales, asteraceae, tanacetum,
					tanacetumVulgare);
			classificationCronquistRepository.save(tanacetumVulgareC);
			Plante tanaisie = new Plante("tanaisie", null, null, null, null, tanacetumVulgareC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(tanaisie);

			Genre prunus = new Genre("prunus");
			genreRepository.save(prunus);
			Espece prunusPersica = new Espece("prunus persica");
			especeRepository.save(prunusPersica);
			ClassificationCronquist prunusPersicaC = new ClassificationCronquist(rosales, rosaceae, prunus,
					prunusPersica);
			classificationCronquistRepository.save(prunusPersicaC);
			Plante pecher = new Plante("pêcher", null, null, null, null, prunusPersicaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(pecher);

			Genre pyrus = new Genre("pyrus");
			genreRepository.save(pyrus);
			Espece pyrusCommunis = new Espece("pyrus communis");
			especeRepository.save(pyrusCommunis);
			ClassificationCronquist pyrusCommunisC = new ClassificationCronquist(rosales, rosaceae, pyrus,
					pyrusCommunis);
			classificationCronquistRepository.save(pyrusCommunisC);
			Plante poirier = new Plante("poirier", null, null, null, null, pyrusCommunisC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(poirier);

			Genre malus = new Genre("malus");
			genreRepository.save(malus);
			Espece malusDomestica = new Espece("malus domestica");
			especeRepository.save(malusDomestica);
			ClassificationCronquist malusDomesticaC = new ClassificationCronquist(rosales, rosaceae, malus,
					malusDomestica);
			classificationCronquistRepository.save(malusDomesticaC);
			Plante pommier = new Plante("pommier", null, null, null, null, malusDomesticaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(pommier);

			Ordre fagales = new Ordre("fagales");
			ordreRepository.save(fagales);
			Famille betulaceae = new Famille("betulaceae");
			familleRepository.save(betulaceae);
			Genre betula = new Genre("betula");
			genreRepository.save(betula);
			Espece betulaPendula = new Espece("betula pendula");
			especeRepository.save(betulaPendula);
			ClassificationCronquist betulaPendulaC = new ClassificationCronquist(fagales, betulaceae, betula,
					betulaPendula);
			classificationCronquistRepository.save(betulaPendulaC);
			Plante bouleau = new Plante("bouleau", null, null, null, null, betulaPendulaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(bouleau);

			Famille geraniaceae = new Famille("geraniaceae");
			familleRepository.save(geraniaceae);
			Genre geranium = new Genre("geranium");
			genreRepository.save(geranium);
			Espece geraniumPratense = new Espece("geranium pratense");
			especeRepository.save(geraniumPratense);
			ClassificationCronquist geraniumPratenseC = new ClassificationCronquist(geraniales, geraniaceae, geranium,
					geraniumPratense);
			classificationCronquistRepository.save(betulaPendulaC);
			Plante geraniumDesPres = new Plante("géranium des prés", null, null, null, null, geraniumPratenseC, null,
					null, null, null, null, null, null, null, null);
			planteRepository.save(geraniumDesPres);

			Genre hyssopus = new Genre("hyssopus");
			genreRepository.save(hyssopus);
			Espece hyssopusOfficinalis = new Espece("hyssopus officinalis");
			especeRepository.save(hyssopusOfficinalis);
			ClassificationCronquist hyssopusOfficinalisC = new ClassificationCronquist(lamiales, lamiaceae, hyssopus,
					hyssopusOfficinalis);
			classificationCronquistRepository.save(betulaPendulaC);
			Plante hysope = new Plante("hysope", null, null, null, null, hyssopusOfficinalisC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(hysope);

			Genre apium = new Genre("apium");
			genreRepository.save(apium);
			Espece apiumGraveolens = new Espece("apium graveolens");
			especeRepository.save(apiumGraveolens);
			ClassificationCronquist apiumGraveolensC = new ClassificationCronquist(apiales, apiaceae, apium,
					apiumGraveolens);
			classificationCronquistRepository.save(apiumGraveolensC);
			Plante celeri = new Plante("celeri", null, null, null, null, apiumGraveolensC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(celeri);

			Genre nepeta = new Genre("nepeta");
			genreRepository.save(nepeta);
			Espece nepetaCataria = new Espece("nepeta cataria");
			especeRepository.save(nepetaCataria);
			ClassificationCronquist nepetaCatariaC = new ClassificationCronquist(lamiales, lamiaceae, nepeta,
					nepetaCataria);
			classificationCronquistRepository.save(nepetaCatariaC);
			Plante cataire = new Plante("cataire", null, null, null, null, nepetaCatariaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(cataire);

			Genre capsicum = new Genre("capsicum");
			genreRepository.save(capsicum);
			Espece capsicumAnnuum = new Espece("capsicum annuum");
			especeRepository.save(capsicumAnnuum);
			ClassificationCronquist capsicumAnnuumC = new ClassificationCronquist(solanales, solanaceae, capsicum,
					capsicumAnnuum);
			classificationCronquistRepository.save(capsicumAnnuumC);
			Plante poivron = new Plante("poivron", null, null, null, null, capsicumAnnuumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(poivron);
			Plante piment = new Plante("piment", null, null, null, null, capsicumAnnuumC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(piment);

			Genre cynara = new Genre("cynara");
			genreRepository.save(cynara);
			Espece cynaraCardunculus = new Espece("cynara cardunculus");
			especeRepository.save(cynaraCardunculus);
			ClassificationCronquist cynaraCardunculusC = new ClassificationCronquist(asterales, asteraceae, cynara,
					cynaraCardunculus);
			classificationCronquistRepository.save(cynaraCardunculusC);
			Plante artichaut = new Plante("artichaut", null, null, null, null, cynaraCardunculusC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(artichaut);

			Genre scorzonera = new Genre("scorzonera");
			genreRepository.save(scorzonera);
			Espece ScorzoneraHumilis = new Espece("Scorzonera humilis");
			especeRepository.save(ScorzoneraHumilis);
			ClassificationCronquist ScorzoneraHumilisC = new ClassificationCronquist(asterales, asteraceae, scorzonera,
					ScorzoneraHumilis);
			classificationCronquistRepository.save(ScorzoneraHumilisC);
			Plante scorsonere = new Plante("scorsonère des prés", null, null, null, null, ScorzoneraHumilisC, null,
					null, null, null, null, null, null, null, null);
			planteRepository.save(scorsonere);

			Genre lens = new Genre("lens");
			genreRepository.save(lens);
			Espece lensCulinaris = new Espece("lens culinaris");
			especeRepository.save(lensCulinaris);
			ClassificationCronquist lensCulinarisC = new ClassificationCronquist(fabales, fabaceae, lens,
					lensCulinaris);
			classificationCronquistRepository.save(lensCulinarisC);
			Plante lentille = new Plante("lentille", null, null, null, null, lensCulinarisC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(lentille);

			Genre carum = new Genre("carum");
			genreRepository.save(carum);
			Espece carumCarvi = new Espece("carumCarvi");
			especeRepository.save(carumCarvi);
			ClassificationCronquist carumCarviC = new ClassificationCronquist(apiales, apiaceae, carum, carumCarvi);
			classificationCronquistRepository.save(carumCarviC);
			Plante carvi = new Plante("carvi", null, null, null, null, carumCarviC, null, null, null, null, null, null,
					null, null, null);
			planteRepository.save(carvi);

			Genre pimpinella = new Genre("pimpinella");
			genreRepository.save(pimpinella);
			Espece pimpinellaAnisum = new Espece("pimpinella anisum");
			especeRepository.save(pimpinellaAnisum);
			ClassificationCronquist pimpinellaAnisumC = new ClassificationCronquist(apiales, apiaceae, pimpinella,
					pimpinellaAnisum);
			classificationCronquistRepository.save(pimpinellaAnisumC);
			Plante anis = new Plante("anis vert", null, null, null, null, pimpinellaAnisumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(anis);

			Genre foeniculum = new Genre("foeniculum");
			genreRepository.save(foeniculum);
			Espece FoeniculumVulgare = new Espece("FoeniculumVulgare");
			especeRepository.save(FoeniculumVulgare);
			ClassificationCronquist FoeniculumVulgareC = new ClassificationCronquist(apiales, apiaceae, foeniculum,
					FoeniculumVulgare);
			classificationCronquistRepository.save(FoeniculumVulgareC);
			Plante fenouil = new Plante("fenouil", null, null, null, null, FoeniculumVulgareC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(fenouil);

			Genre artemisia = new Genre("artemisia");
			genreRepository.save(artemisia);
			Espece artemisiaAbsinthium = new Espece("artemisiaAbsinthium");
			especeRepository.save(artemisiaAbsinthium);
			ClassificationCronquist artemisiaAbsinthiumC = new ClassificationCronquist(asterales, asteraceae, artemisia,
					artemisiaAbsinthium);
			classificationCronquistRepository.save(artemisiaAbsinthiumC);
			Plante absinthe = new Plante("absinthe", null, null, null, null, artemisiaAbsinthiumC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(absinthe);

			Ordre sapindales = new Ordre("sapindales");
			ordreRepository.save(sapindales);
			Famille rutaceae = new Famille("rutaceae");
			familleRepository.save(rutaceae);
			Genre ruta = new Genre("ruta");
			genreRepository.save(ruta);
			Espece rutaGraveolens = new Espece("ruta graveolens");
			especeRepository.save(rutaGraveolens);
			ClassificationCronquist rutaGraveolensC = new ClassificationCronquist(sapindales, rutaceae, ruta,
					rutaGraveolens);
			classificationCronquistRepository.save(rutaGraveolensC);
			Plante rueOfficinale = new Plante("Rue officinale", null, null, null, null, rutaGraveolensC, null, null,
					null, null, null, null, null, null, null);
			planteRepository.save(rueOfficinale);

			Ordre juglandales = new Ordre("juglandales");
			ordreRepository.save(juglandales);
			Famille juglandaceae = new Famille("juglandaceae");
			familleRepository.save(juglandaceae);
			Genre juglans = new Genre("juglans");
			genreRepository.save(juglans);
			Espece juglansRegia = new Espece("juglans regia");
			especeRepository.save(juglansRegia);
			ClassificationCronquist juglansRegiaC = new ClassificationCronquist(juglandales, juglandaceae, juglans,
					juglansRegia);
			classificationCronquistRepository.save(juglansRegiaC);
			Plante noyer = new Plante("noyer", null, null, null, null, juglansRegiaC, null, null, null, null, null,
					null, null, null, null);
			planteRepository.save(noyer);

			Genre lepidium = new Genre("lepidium");
			genreRepository.save(lepidium);
			Espece lepidiumSativum = new Espece("lepidium sativum");
			especeRepository.save(lepidiumSativum);
			ClassificationCronquist lepidiumSativumC = new ClassificationCronquist(capparales, brassicaceae, lepidium,
					lepidiumSativum);
			classificationCronquistRepository.save(lepidiumSativumC);
			Plante cresson = new Plante("cresson", null, null, null, null, lepidiumSativumC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(cresson);

			Genre anthriscus = new Genre("anthriscus");
			genreRepository.save(anthriscus);
			Espece anthriscusCerefolium = new Espece("anthriscus cerefolium");
			especeRepository.save(anthriscusCerefolium);
			ClassificationCronquist anthriscusCerefoliumC = new ClassificationCronquist(apiales, apiaceae, anthriscus,
					anthriscusCerefolium);
			classificationCronquistRepository.save(anthriscusCerefoliumC);
			Plante cerfeuil = new Plante("cerfeuil", null, null, null, null, anthriscusCerefoliumC, null, null, null,
					null, null, null, null, null, null);
			planteRepository.save(cerfeuil);

			Genre atriplex = new Genre("atriplex");
			genreRepository.save(atriplex);
			Espece atriplexHortensis = new Espece("atriplexHortensis");
			especeRepository.save(atriplexHortensis);
			ClassificationCronquist atriplexHortensisC = new ClassificationCronquist(caryophyllales, chenopodiaceae,
					atriplex, atriplexHortensis);
			classificationCronquistRepository.save(atriplexHortensisC);
			Plante arrocheDesJardins = new Plante("arroche des jardins", null, null, null, null, atriplexHortensisC,
					null, null, null, null, null, null, null, null, null);
			planteRepository.save(arrocheDesJardins);

			Genre armoracia = new Genre("armoracia");
			genreRepository.save(armoracia);
			Espece armoraciaRusticana = new Espece("armoraciaRusticana");
			especeRepository.save(armoraciaRusticana);
			ClassificationCronquist armoraciaRusticanaC = new ClassificationCronquist(capparales, brassicaceae,
					armoracia, armoraciaRusticana);
			classificationCronquistRepository.save(armoraciaRusticanaC);
			Plante raifort = new Plante("raifort", null, null, null, null, armoraciaRusticanaC, null, null, null, null,
					null, null, null, null, null);
			planteRepository.save(raifort);

			// Pour carotte
			InteractionPlantePlante interactionPourCarotte1 = new InteractionPlantePlante("+1", "", null, ail, carotte);
			InteractionPlantePlante interactionPourCarotte2 = new InteractionPlantePlante("+1",
					"L’aneth protège les carottes et aide à la levée", null, aneth, carotte);
			InteractionPlantePlante interactionPourCarotte3 = new InteractionPlantePlante("+1", "", null, echalotte,
					carotte);
			InteractionPlantePlante interactionPourCarotte4 = new InteractionPlantePlante("+1", "", null, poireau,
					carotte);
			InteractionPlantePlante interactionPourCarotte5 = new InteractionPlantePlante("+1", "", null, tomate,
					carotte);
			InteractionPlantePlante interactionPourCarotte6 = new InteractionPlantePlante("+1", "", null, laitue,
					carotte);
			InteractionPlantePlante interactionPourCarotte7 = new InteractionPlantePlante("+1", "", null, ciboulette,
					carotte);
			InteractionPlantePlante interactionPourCarotte8 = new InteractionPlantePlante("+1", "", null, radis,
					carotte);
			InteractionPlantePlante interactionPourCarotte9 = new InteractionPlantePlante("+1",
					"L'oignon repousse la mouche de la carotte", null, oignon, carotte);
			InteractionPlantePlante interactionPourCarotte10 = new InteractionPlantePlante("+1", "", null, pois,
					carotte);
			InteractionPlantePlante interactionPourCarotte11 = new InteractionPlantePlante("+1",
					"Le poireau repousse la mouche de la carotte", null, poireau, carotte);
			InteractionPlantePlante interactionPourCarotte12 = new InteractionPlantePlante("+1", "", null, haricot,
					carotte);
			InteractionPlantePlante interactionPourCarotte13 = new InteractionPlantePlante("+1", "", null, coriandre,
					carotte);
			InteractionPlantePlante interactionPourCarotte14 = new InteractionPlantePlante("+1", "", null, romarin,
					carotte);
			InteractionPlantePlante interactionPourCarotte15 = new InteractionPlantePlante("+1", "", null, scorsonere,
					carotte);

			// Pour chouCommun
			InteractionPlantePlante interactionPourChouCommun1 = new InteractionPlantePlante("+1", "", null, sarriette,
					chouCommun);
			InteractionPlantePlante interactionPourChouCommun2 = new InteractionPlantePlante("+1", "", null, betterave,
					chouCommun);
			InteractionPlantePlante interactionPourChouCommun3 = new InteractionPlantePlante("+1",
					"La piéride du chou est repoussée par la tomate", null, tomate, chouCommun);
			InteractionPlantePlante interactionPourChouCommun4 = new InteractionPlantePlante("+1", "", null, romarin,
					chouCommun);
			InteractionPlantePlante interactionPourChouCommun5 = new InteractionPlantePlante("+1",
					"La menthe protège les choux des papillons", null, menthePoivree, chouCommun);
			InteractionPlantePlante interactionPourChouCommun6 = new InteractionPlantePlante("+1",
					"La sauge protège les choux des papillons", null, sauge, chouCommun);
			InteractionPlantePlante interactionPourChouCommun7 = new InteractionPlantePlante("+1", "", null, thymCommun,
					chouCommun);
			InteractionPlantePlante interactionPourChouCommun8 = new InteractionPlantePlante("+1", "", null,
					grandeCapucine, chouCommun);
			InteractionPlantePlante interactionPourChouCommun9 = new InteractionPlantePlante("+1", "", null,
					pommeDeTerre, chouCommun);
			InteractionPlantePlante interactionPourChouCommun10 = new InteractionPlantePlante("+1",
					"L'hysope empêche les mouches blanche de pondre dans les choux", null, hysope, chouCommun);
			InteractionPlantePlante interactionPourChouCommun11 = new InteractionPlantePlante("+1",
					"La piéride du chou est repoussée par le céleri", null, celeri, chouCommun);
			InteractionPlantePlante interactionPourChouCommun12 = new InteractionPlantePlante("+1", "", null, bourrache,
					chouCommun);
			InteractionPlantePlante interactionPourChouCommun13 = new InteractionPlantePlante("+1", "", null,
					oeilletsDInde, chouCommun);
			InteractionPlantePlante interactionPourChouCommun14 = new InteractionPlantePlante("+1",
					"La piéride du chou n'aime pas l'odeur de la tomate. "
							+ "L'effet protecteur est renforcé lorsqu'on met entre les plantes menacées, les gourmands des tomates.",
					null, tomate, chouCommun);
			InteractionPlantePlante interactionPourChouCommun15 = new InteractionPlantePlante("+1",
					"La piéride du chou n'aime pas l'odeur du céleri", null, celeri, chouCommun);

			// Pour haricot
			InteractionPlantePlante interactionPourHaricot1 = new InteractionPlantePlante("+1", "", null, concombre,
					haricot);
			InteractionPlantePlante interactionPourHaricot2 = new InteractionPlantePlante("+1", "", null, laitue,
					haricot);
			InteractionPlantePlante interactionPourHaricot3 = new InteractionPlantePlante("+1", "", null, tomate,
					haricot);
			InteractionPlantePlante interactionPourHaricot4 = new InteractionPlantePlante("+1", "", null, carotte,
					haricot);
			InteractionPlantePlante interactionPourHaricot5 = new InteractionPlantePlante("+1", "", null, aubergine,
					haricot);
			InteractionPlantePlante interactionPourHaricot6 = new InteractionPlantePlante("+1", "", null, oeilletsDInde,
					haricot);
			InteractionPlantePlante interactionPourHaricot7 = new InteractionPlantePlante("+1", "", null, souci,
					haricot);
			InteractionPlantePlante interactionPourHaricot8 = new InteractionPlantePlante("+1", "", null, betterave,
					haricot);
			InteractionPlantePlante interactionPourHaricot9 = new InteractionPlantePlante("+1", "", null, mais,
					haricot);
			InteractionPlantePlante interactionPourHaricot10 = new InteractionPlantePlante("+1", "", null, pommeDeTerre,
					haricot);

			// Pour concombre
			InteractionPlantePlante interactionPourConcombre1 = new InteractionPlantePlante("+1", "", null, basilic,
					concombre);
			InteractionPlantePlante interactionPourConcombre2 = new InteractionPlantePlante("+1", "", null, chouCommun,
					concombre);
			InteractionPlantePlante interactionPourConcombre3 = new InteractionPlantePlante("+1", "", null, haricot,
					concombre);
			InteractionPlantePlante interactionPourConcombre4 = new InteractionPlantePlante("+1", "", null, laitue,
					concombre);
			InteractionPlantePlante interactionPourConcombre5 = new InteractionPlantePlante("+1", "", null, mais,
					concombre);
			InteractionPlantePlante interactionPourConcombre6 = new InteractionPlantePlante("+1", "", null, tournesol,
					concombre);
			InteractionPlantePlante interactionPourConcombre7 = new InteractionPlantePlante("+1", "", null,
					pommeDeTerre, concombre);
			InteractionPlantePlante interactionPourConcombre8 = new InteractionPlantePlante("+1", "", null, radis,
					concombre);
			InteractionPlantePlante interactionPourConcombre9 = new InteractionPlantePlante("+1", "", null, origan,
					concombre);
			InteractionPlantePlante interactionPourConcombre10 = new InteractionPlantePlante("+1", "", null, pois,
					concombre);
			InteractionPlantePlante interactionPourConcombre11 = new InteractionPlantePlante("+1",
					"L’aneth protège les concombres", null, aneth, concombre);

			// Pour pommeDeTerre
			InteractionPlantePlante interactionPourPommeDeTerre1 = new InteractionPlantePlante("+1", "", null,
					chouCommun, pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre2 = new InteractionPlantePlante("+1", "", null, feve,
					pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre3 = new InteractionPlantePlante("+1",
					"Le doryphore de la pomme de terre est repoussé par le pois", null, pois, pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre4 = new InteractionPlantePlante("+1", "", null, courge,
					pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre5 = new InteractionPlantePlante("+1", "", null, potiron,
					pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre6 = new InteractionPlantePlante("+1", "", null, haricot,
					pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre7 = new InteractionPlantePlante("+1", "", null,
					oeilletsDInde, pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre8 = new InteractionPlantePlante("+1", "", null, souci,
					pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre9 = new InteractionPlantePlante("+1", "", null,
					concombre, pommeDeTerre);
			InteractionPlantePlante interactionPourPommeDeTerre10 = new InteractionPlantePlante("+1", "", null,
					bourrache, pommeDeTerre);

			// Pour tomate
			InteractionPlantePlante interactionPourTomate1 = new InteractionPlantePlante("+1",
					"Le persil rend la tomate plus résistante aux maladies", null, persil, tomate);
			InteractionPlantePlante interactionPourTomate2 = new InteractionPlantePlante("+1", "", null, oignon,
					tomate);
			InteractionPlantePlante interactionPourTomate3 = new InteractionPlantePlante("+1", "", null, poireau,
					tomate);
			InteractionPlantePlante interactionPourTomate4 = new InteractionPlantePlante("+1", "", null, oeilletsDInde,
					tomate);
			InteractionPlantePlante interactionPourTomate5 = new InteractionPlantePlante("+1", "", null, carotte,
					tomate);
			InteractionPlantePlante interactionPourTomate6 = new InteractionPlantePlante("+1",
					"Le basilic rend la tomate plus résistante aux maladies", null, basilic, tomate);
			InteractionPlantePlante interactionPourTomate7 = new InteractionPlantePlante("+1", "", null, asperge,
					tomate);
			InteractionPlantePlante interactionPourTomate8 = new InteractionPlantePlante("+1", "", null, souci, tomate);
			InteractionPlantePlante interactionPourTomate9 = new InteractionPlantePlante("+1", "", null, bourrache,
					tomate);
			InteractionPlantePlante interactionPourTomate10 = new InteractionPlantePlante("+1", "", null,
					grandeCapucine, tomate);

			// Pour asperge
			InteractionPlantePlante interactionPourAsperge1 = new InteractionPlantePlante("+1", "", null, tomate,
					asperge);
			InteractionPlantePlante interactionPourAsperge2 = new InteractionPlantePlante("+1", "", null, persil,
					asperge);
			InteractionPlantePlante interactionPourAsperge3 = new InteractionPlantePlante("+1", "", null, basilic,
					asperge);
			InteractionPlantePlante interactionPourAsperge4 = new InteractionPlantePlante("+1", "", null, haricot,
					asperge);

			// Pour betterave
			InteractionPlantePlante interactionPourBetterave1 = new InteractionPlantePlante("+1", "", null, coriandre,
					betterave);
			InteractionPlantePlante interactionPourBetterave2 = new InteractionPlantePlante("+1", "", null, chouCommun,
					betterave);
			InteractionPlantePlante interactionPourBetterave3 = new InteractionPlantePlante("+1", "", null, haricot,
					betterave);
			InteractionPlantePlante interactionPourBetterave4 = new InteractionPlantePlante("+1", "", null, oignon,
					betterave);
			InteractionPlantePlante interactionPourBetterave5 = new InteractionPlantePlante("+1", "", null, laitue,
					betterave);
			InteractionPlantePlante interactionPourBetterave6 = new InteractionPlantePlante("+1", "", null, fraisier,
					betterave);
			InteractionPlantePlante interactionPourBetterave7 = new InteractionPlantePlante("+1", "", null, chouRave,
					betterave);
			InteractionPlantePlante interactionPourBetterave8 = new InteractionPlantePlante("+1", "", null, concombre,
					betterave);

			// Pour framboisier
			InteractionPlantePlante interactionPourFramboisier1 = new InteractionPlantePlante("+1",
					"Le vers de framboisier est repoussé par la myosotis. Il empêche le vers de proliférer", null,
					myosotisDesChamps, framboisier);

			// Pour aubergine
			InteractionPlantePlante interactionPourAubergine1 = new InteractionPlantePlante("+1", "", null, haricot,
					aubergine);
			InteractionPlantePlante interactionPourAubergine2 = new InteractionPlantePlante("+1", "", null, basilic,
					aubergine);

			// Pour courge
			InteractionPlantePlante interactionPourCourge1 = new InteractionPlantePlante("+1", "", null, grandeCapucine,
					courge);
			InteractionPlantePlante interactionPourCourge2 = new InteractionPlantePlante("+1", "", null, pommeDeTerre,
					courge);
			InteractionPlantePlante interactionPourCourge3 = new InteractionPlantePlante("+1", "", null, epinard,
					courge);
			InteractionPlantePlante interactionPourCourge4 = new InteractionPlantePlante("+1", "", null, fraisier,
					courge);
			InteractionPlantePlante interactionPourCourge5 = new InteractionPlantePlante("+1", "", null, mais, courge);
			InteractionPlantePlante interactionPourCourge6 = new InteractionPlantePlante("+1", "", null, radis, courge);
			InteractionPlantePlante interactionPourCourge7 = new InteractionPlantePlante("+1", "", null, bourrache,
					courge);
			InteractionPlantePlante interactionPourCourge8 = new InteractionPlantePlante("+1",
					"La capucine éloigne les punaises des courgettes et citrouilles", null, grandeCapucine, courge);

			// Pour potiron
			InteractionPlantePlante interactionPourPotiron1 = new InteractionPlantePlante("+1", "", null,
					grandeCapucine, potiron);
			InteractionPlantePlante interactionPourPotiron2 = new InteractionPlantePlante("+1", "", null, pommeDeTerre,
					potiron);
			InteractionPlantePlante interactionPourPotiron3 = new InteractionPlantePlante("+1", "", null, epinard,
					potiron);
			InteractionPlantePlante interactionPourPotiron4 = new InteractionPlantePlante("+1", "", null, fraisier,
					potiron);
			InteractionPlantePlante interactionPourPotiron5 = new InteractionPlantePlante("+1", "", null, mais,
					potiron);
			InteractionPlantePlante interactionPourPotiron6 = new InteractionPlantePlante("+1", "", null, radis,
					potiron);

			// Pour fraisier
			InteractionPlantePlante interactionPourFraisier1 = new InteractionPlantePlante("+1", "", null, oignon,
					fraisier);
			InteractionPlantePlante interactionPourFraisier2 = new InteractionPlantePlante("+1", "", null, bourrache,
					fraisier);
			InteractionPlantePlante interactionPourFraisier3 = new InteractionPlantePlante("+1", "", null, epinard,
					fraisier);
			InteractionPlantePlante interactionPourFraisier4 = new InteractionPlantePlante("+1", "", null, laitue,
					fraisier);

			// Pour laitue
			InteractionPlantePlante interactionPourLaitue1 = new InteractionPlantePlante("+1", "", null, fraisier,
					laitue);
			InteractionPlantePlante interactionPourLaitue2 = new InteractionPlantePlante("+1", "", null, carotte,
					laitue);
			InteractionPlantePlante interactionPourLaitue3 = new InteractionPlantePlante("+1", "", null, radis, laitue);

			// Pour mais
			InteractionPlantePlante interactionPourMais1 = new InteractionPlantePlante("+1", "", null, haricot, mais);
			InteractionPlantePlante interactionPourMais2 = new InteractionPlantePlante("+1", "", null, pois, mais);
			InteractionPlantePlante interactionPourMais3 = new InteractionPlantePlante("+1", "", null, concombre, mais);
			InteractionPlantePlante interactionPourMais4 = new InteractionPlantePlante("+1", "", null, courge, mais);
			InteractionPlantePlante interactionPourMais5 = new InteractionPlantePlante("+1", "", null, potiron, mais);

			// Pour melon
			InteractionPlantePlante interactionPourMelon1 = new InteractionPlantePlante("+1", "", null, origan, melon);

			// Pour oignon
			InteractionPlantePlante interactionPourOignon1 = new InteractionPlantePlante("+1",
					"La mouche de l’oignon est repoussée par les carottes. Leur odeur repousse les mouches.", null,
					carotte, oignon);
			InteractionPlantePlante interactionPourOignon2 = new InteractionPlantePlante("+1", "", null, fraisier,
					oignon);

			// Pour poireau
			InteractionPlantePlante interactionPourPoireau1 = new InteractionPlantePlante("+1",
					"Les carottes contribues à la lutte contre la teigne du poireau", null, carotte, poireau);

			// Pour pecher
			InteractionPlantePlante interactionPourPecher1 = new InteractionPlantePlante("+1", "", null, tanaisie,
					pecher);
			InteractionPlantePlante interactionPourPecher2 = new InteractionPlantePlante("+1",
					"L'ail planté aux pieds des pêchers pour protéger de la cloque", null, ail, pecher);
			InteractionPlantePlante interactionPourPecher3 = new InteractionPlantePlante("+1",
					"L'oignon planté aux pieds des pêchers pour protéger de la cloque", null, oignon, pecher);

			// Pour poirier
			InteractionPlantePlante interactionPourPoirier1 = new InteractionPlantePlante("+1", "", null, sauge,
					poirier);

			// Pour pommier
			InteractionPlantePlante interactionPourPommier1 = new InteractionPlantePlante("+1",
					"La ciboulette se plante près des pommiers pour prévenir de la tavelure, de la gale et des chancres",
					null, ciboulette, pommier);
			InteractionPlantePlante interactionPourPommier2 = new InteractionPlantePlante("+1",
					"Plantée au pied des pommiers, elle prévient contre le puceron lanigère", null, grandeCapucine,
					pommier);

			// Pour pois
			InteractionPlantePlante interactionPourPois1 = new InteractionPlantePlante("+1", "", null, radis, pois);
			InteractionPlantePlante interactionPourPois2 = new InteractionPlantePlante("+1", "", null, carotte, pois);
			InteractionPlantePlante interactionPourPois3 = new InteractionPlantePlante("+1", "", null, concombre, pois);
			InteractionPlantePlante interactionPourPois4 = new InteractionPlantePlante("+1", "", null, mais, pois);
			InteractionPlantePlante interactionPourPois5 = new InteractionPlantePlante("+1", "", null, pommeDeTerre,
					pois);

			// Pour radis
			InteractionPlantePlante interactionPourRadis1 = new InteractionPlantePlante("+1", "", null, epinard, radis);
			InteractionPlantePlante interactionPourRadis2 = new InteractionPlantePlante("+1", "", null, menthePoivree,
					radis);
			InteractionPlantePlante interactionPourRadis3 = new InteractionPlantePlante("+1", "", null, concombre,
					radis);
			InteractionPlantePlante interactionPourRadis4 = new InteractionPlantePlante("+1", "", null, grandeCapucine,
					radis);
			InteractionPlantePlante interactionPourRadis5 = new InteractionPlantePlante("+1", "", null, carotte, radis);
			InteractionPlantePlante interactionPourRadis6 = new InteractionPlantePlante("+1", "", null, laitue, radis);
			InteractionPlantePlante interactionPourRadis7 = new InteractionPlantePlante("+1", "", null, pois, radis);

			// Pour rosiers
			InteractionPlantePlante interactionPourRosier1 = new InteractionPlantePlante("+1",
					"Les géraniums protègent les rosiers", null, geraniumDesPres, rosierDeFrance);
			InteractionPlantePlante interactionPourRosier2 = new InteractionPlantePlante("+1",
					"L'ail planté au pied des rosiersles rends plus beaux et résistants", null, ail, rosierDeFrance);
			InteractionPlantePlante interactionPourRosier3 = new InteractionPlantePlante("+1",
					"Les pucerons noirs des rosiers sont repoussés par la menthe verte ou poivrée", null, menthePoivree,
					rosierDeFrance);

			// Pour piment
			InteractionPlantePlante interactionPourPiment1 = new InteractionPlantePlante("+1",
					"Le basilic s'associe parfaitement avec le genre capsicum", null, basilic, piment);

			// Pour poivron
			InteractionPlantePlante interactionPourPoivron1 = new InteractionPlantePlante("+1",
					"Le basilic s'associe parfaitement avec le genre capsicum", null, basilic, poivron);

			// Pour artichaut
			InteractionPlantePlante interactionPourArtichaut1 = new InteractionPlantePlante("+1", "", null, feve,
					artichaut);

			// Pour bette
			InteractionPlantePlante interactionPourBette1 = new InteractionPlantePlante("+1", "", null, radis, bette);
			InteractionPlantePlante interactionPourBette2 = new InteractionPlantePlante("+1", "", null, carotte, bette);
			InteractionPlantePlante interactionPourBette3 = new InteractionPlantePlante("+1", "", null, haricot, bette);
			InteractionPlantePlante interactionPourBette4 = new InteractionPlantePlante("+1", "", null, raifort, bette);

			// Pour celeri
			InteractionPlantePlante interactionPourCeleri1 = new InteractionPlantePlante("+1", "", null, poireau,
					celeri);
			InteractionPlantePlante interactionPourCeleri2 = new InteractionPlantePlante("+1", "", null, tomate,
					celeri);
			InteractionPlantePlante interactionPourCeleri3 = new InteractionPlantePlante("+1",
					"Le céleri cultivé seul n'utilise qu'une partie des substances nutritives trouvées dans le sol. Quand on le plante avec du chou-fleur, "
							+ "il les utilise mieux, de même que ce dernier. La récolte est alors meilleure pour les deux plantes.\r\n",
					null, chouFleur, celeri);
			InteractionPlantePlante interactionPourCeleri4 = new InteractionPlantePlante("+1", "", null, epinard,
					celeri);
			InteractionPlantePlante interactionPourCeleri5 = new InteractionPlantePlante("+1", "", null, concombre,
					celeri);
			InteractionPlantePlante interactionPourCeleri6 = new InteractionPlantePlante("+1", "", null, haricot,
					celeri);

			// Contre chouFleur
			InteractionPlantePlante interactionContreChouFleur1 = new InteractionPlantePlante("-1",
					"Le chou fleur utilise mieux les nutriments présents dans le sols lorsqu'il accompagné du céleri, de même pour ce dernier.",
					null, celeri, chouFleur);

			// Contre ail
			InteractionPlantePlante interactionContreAil1 = new InteractionPlantePlante("-1", "", null, feve, ail);
			InteractionPlantePlante interactionContreAil2 = new InteractionPlantePlante("-1", "", null, lentille, ail);
			InteractionPlantePlante interactionContreAil3 = new InteractionPlantePlante("-1", "", null, haricot, ail);
			InteractionPlantePlante interactionContreAil4 = new InteractionPlantePlante("-1", "", null, pois, ail);

			// Contre carotte
			InteractionPlantePlante interactionContreCarotte1 = new InteractionPlantePlante("-1",
					"En compagnie d'aneth la carotte est sensible aux maladies et aux ravageurs et au mieux végètera. "
							+ "Elle ne poussent pas là où auparavant il y en avait déjà",
					null, aneth, carotte);

			// Contre absinthe
			InteractionPlantePlante interactionContreAbsinthe1 = new InteractionPlantePlante("-1", "", null, carvi,
					absinthe);
			InteractionPlantePlante interactionContreAbsinthe2 = new InteractionPlantePlante("-1", "", null, sauge,
					absinthe);
			InteractionPlantePlante interactionContreAbsinthe3 = new InteractionPlantePlante("-1", "", null, anis,
					absinthe);
			InteractionPlantePlante interactionContreAbsinthe4 = new InteractionPlantePlante("-1", "", null, fenouil,
					absinthe);

			// Contre artichaut
			InteractionPlantePlante interactionContreArtichaut1 = new InteractionPlantePlante("-1", "", null, feve,
					artichaut);

			// Contre basilic
			InteractionPlantePlante interactionContreBasilic1 = new InteractionPlantePlante("-1", "", null,
					rueOfficinale, basilic);

			// Contre betterave
			InteractionPlantePlante interactionContreBetterave1 = new InteractionPlantePlante("-1", "", null, haricot,
					betterave);
			InteractionPlantePlante interactionContreBetterave2 = new InteractionPlantePlante("-1", "", null, epinard,
					betterave);

			// Contre radis
			InteractionPlantePlante interactionContreRadis1 = new InteractionPlantePlante("-1", "", null, cerfeuil,
					radis);

			// Contre chou
			InteractionPlantePlante interactionContreChou1 = new InteractionPlantePlante("-1", "", null, fraisier,
					chouCommun);
			InteractionPlantePlante interactionContreChou2 = new InteractionPlantePlante("-1", "", null, oignon,
					chouCommun);
			InteractionPlantePlante interactionContreChou3 = new InteractionPlantePlante("-1", "", null, tomate,
					chouCommun);

			// Contre concombre
			InteractionPlantePlante interactionContreConcombre1 = new InteractionPlantePlante("-1", "", null,
					pommeDeTerre, concombre);
			InteractionPlantePlante interactionContreConcombre2 = new InteractionPlantePlante("-1", "", null, tomate,
					concombre);
			InteractionPlantePlante interactionContreConcombre3 = new InteractionPlantePlante("-1", "", null, courgette,
					concombre);

			// Contre courge
			InteractionPlantePlante interactionContreCourge1 = new InteractionPlantePlante("-1", "", null, pommeDeTerre,
					courge);

			// Contre echalotte
			InteractionPlantePlante interactionContreEchalotte1 = new InteractionPlantePlante("-1", "", null, pois,
					echalotte);
			InteractionPlantePlante interactionContreEchalotte2 = new InteractionPlantePlante("-1", "", null, haricot,
					echalotte);
			InteractionPlantePlante interactionContreEchalotte3 = new InteractionPlantePlante("-1", "", null, feve,
					echalotte);
			InteractionPlantePlante interactionContreEchalotte4 = new InteractionPlantePlante("-1", "", null, lentille,
					echalotte);

			// Contre epinard
			InteractionPlantePlante interactionContreEpinard1 = new InteractionPlantePlante("-1", "", null,
					pommeDeTerre, epinard);
			InteractionPlantePlante interactionContreEpinard2 = new InteractionPlantePlante("-1", "", null, betterave,
					epinard);

			// Contre fenouil
			InteractionPlantePlante interactionContreFenouil1 = new InteractionPlantePlante("-1", "", null, tomate,
					fenouil);
			InteractionPlantePlante interactionContreFenouil2 = new InteractionPlantePlante("-1", "", null, chouRave,
					fenouil);
			InteractionPlantePlante interactionContreFenouil3 = new InteractionPlantePlante("-1", "", null, absinthe,
					fenouil);
			InteractionPlantePlante interactionContreFenouil4 = new InteractionPlantePlante("-1", "", null, carvi,
					fenouil);
			InteractionPlantePlante interactionContreFenouil5 = new InteractionPlantePlante("-1", "", null, haricot,
					fenouil);
			InteractionPlantePlante interactionContreFenouil6 = new InteractionPlantePlante("-1", "", null, pois,
					fenouil);
			InteractionPlantePlante interactionContreFenouil7 = new InteractionPlantePlante("-1", "", null, echalotte,
					fenouil);
			InteractionPlantePlante interactionContreFenouil8 = new InteractionPlantePlante("-1",
					"L'absinthe semble gêner la formation des graines", null, absinthe, fenouil);
			InteractionPlantePlante interactionContreFenouil9 = new InteractionPlantePlante("-1",
					"La coriandre semble gêner la formation des graines", null, coriandre, fenouil);

			// Contre haricot
			InteractionPlantePlante interactionContreHaricot1 = new InteractionPlantePlante("-1", "", null, oignon,
					haricot);
			InteractionPlantePlante interactionContreHaricot2 = new InteractionPlantePlante("-1", "", null, ail,
					haricot);
			InteractionPlantePlante interactionContreHaricot3 = new InteractionPlantePlante("-1", "", null, echalotte,
					haricot);
			InteractionPlantePlante interactionContreHaricot4 = new InteractionPlantePlante("-1", "", null, tomate,
					haricot);
			InteractionPlantePlante interactionContreHaricot5 = new InteractionPlantePlante("-1", "", null, fenouil,
					haricot);
			InteractionPlantePlante interactionContreHaricot6 = new InteractionPlantePlante("-1", "", null, pois,
					haricot);

			// Contre laitue
			InteractionPlantePlante interactionContreLaitue1 = new InteractionPlantePlante("-1", "", null, tournesol,
					laitue);
			InteractionPlantePlante interactionContreLaitue2 = new InteractionPlantePlante("-1", "", null, persil,
					laitue);

			// Contre melon
			InteractionPlantePlante interactionContreMelon1 = new InteractionPlantePlante("-1", "", null, concombre,
					melon);
			InteractionPlantePlante interactionContreMelon2 = new InteractionPlantePlante("-1", "", null, courge,
					melon);

			// Contre oignon
			InteractionPlantePlante interactionContreOignon1 = new InteractionPlantePlante("-1", "", null, pois,
					oignon);
			InteractionPlantePlante interactionContreOignon2 = new InteractionPlantePlante("-1", "", null, haricot,
					oignon);
			InteractionPlantePlante interactionContreOignon3 = new InteractionPlantePlante("-1", "", null, feve,
					oignon);
			InteractionPlantePlante interactionContreOignon4 = new InteractionPlantePlante("-1", "", null, lentille,
					oignon);

			// Contre poireau
			InteractionPlantePlante interactionContrePoireau1 = new InteractionPlantePlante("-1", "", null, brocoli,
					poireau);
			InteractionPlantePlante interactionContrePoireau2 = new InteractionPlantePlante("-1", "", null, haricot,
					poireau);
			InteractionPlantePlante interactionContrePoireau3 = new InteractionPlantePlante("-1", "", null, feve,
					poireau);

			// Contre pois
			InteractionPlantePlante interactionContrePois1 = new InteractionPlantePlante("-1", "", null, ail, pois);
			InteractionPlantePlante interactionContrePois2 = new InteractionPlantePlante("-1", "", null, echalotte,
					pois);
			InteractionPlantePlante interactionContrePois3 = new InteractionPlantePlante("-1", "", null, oignon, pois);
			InteractionPlantePlante interactionContrePois4 = new InteractionPlantePlante("-1", "", null, poireau, pois);
			InteractionPlantePlante interactionContrePois5 = new InteractionPlantePlante("-1", "", null, tomate, pois);

			// Contre pommeDeTerre
			InteractionPlantePlante interactionContrePommeDeTerre1 = new InteractionPlantePlante("-1", "", null, tomate,
					pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre2 = new InteractionPlantePlante("-1", "", null, courge,
					pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre3 = new InteractionPlantePlante("-1", "", null,
					carotte, pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre4 = new InteractionPlantePlante("-1", "", null, oignon,
					pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre5 = new InteractionPlantePlante("-1", "", null,
					framboisier, pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre6 = new InteractionPlantePlante("-1", "", null,
					arrocheDesJardins, pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre7 = new InteractionPlantePlante("-1", "", null,
					tournesol, pommeDeTerre);
			InteractionPlantePlante interactionContrePommeDeTerre8 = new InteractionPlantePlante("-1", "", null, melon,
					pommeDeTerre);

			// Contre tomate
			InteractionPlantePlante interactionContreTomate1 = new InteractionPlantePlante("-1", "", null, haricot,
					tomate);
			InteractionPlantePlante interactionContreTomate2 = new InteractionPlantePlante("-1", "", null, concombre,
					tomate);
			InteractionPlantePlante interactionContreTomate3 = new InteractionPlantePlante("-1", "", null, chouRave,
					tomate);
			InteractionPlantePlante interactionContreTomate4 = new InteractionPlantePlante("-1", "", null, chouCommun,
					tomate);
			InteractionPlantePlante interactionContreTomate5 = new InteractionPlantePlante("-1", "", null, pommeDeTerre,
					tomate);
			InteractionPlantePlante interactionContreTomate6 = new InteractionPlantePlante("-1", "", null, betterave,
					tomate);
			InteractionPlantePlante interactionContreTomate7 = new InteractionPlantePlante("-1", "", null, pois,
					tomate);

			// Contre sauge
			InteractionPlantePlante interactionContreSauge1 = new InteractionPlantePlante("-1", "", null, oignon,
					sauge);

			// Contre persil
			InteractionPlantePlante interactionContrePersil1 = new InteractionPlantePlante("-1", "", null, laitue,
					persil);

			// Contre fraisier
			InteractionPlantePlante interactionContreFraisier1 = new InteractionPlantePlante("-1", "", null, chouCommun,
					fraisier);

			// Contre asperge
			InteractionPlantePlante interactionContreAsperge1 = new InteractionPlantePlante("-1", "", null, tomate,
					asperge);
			InteractionPlantePlante interactionContreAsperge2 = new InteractionPlantePlante("-1", "", null, haricot,
					asperge);
			InteractionPlantePlante interactionContreAsperge3 = new InteractionPlantePlante("-1", "", null, persil,
					asperge);

			// Contre aubergine
			InteractionPlantePlante interactionContreAubergine1 = new InteractionPlantePlante("-1", "", null, haricot,
					aubergine);

		} catch (ConstraintViolationException cve) {
			log.warn("Constraint violation when loading app: " + cve.getConstraintName());
		} catch (Exception e) {
			log.warn("Exception thrown when populate database on initialization: " + e.getMessage());
		}
	}
}
