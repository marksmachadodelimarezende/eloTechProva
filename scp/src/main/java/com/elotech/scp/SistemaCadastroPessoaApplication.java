package com.elotech.scp;

import com.elotech.scp.useful.ValidatorCPF;
import com.elotech.scp.useful.ValidatorsUseful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaCadastroPessoaApplication {
	Logger log = LoggerFactory.getLogger(SistemaCadastroPessoaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SistemaCadastroPessoaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			log.info("****************************");
			log.info("**** Session Validators ****");
			validEmails();
			validCpfs();
			log.info("**** Finish Validators ****");
			log.info("****************************");
			log.info("*** Backend Inicializado ***");
			log.info("****************************");
		};
	}

	private void validCpfs() {
		validCpf("00000000000");
		validCpf("0");
		validCpf("01234567890");
		validCpf("00002377101");
		validCpf("11111111111");
		validCpf("22222222222");
	}

	private void validEmails() {
		log.info(checkSintaxEmail("test@email.com"));
		log.info(checkSintaxEmail("test.ok@email.com"));
		log.info(checkSintaxEmail("testemail.com"));
		log.info(checkSintaxEmail("test.com@"));
		log.info(checkSintaxEmail("@email.com"));
		log.info(checkSintaxEmail("test@em@il.com"));
		log.info(checkSintaxEmail("test@emailcom"));
	}

	private String checkSintaxEmail(String email) {
		return "Email " + email + " is " + (ValidatorsUseful.isValidSintaxEmail(email) ? "Ok" : "Not Valid");
	}

	private void validCpf(String cpf) {
		log.info("CPF '" + cpf + "': " + (ValidatorCPF.isValidCpf(cpf) ? "Valid" : "Invalid"));
	}
}
