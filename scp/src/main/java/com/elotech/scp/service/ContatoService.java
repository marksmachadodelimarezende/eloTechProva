package com.elotech.scp.service;

import com.elotech.scp.persistence.Contato;
import com.elotech.scp.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    private ContatoRepository repository;
    private ContatoValidatorsService validatorsService;

    @Autowired
    public ContatoService(ContatoRepository repository, ContatoValidatorsService validatorsService) {
        this.repository = repository;
        this.validatorsService = validatorsService;
    }

    public synchronized Contato save(Contato contato) {
        validatorsService.validateForm(contato);
        return repository.save(contato);
    }

    public List<Contato> saveList(List<Contato> contatos) {
        return contatos.stream().map(this::save).collect(Collectors.toList());
    }

    public List<Contato> updateList(List<Contato> contatos) {
        return contatos.stream().map(this::update).collect(Collectors.toList());
    }

    public Contato update(Contato contato) {
        Contato savedRecord = Optional.of(repository.findById(contato.getId()).orElseGet(Contato::new)).get();
        if (savedRecord.getId() != null) {
            BeanUtils.copyProperties(contato, savedRecord, "id");
            save(savedRecord);
            return repository.findById(contato.getId()).get();
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
