package com.olympp.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Recolte.
 */
@Entity
@Table(name = "recolte",
uniqueConstraints={@UniqueConstraint(columnNames={"plante_id", "mois_id"})})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Recolte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("recoltes")
    private Plante plante;

    @ManyToOne
    @JsonIgnoreProperties("recoltes")
    private Mois mois;

    public Recolte(Plante plante, Mois mois) {
		super();
		this.plante = plante;
		this.mois = mois;
	}

	public Recolte() {
		super();
		// TODO Auto-generated constructor stub
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plante getPlante() {
        return plante;
    }

    public Recolte plante(Plante plante) {
        this.plante = plante;
        return this;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    public Mois getMois() {
        return mois;
    }

    public Recolte mois(Mois mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Mois mois) {
        this.mois = mois;
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
        Recolte recolte = (Recolte) o;
        if (recolte.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recolte.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Recolte{" +
            "id=" + getId() +
            "}";
    }
}
