package com.elotech.scp.controller;

import com.elotech.scp.persistence.Pessoa;
import com.elotech.scp.repository.PessoaRepository;
import com.elotech.scp.repository.filter.PessoaFilter;
import com.elotech.scp.representation.PessoaRepresentation;
import com.elotech.scp.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.elotech.scp.useful.ResponseEntityUseful.getResponseEntity;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    private PessoaRepository repository;
    private PessoaService service;

    @Autowired
    public PessoaController(PessoaRepository repository, PessoaService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = "page")
    @ResponseStatus(HttpStatus.OK)
    public Page<Pessoa> getFindAll(@RequestBody PessoaFilter pessoaFilter, Pageable pageable){
        return repository.getListPageable(pessoaFilter, pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getFindById(@PathVariable @NotNull Long id){
        return getResponseEntity(PessoaRepresentation.of(repository.findById(id).orElseGet(Pessoa::new)));
    }

    @GetMapping(value = "/{id}/contato")
    public ResponseEntity<?> getFindByIdPlusContato(@PathVariable @NotNull Long id){
        return getResponseEntity(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa){
        var result = service.save(pessoa);
        return getResponseEntity(HttpStatus.CREATED, result);
    }

    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody Pessoa pessoa){
        return getResponseEntity(service.put(pessoa));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotNull Long id) {
        repository.deleteById(id);
    }

}
