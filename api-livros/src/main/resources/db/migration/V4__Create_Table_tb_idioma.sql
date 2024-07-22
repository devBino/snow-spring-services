CREATE TABLE IF NOT EXISTS tb_idioma (
    id INT NOT NULL AUTO_INCREMENT,
    ativo INT NOT NULL DEFAULT 1,
    nome VARCHAR(50) NOT NULL,
    cod_idioma VARCHAR(10) NOT NULL,
    cod_regiao VARCHAR(10) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);
