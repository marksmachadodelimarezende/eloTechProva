package com.elotech.scp.service;

import com.elotech.scp.core.exceptionHandler.MessageError;
import com.elotech.scp.core.exceptionHandler.exception.InvalidValueException;
import com.elotech.scp.persistence.Contato;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.elotech.scp.useful.ValidatorsUseful.isValidSintaxEmail;
import static com.elotech.scp.useful.ValidatorsUseful.isValidString;

@Service
public class ContatoValidatorsService {
    List<MessageError> errors;

    public void validateForm(Contato dados) {
        errors = new ArrayList<>();
        checkFormValidContato(dados);
        if (!errors.isEmpty())
            throw new InvalidValueException(errors, "Validação de formulário da entidade Contato");
    }

    private void checkFormValidContato(Contato contato) {
        if (!isValidString(contato.getNome())) addErrors("nome do contato", contato.getNome());
        if (!isValidString(contato.getEmail()) && !isValidSintaxEmail(contato.getEmail()))
            addErrors("email do contato", contato.getEmail());
        if (!isValidString(contato.getTelefone())) addErrors("telefone do contato", contato.getTelefone());
    }

    private void addErrors(String fieldName, Object value) {
        errors.add(new MessageError(fieldName, value, "Campo inválido ou obrigatório"));
    }

}
