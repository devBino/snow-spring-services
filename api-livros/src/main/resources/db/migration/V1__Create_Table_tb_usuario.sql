CREATE TABLE IF NOT EXISTS tb_usuario (
    id INT NOT NULL AUTO_INCREMENT,
    ativo INT NOT NULL DEFAULT 1,
    nome VARCHAR(100) DEFAULT NULL,
    password VARCHAR(150) DEFAULT NULL,
    usuario VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome),
    UNIQUE (password),
    UNIQUE (usuario)
);
