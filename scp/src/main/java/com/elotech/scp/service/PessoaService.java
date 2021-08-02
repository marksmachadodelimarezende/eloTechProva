package com.elotech.scp.service;

import com.elotech.scp.persistence.Pessoa;
import com.elotech.scp.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    private PessoaRepository repository;
    private PessoaValidatorsService pessoaValidatorsService;
    private ContatoService contatoService;

    @Autowired
    public PessoaService(PessoaRepository repository, PessoaValidatorsService pessoaValidatorsService, ContatoService contatoService) {
        this.repository = repository;
        this.pessoaValidatorsService = pessoaValidatorsService;
        this.contatoService = contatoService;
    }

    public synchronized Pessoa save(Pessoa dados){
        pessoaValidatorsService.validateForm(dados);
        contatoService.saveList(dados.getContatos());
        return repository.save(dados);
    }

    public Optional<Pessoa> put(Pessoa pessoa) {
        Pessoa savedRecord = Optional.of(repository.findById(pessoa.getId()).orElseGet(Pessoa::new)).get();
        if (savedRecord.getId() != null) {
            contatoService.updateList(pessoa.getContatos());
            BeanUtils.copyProperties(pessoa, savedRecord, "id");
            save(savedRecord);
            return repository.findById(pessoa.getId());
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
