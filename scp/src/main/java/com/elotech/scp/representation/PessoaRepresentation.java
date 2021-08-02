package com.elotech.scp.representation;

import com.elotech.scp.persistence.Pessoa;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PessoaRepresentation {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dtNascimento;

    public static PessoaRepresentation of(Pessoa pessoa) {
        return builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpf(pessoa.getCpf())
                .dtNascimento(pessoa.getDtNascimento())
                .build();
    }
}
