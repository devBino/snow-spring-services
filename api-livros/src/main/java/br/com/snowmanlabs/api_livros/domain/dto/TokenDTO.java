package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular dados do Token gerado
 * que será devolvido nas rotinas de autenticação
 */
@Getter
@Setter
@NoArgsConstructor
public class TokenDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    private String token;
    private String mensagem;
    private boolean sucesso;

}
