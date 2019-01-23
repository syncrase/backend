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
 * Criteria class for the PlantCommonName entity. This class is used in PlantCommonNameResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /plant-common-names?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PlantCommonNameCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter commonName;

    private LongFilter planteId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCommonName() {
        return commonName;
    }

    public void setCommonName(StringFilter commonName) {
        this.commonName = commonName;
    }

    public LongFilter getPlanteId() {
        return planteId;
    }

    public void setPlanteId(LongFilter planteId) {
        this.planteId = planteId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlantCommonNameCriteria that = (PlantCommonNameCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(commonName, that.commonName) &&
            Objects.equals(planteId, that.planteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        commonName,
        planteId
        );
    }

    @Override
    public String toString() {
        return "PlantCommonNameCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (commonName != null ? "commonName=" + commonName + ", " : "") +
                (planteId != null ? "planteId=" + planteId + ", " : "") +
            "}";
    }

}