package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular dados da entidade usuario
 */
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;

    private String nome;
    private String user;
    private String password;
    private int ativo;

}
