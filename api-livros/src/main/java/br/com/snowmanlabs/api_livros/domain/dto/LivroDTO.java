package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    @NotEmpty
    private String titulo;

    @NotNull
    @Positive
    private Long autorId;

    @NotNull
    @Positive
    private Long idiomaId;

    @NotNull
    @Positive
    private Long usuarioId;

    @NotNull
    private LocalDateTime dataPublicacao;

    private String editora;

    @NotNull
    @Positive
    private int numeroPaginas;

    private String genero;
    private String sinopse;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal valor;
    
}
