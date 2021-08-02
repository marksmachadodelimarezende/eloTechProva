#BACKEND
#Requisitos:
Possuir ao menos os endpoints: GET(Buscar uma �nica Pessoa), GET (Busca paginada op��o de filtro para retornar v�rias pessoas), POST, PUT, DELETE

O cadastro de pessoa deve ter os campos: Id, Nome, CPF, Data de nascimento.

A pessoa deve possuir uma lista de contatos (relacionamento um para muitos) com os campos: Id, Nome, Telefone e Email.

Os dados devem ser persistidos utilizando um banco de dados relacional.

---
#Valida��es:
Todos os campos s�o obrigat�rios, tanto da pessoa como do contato

A Pessoa deve possuir ao menos um contato

O CPF deve ser um CPF v�lido

A Data de nascimento n�o pode ser uma data futura

Validar sintaxe do email do contato
