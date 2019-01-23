package com.olympp.backend.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Mois entity. This class is used in MoisResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mois?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MoisCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter mois;

    private LongFilter recolteId;

    private LongFilter floraisonId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMois() {
        return mois;
    }

    public void setMois(StringFilter mois) {
        this.mois = mois;
    }

    public LongFilter getRecolteId() {
        return recolteId;
    }

    public void setRecolteId(LongFilter recolteId) {
        this.recolteId = recolteId;
    }

    public LongFilter getFloraisonId() {
        return floraisonId;
    }

    public void setFloraisonId(LongFilter floraisonId) {
        this.floraisonId = floraisonId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MoisCriteria that = (MoisCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mois, that.mois) &&
            Objects.equals(recolteId, that.recolteId) &&
            Objects.equals(floraisonId, that.floraisonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mois,
        recolteId,
        floraisonId
        );
    }

    @Override
    public String toString() {
        return "MoisCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mois != null ? "mois=" + mois + ", " : "") +
                (recolteId != null ? "recolteId=" + recolteId + ", " : "") +
                (floraisonId != null ? "floraisonId=" + floraisonId + ", " : "") +
            "}";
    }

}