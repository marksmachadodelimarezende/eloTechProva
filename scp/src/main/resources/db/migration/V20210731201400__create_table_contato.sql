Create table public.contato
(
    id       serial,
    nome     varchar(250),
    telefone varchar(50),
    email    varchar(250),
    CONSTRAINT contato_id_pk PRIMARY KEY (id)
);

insert into public.contato (nome, telefone, email)
values ('Contato 1', '(62) 1111-1111', 'contato1@email.com'), --id 1
       ('Contato 2', '(63) 97777-8888', 'contato2@email.com'), --id 2
       ('Contato 3', '(15) 2222-3333', 'c3@email.com'), --id 3
       ('Contato 4', '(21) 98888-5555', 'con4@email.com.br'); --id 4