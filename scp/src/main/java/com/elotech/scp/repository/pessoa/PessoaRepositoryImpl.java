package com.elotech.scp.repository.pessoa;

import com.elotech.scp.persistence.Pessoa;
import com.elotech.scp.repository.filter.ContatoFilter;
import com.elotech.scp.repository.filter.PessoaFilter;
import com.elotech.scp.repository.predicates.PredicateFilter;
import com.elotech.scp.service.PessoaService;
import com.elotech.scp.useful.ValidatorsUseful;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.elotech.scp.useful.ValidatorsUseful.*;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<?> criteriaQuery;
    private Root<?> root;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> getListPageable(PessoaFilter pessoaFilter, Pageable pageable) {
        criteriaBuilder = manager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        root = criteriaQuery.from(Pessoa.class);
        return (Page<Pessoa>) getDataRepositoryFilter(pessoaFilter, pageable);
    }

    private Page<?> getDataRepositoryFilter(PessoaFilter pessoaFilter, Pageable pageable) {
        //Restricoes
        Predicate[] predicates = criarRestricoes(pessoaFilter);

        criteriaQuery.where(predicates);

        TypedQuery<?> typedQuery = manager.createQuery(criteriaQuery);

        return getPageResultList(typedQuery, pageable, pessoaFilter);
    }

    private Predicate[] criarRestricoes(PessoaFilter pessoaFilter) {
        List<Predicate> predicates = new ArrayList<>();
        PredicateFilter filters = new PredicateFilter(criteriaBuilder, root);

        if (isValidString(pessoaFilter.getNome()))
            predicates.add(filters.getStringLike("nome", pessoaFilter.getNome()));

        if (isValidString(pessoaFilter.getCpf()))
            predicates.add(filters.getEqual("cpf", pessoaFilter.getCpf()));

        if (pessoaFilter.getDtNascimentoDe() != null)
            predicates.add(filters.getDateGE("dtNascimento", pessoaFilter.getDtNascimentoDe()));

        if (pessoaFilter.getDtNascimentoAte() != null)
            predicates.add(filters.getDateLE("dtNascimento", pessoaFilter.getDtNascimentoAte()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Page<?> getPageResultList(TypedQuery<?> typedQuery, Pageable pageable, PessoaFilter pessoaFilter) {
        adicionarRestricoesPaginacoesQuery(typedQuery, pageable);
        return new PageImpl<>(typedQuery.getResultList(), pageable, total(pessoaFilter));
    }

    private void adicionarRestricoesPaginacoesQuery(TypedQuery<?> typedQuery, Pageable pageable) {
        int pagAtual = pageable.getPageNumber();
        int totalPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = pagAtual * totalPorPagina;
        typedQuery.setFirstResult(primeiroRegistroPagina);
        typedQuery.setMaxResults(totalPorPagina);
    }

    private Long total(PessoaFilter pessoaFilter) {
        criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQueryLong = criteriaBuilder.createQuery(Long.class);
        root = criteriaQueryLong.from(Pessoa.class);

        Predicate[] predicates = criarRestricoes(pessoaFilter);
        criteriaQueryLong.where(predicates);
        criteriaQueryLong.select(criteriaBuilder.count(root));
        return manager.createQuery(criteriaQueryLong).getSingleResult();
    }
}
