create table consultas(

    id bigint not null auto_increment,
    cadastro_id bigint not null,
    cadastrossecundarios_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_cadastro_id foreign key(cadastro_id) references cadastros(id),
    constraint fk_consultas_cadastrossecundarios_id foreign key(cadastrossecundarios_id) references cadastrossecundarios(id)

);