package com.elotech.scp.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PessoaFilter {
    private String nome;

    private String cpf;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNascimentoDe;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNascimentoAte;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimentoDe() {
        return dtNascimentoDe;
    }

    public void setDtNascimentoDe(LocalDate dtNascimentoDe) {
        this.dtNascimentoDe = dtNascimentoDe;
    }

    public LocalDate getDtNascimentoAte() {
        return dtNascimentoAte;
    }

    public void setDtNascimentoAte(LocalDate dtNascimentoAte) {
        this.dtNascimentoAte = dtNascimentoAte;
    }
}
