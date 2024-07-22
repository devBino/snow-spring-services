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
 * Entidade do banco de dados representando usuarios
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class MUsuario implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String usuario;
    private String password;
    private int ativo;

}
