alter table cadastros add ativo tinyint;
update cadastros set ativo = 1;