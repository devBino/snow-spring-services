package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular dados da entidade Livro
 * que será transeferida entre as camadas da api
 */
@Getter
@Setter
@NoArgsConstructor
public class LivroDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private Long id;
    private int ativo;
    private String titulo;
    private Long autorId;
    private Long idiomaId;
    private Long usuarioId;
    private LocalDateTime dataPublicacao;
    private String editora;
    private int numeroPaginas;
    private String genero;
    private String sinopse;
    private BigDecimal valor;
    
}
