create table consultas(
    id serial primary key,
    data varchar(10) not null,
    hora varchar(10) not null,
    medico_id int not null,
    especialidade varchar(25) not null,
    paciente_id int not null,
    andamento varchar(25) not null,
    observacoes varchar(100) not null
);