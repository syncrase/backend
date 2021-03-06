package com.olympp.backend.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeTerre.
 */
@Entity
@Table(name = "type_terre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeTerre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_terre", unique = true)
    private String typeTerre;

    public TypeTerre(String typeTerre) {
		super();
		this.typeTerre = typeTerre;
	}

	public TypeTerre() {
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

    public String getTypeTerre() {
        return typeTerre;
    }

    public TypeTerre typeTerre(String typeTerre) {
        this.typeTerre = typeTerre;
        return this;
    }

    public void setTypeTerre(String typeTerre) {
        this.typeTerre = typeTerre;
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
        TypeTerre typeTerre = (TypeTerre) o;
        if (typeTerre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeTerre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeTerre{" +
            "id=" + getId() +
            ", typeTerre='" + getTypeTerre() + "'" +
            "}";
    }
}
