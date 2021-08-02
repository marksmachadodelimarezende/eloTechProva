package com.elotech.scp.repository.predicates;

import com.sun.jdi.CharValue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class PredicateFilter {

    private CriteriaBuilder criteriaBuilder;
    private Root<?> root;

    public PredicateFilter(CriteriaBuilder criteriaBuilder, Root<?> root) {
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
    }

    public Predicate getStringLike(String labelAtribute, String valueFilter) {
        return criteriaBuilder.like(
                criteriaBuilder.lower(root.get(labelAtribute)),
                "%" + valueFilter.toLowerCase() + "%"
        );
    }

    public Predicate getStringLike(String chave, String labelAtribute, String valueFilter) {
        return criteriaBuilder.like(
                criteriaBuilder.lower(root.get(chave).get(labelAtribute)),
                "%" + valueFilter.toLowerCase() + "%"
        );
    }

    public Predicate getEqual(String labelAtribute, String valueFilter) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(labelAtribute)), valueFilter.toLowerCase());
    }

    public Predicate getDateEQ(String labelAtribute, LocalDate dataFilter) {
        return criteriaBuilder.equal(root.get(labelAtribute), dataFilter);
    }

    public Predicate getDateGE(String labelAtribute, LocalDate dataFilter) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get(labelAtribute), dataFilter);
    }

    public Predicate getDateLE(String labelAtribute, LocalDate dataFilter) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(labelAtribute), dataFilter);
    }
}
