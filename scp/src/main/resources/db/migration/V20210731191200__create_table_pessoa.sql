Create table public.pessoa
(
    id            serial,
    nome          varchar(250),
    cpf           varchar(11),
    dt_nascimento date,
    CONSTRAINT pessoa_id_pk PRIMARY KEY (id)
);

insert into public.pessoa (nome, cpf, dt_nascimento) values
    ('José Alves', '35109526001', '1987-05-15'),
    ('Maria Donzela', '65746700045', '1985-05-15'),
    ('Carlos Gonçalves', '90187039070', '1995-05-15'),
    ('Alvaro Brito', '33402418053', '2001-05-15'),
    ('Juan Mexicano', '00676862063', '2005-05-15'),
    ('Renato Tavares', '07420744041', '2012-05-15');