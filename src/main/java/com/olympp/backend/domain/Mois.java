package com.olympp.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Mois enumeration. TODO contraindre nombre lignes
 * 
 * @author Pierre TAQUET
 */
@ApiModel(description = "Mois enumeration. TODO contraindre nombre lignes @author Pierre TAQUET")
@Entity
@Table(name = "mois")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mois implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mois", unique = true)
	private String mois;

	@OneToMany(mappedBy = "mois")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Recolte> recoltes = new HashSet<>();
	@OneToMany(mappedBy = "mois")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Floraison> floraisons = new HashSet<>();

	public Mois(String mois) {
		this.mois = mois;
	}

	public Mois() {
		super();
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMois() {
		return mois;
	}

	public Mois mois(String mois) {
		this.mois = mois;
		return this;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Set<Recolte> getRecoltes() {
		return recoltes;
	}

	public Mois recoltes(Set<Recolte> recoltes) {
		this.recoltes = recoltes;
		return this;
	}

	public Mois addRecolte(Recolte recolte) {
		this.recoltes.add(recolte);
		recolte.setMois(this);
		return this;
	}

	public Mois removeRecolte(Recolte recolte) {
		this.recoltes.remove(recolte);
		recolte.setMois(null);
		return this;
	}

	public void setRecoltes(Set<Recolte> recoltes) {
		this.recoltes = recoltes;
	}

	public Set<Floraison> getFloraisons() {
		return floraisons;
	}

	public Mois floraisons(Set<Floraison> floraisons) {
		this.floraisons = floraisons;
		return this;
	}

	public Mois addFloraison(Floraison floraison) {
		this.floraisons.add(floraison);
		floraison.setMois(this);
		return this;
	}

	public Mois removeFloraison(Floraison floraison) {
		this.floraisons.remove(floraison);
		floraison.setMois(null);
		return this;
	}

	public void setFloraisons(Set<Floraison> floraisons) {
		this.floraisons = floraisons;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Mois mois = (Mois) o;
		if (mois.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), mois.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Mois{" + "id=" + getId() + ", mois='" + getMois() + "'" + "}";
	}
}
