CREATE TABLE tb_livro (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor_id INT NOT NULL,
    idioma_id INT NOT NULL,
    usuario_id INT NOT NULL,
    dt_publicacao TIMESTAMP DEFAULT NULL,
    editora VARCHAR(100) DEFAULT NULL,
    nr_paginas INT DEFAULT NULL,
    genero VARCHAR(70) DEFAULT NULL,
    sinopse CLOB,
    valor DECIMAL(10,2) DEFAULT 0.00,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_id) REFERENCES tb_autor(id),
    FOREIGN KEY (idioma_id) REFERENCES tb_idioma(id)
);
