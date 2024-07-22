package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular dados da entidade Idioma
 * que será transeferida entre as camadas da api
 */
@Getter
@Setter
@NoArgsConstructor
public class IdiomaDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;
    private int ativo;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String codIdioma;
    
    private String codRegiao;

}
