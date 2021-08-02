package com.elotech.scp.useful;

import br.com.caelum.stella.validation.CPFValidator;

public class ValidatorCPF {

    public static boolean isValidCpf(String cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
