package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular dados da entidade Autor
 * que será transeferida entre as camadas da api
 */
@Getter
@Setter
@NoArgsConstructor
public class AutorDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;
    private int ativo;
    private String nome;

}
