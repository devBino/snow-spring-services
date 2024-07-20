CREATE TABLE tb_autor (
    id INT NOT NULL AUTO_INCREMENT,
    ativo INT NOT NULL,
    nome VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);
