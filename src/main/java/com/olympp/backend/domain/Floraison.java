package com.olympp.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Floraison.
 */
@Entity
@Table(name = "floraison",
uniqueConstraints={@UniqueConstraint(columnNames={"plante_id", "mois_id"})})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Floraison implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("floraisons")
    private Plante plante;

    @ManyToOne
    @JsonIgnoreProperties("floraisons")
    private Mois mois;

    
    public Floraison(Plante plante, Mois mois) {
		super();
		this.plante = plante;
		this.mois = mois;
	}

	public Floraison() {
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

    public Floraison plante(Plante plante) {
        this.plante = plante;
        return this;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    public Mois getMois() {
        return mois;
    }

    public Floraison mois(Mois mois) {
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
        Floraison floraison = (Floraison) o;
        if (floraison.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), floraison.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Floraison{" +
            "id=" + getId() +
            "}";
    }
}
