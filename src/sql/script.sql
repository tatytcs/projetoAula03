create table produto (
	id			UUID			primary key,
	nome		varchar(250)	not null,
	preco		numeric(10,2)	not null,
	quantidade	int				not null
);