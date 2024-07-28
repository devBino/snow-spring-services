package br.com.snowmanlabs.servico_email.domain.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade do banco de dados representando Usuarios
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_usuario")
public class MUsuario implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cidade;
    private String uf;
    private String statusEnvio;
    private String obsEnvio;

}
