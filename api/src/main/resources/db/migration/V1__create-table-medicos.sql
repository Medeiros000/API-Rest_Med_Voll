create table medicos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(50) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(50) not null,
    logradouro varchar(100) not null,
    bairro varchar(50) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(50) not null,
    telefone varchar(20) not null,
    ativo tinyint default 1,

    primary key(id)

);