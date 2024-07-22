package br.com.snowmanlabs.api_livros.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando livros
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_livro")
public class MLivro implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ativo;
    private String titulo;

    @OneToOne
    @JoinColumn(name = "autor_id")
    private MAutor autor;

    @OneToOne
    @JoinColumn(name = "idioma_id")
    private MIdioma idioma;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private MUsuario usuario;

    @Column(name = "dt_publicacao")
    private LocalDateTime dataPublicacao;
    private String editora;

    @Column(name = "nr_paginas")
    private int numeroPaginas;

    private String genero;
    private String sinopse;
    private BigDecimal valor;

}
