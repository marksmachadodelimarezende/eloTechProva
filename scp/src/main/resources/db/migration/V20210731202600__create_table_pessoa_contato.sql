CREATE TABLE public.pessoa_contato
(
    id_pessoa  BIGINT NOT NULL,
    id_contato BIGINT NOT NULL,
    CONSTRAINT pessoa_contato_pk PRIMARY KEY (id_pessoa, id_contato),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
    FOREIGN KEY (id_contato) REFERENCES contato (id)
);

insert into public.pessoa_contato
values (1, 2),
       (1, 3),
       (2, 1),
       (3, 3),
       (4, 2),
       (4, 4),
       (5, 4),
       (6, 1);
