package com.elotech.scp.service;

import com.elotech.scp.core.exceptionHandler.MessageError;
import com.elotech.scp.core.exceptionHandler.exception.InvalidValueException;
import com.elotech.scp.persistence.Contato;
import com.elotech.scp.persistence.Pessoa;
import com.elotech.scp.useful.ValidatorsUseful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.elotech.scp.useful.DateUseful.isDateAfterNow;
import static com.elotech.scp.useful.DateUseful.isValidDate;
import static com.elotech.scp.useful.ValidatorCPF.isValidCpf;
import static com.elotech.scp.useful.ValidatorsUseful.isValidString;

@Service
public class PessoaValidatorsService {
    private List<MessageError> errors;
    private ContatoService contatoService;

    @Autowired
    public PessoaValidatorsService(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    public void validateForm(Pessoa dados) {
        errors = new ArrayList<>();
        checkFormValidPessoa(dados);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors, "Validação de formulário da entidade Pessoa");
    }

    private void checkFormValidPessoa(Pessoa dados) {
        if (!isValidString(dados.getNome())) addErrors("nome da pessoa",dados.getNome());
        if (!isValidString(dados.getCpf()) || !isValidCpf(dados.getCpf())) addErrors("cpf da pessoa",dados.getCpf(), "Campo invávlido ou obritagório com 11 dígitos");
        if (!isValidDate(dados.getDtNascimento())) addErrors("data de nascimento da pessoa",dados.getDtNascimento());
        if (isDateAfterNow(dados.getDtNascimento())) addErrors("data de nascimento da pessoa",dados.getDtNascimento(), "Campo data não pode ser maior do que hoje");
    }

    private void addErrors(String fieldName, Object value) {
        addErrors(fieldName, value, "Campo inválido ou obrigatório");
    }

    private void addErrors(String fieldName, Object value, String message) {
        errors.add(new MessageError(fieldName, value, message));
    }
}
