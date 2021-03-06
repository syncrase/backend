package com.olympp.backend.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VitesseCroissance.
 */
@Entity
@Table(name = "vitesse_croissance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VitesseCroissance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vitesse_croissance", unique = true)
    private String vitesseCroissance;

    public VitesseCroissance(String vitesseCroissance) {
		super();
		this.vitesseCroissance = vitesseCroissance;
	}

	public VitesseCroissance() {
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

    public String getVitesseCroissance() {
        return vitesseCroissance;
    }

    public VitesseCroissance vitesseCroissance(String vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
        return this;
    }

    public void setVitesseCroissance(String vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
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
        VitesseCroissance vitesseCroissance = (VitesseCroissance) o;
        if (vitesseCroissance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vitesseCroissance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VitesseCroissance{" +
            "id=" + getId() +
            ", vitesseCroissance='" + getVitesseCroissance() + "'" +
            "}";
    }
}
