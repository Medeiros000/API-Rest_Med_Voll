create table consultas(

    id bigint not null auto_increment,
    data varchar(10) not null,
    hora varchar(10) not null,
    medico_id int(6) not null,
    especialidade varchar(25) not null,
    paciente_id int(10) not null,
    status_consulta varchar(25) not null,
    observacoes varchar(100) not null,


    primary key(id)

);