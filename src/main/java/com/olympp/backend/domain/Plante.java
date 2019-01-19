package com.olympp.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * @author Pierre TAQUET
 */
@ApiModel(description = "@author Pierre TAQUET")
@Entity
@Table(name = "plante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Plante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$")
    @Column(name = "ph_min")
    private String phMin;

    @Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$")
    @Column(name = "ph_max")
    private String phMax;

    @Column(name = "temp_min")
    private Integer tempMin;

    @Column(name = "temp_max")
    private Integer tempMax;

    @Column(name = "common_name")
    private String commonName;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private ClassificationCronquist classificationCronquist;

    @OneToMany(mappedBy = "plante")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Recolte> recoltes = new HashSet<>();
    @OneToMany(mappedBy = "plante")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Floraison> floraisons = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private Strate strate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VitesseCroissance vitesseCroissance;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Ensoleillement ensoleillement;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RichesseSol richesseSol;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TypeTerre typeTerre;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TypeFeuillage typeFeuillage;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TypeRacine typeRacine;

	public Plante(@Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$") String phMin,
			@Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$") String phMax, Integer tempMin, Integer tempMax,
			String commonName, @NotNull ClassificationCronquist classificationCronquist, Set<Recolte> recoltes,
			Set<Floraison> floraisons, Strate strate, VitesseCroissance vitesseCroissance,
			Ensoleillement ensoleillement, RichesseSol richesseSol, TypeTerre typeTerre, TypeFeuillage typeFeuillage,
			TypeRacine typeRacine) {
		super();
		this.phMin = phMin;
		this.phMax = phMax;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.commonName = commonName;
		this.classificationCronquist = classificationCronquist;
		this.recoltes = recoltes;
		this.floraisons = floraisons;
		this.strate = strate;
		this.vitesseCroissance = vitesseCroissance;
		this.ensoleillement = ensoleillement;
		this.richesseSol = richesseSol;
		this.typeTerre = typeTerre;
		this.typeFeuillage = typeFeuillage;
		this.typeRacine = typeRacine;
	}

	public Plante() {
		super();
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhMin() {
        return phMin;
    }

    public Plante phMin(String phMin) {
        this.phMin = phMin;
        return this;
    }

    public void setPhMin(String phMin) {
        this.phMin = phMin;
    }

    public String getPhMax() {
        return phMax;
    }

    public Plante phMax(String phMax) {
        this.phMax = phMax;
        return this;
    }

    public void setPhMax(String phMax) {
        this.phMax = phMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public Plante tempMin(Integer tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public Plante tempMax(Integer tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public String getCommonName() {
        return commonName;
    }

    public Plante commonName(String commonName) {
        this.commonName = commonName;
        return this;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public ClassificationCronquist getClassificationCronquist() {
        return classificationCronquist;
    }

    public Plante classificationCronquist(ClassificationCronquist classificationCronquist) {
        this.classificationCronquist = classificationCronquist;
        return this;
    }

    public void setClassificationCronquist(ClassificationCronquist classificationCronquist) {
        this.classificationCronquist = classificationCronquist;
    }

    public Set<Recolte> getRecoltes() {
        return recoltes;
    }

    public Plante recoltes(Set<Recolte> recoltes) {
        this.recoltes = recoltes;
        return this;
    }

    public Plante addRecolte(Recolte recolte) {
        this.recoltes.add(recolte);
        recolte.setPlante(this);
        return this;
    }

    public Plante removeRecolte(Recolte recolte) {
        this.recoltes.remove(recolte);
        recolte.setPlante(null);
        return this;
    }

    public void setRecoltes(Set<Recolte> recoltes) {
        this.recoltes = recoltes;
    }

    public Set<Floraison> getFloraisons() {
        return floraisons;
    }

    public Plante floraisons(Set<Floraison> floraisons) {
        this.floraisons = floraisons;
        return this;
    }

    public Plante addFloraison(Floraison floraison) {
        this.floraisons.add(floraison);
        floraison.setPlante(this);
        return this;
    }

    public Plante removeFloraison(Floraison floraison) {
        this.floraisons.remove(floraison);
        floraison.setPlante(null);
        return this;
    }

    public void setFloraisons(Set<Floraison> floraisons) {
        this.floraisons = floraisons;
    }

    public Strate getStrate() {
        return strate;
    }

    public Plante strate(Strate strate) {
        this.strate = strate;
        return this;
    }

    public void setStrate(Strate strate) {
        this.strate = strate;
    }

    public VitesseCroissance getVitesseCroissance() {
        return vitesseCroissance;
    }

    public Plante vitesseCroissance(VitesseCroissance vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
        return this;
    }

    public void setVitesseCroissance(VitesseCroissance vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
    }

    public Ensoleillement getEnsoleillement() {
        return ensoleillement;
    }

    public Plante ensoleillement(Ensoleillement ensoleillement) {
        this.ensoleillement = ensoleillement;
        return this;
    }

    public void setEnsoleillement(Ensoleillement ensoleillement) {
        this.ensoleillement = ensoleillement;
    }

    public RichesseSol getRichesseSol() {
        return richesseSol;
    }

    public Plante richesseSol(RichesseSol richesseSol) {
        this.richesseSol = richesseSol;
        return this;
    }

    public void setRichesseSol(RichesseSol richesseSol) {
        this.richesseSol = richesseSol;
    }

    public TypeTerre getTypeTerre() {
        return typeTerre;
    }

    public Plante typeTerre(TypeTerre typeTerre) {
        this.typeTerre = typeTerre;
        return this;
    }

    public void setTypeTerre(TypeTerre typeTerre) {
        this.typeTerre = typeTerre;
    }

    public TypeFeuillage getTypeFeuillage() {
        return typeFeuillage;
    }

    public Plante typeFeuillage(TypeFeuillage typeFeuillage) {
        this.typeFeuillage = typeFeuillage;
        return this;
    }

    public void setTypeFeuillage(TypeFeuillage typeFeuillage) {
        this.typeFeuillage = typeFeuillage;
    }

    public TypeRacine getTypeRacine() {
        return typeRacine;
    }

    public Plante typeRacine(TypeRacine typeRacine) {
        this.typeRacine = typeRacine;
        return this;
    }

    public void setTypeRacine(TypeRacine typeRacine) {
        this.typeRacine = typeRacine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plante plante = (Plante) o;
        if (plante.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plante.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plante{" +
            "id=" + getId() +
            ", phMin='" + getPhMin() + "'" +
            ", phMax='" + getPhMax() + "'" +
            ", tempMin=" + getTempMin() +
            ", tempMax=" + getTempMax() +
            ", commonName='" + getCommonName() + "'" +
            "}";
    }
}
