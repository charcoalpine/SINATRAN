CREATE DATABASE sinatran
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;
       

CREATE TABLE users(
	login character varying(80)NOT NULL,
	password character varying(80),
	CONSTRAINT pk_login PRIMARY KEY (login)
)WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

CREATE TABLE cidadao
(
  cod  SERIAL NOT NULL, -- Codigo do cidadao...
  nome character varying(100), -- Nome completo do cidadão
  data_nasc date,
  sexo character(1),
  endereco character varying(150),
  telefone character varying(11) NOT NULL,
  rg integer NOT NULL,
  cnh integer, 
  cpf integer NOT NULL,
  CONSTRAINT pk_cod PRIMARY KEY (cod)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cidadao
  OWNER TO postgres;
COMMENT ON COLUMN cidadao.cod IS 'Codigo do cidadao
PK, com AI';
COMMENT ON COLUMN cidadao.nome IS 'Nome completo do cidadão';

CREATE TABLE multa
(
  cod SERIAL NOT NULL,
  tipo character varying(30),
  CONSTRAINT pk_cod_multa PRIMARY KEY (cod)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE multa
  OWNER TO postgres;

CREATE TABLE automovel
(
  cod SERIAL NOT NULL,
  cod_cidadao INTEGER,
  cod_multa INTEGER,
  tipo character varying(20),
  modelo character varying(20),
  placa character varying(7),
  chassi character varying(17),
  CONSTRAINT pk_cod_automovel PRIMARY KEY (cod),
  CONSTRAINT fk_multa FOREIGN KEY (cod_multa) 
      REFERENCES multa (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cidadao FOREIGN KEY (cod_cidadao) 
  		REFERENCES cidadao(cod) MATCH SIMPLE
  		ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE automovel
  OWNER TO postgres;

CREATE TABLE apreensao(
	cod SERIAL NOT NULL,
	cod_automovel INTEGER,
	cod_cidadao INTEGER,
	cod_multa INTEGER,
	numero integer,
	data date,

	CONSTRAINT pk_cod_apreensao PRIMARY KEY (cod),
	CONSTRAINT fk_cod_automovel FOREIGN KEY (cod_automovel) 
		REFERENCES automovel(cod) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_cod_cidadao FOREIGN KEY (cod_cidadao) 
		REFERENCES cidadao(cod)MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION

)WITH (
  OIDS=FALSE
);
ALTER TABLE apreensao
  OWNER TO postgres;
