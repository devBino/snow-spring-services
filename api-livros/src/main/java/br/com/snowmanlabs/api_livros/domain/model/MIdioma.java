package br.com.snowmanlabs.api_livros.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando idiomas
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_idioma")
public class MIdioma implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ativo;
    private String nome;

    @Column(name = "cod_idioma")
    private String codIdioma;

    @Column(name = "cod_regiao")
    private String codRegiao;
    
}
