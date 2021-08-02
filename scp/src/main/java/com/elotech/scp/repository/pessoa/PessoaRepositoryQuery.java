package com.elotech.scp.repository.pessoa;

import com.elotech.scp.persistence.Pessoa;
import com.elotech.scp.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {
    Page<Pessoa> getListPageable(PessoaFilter pessoaFilter, Pageable pageable);
}
