package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO para encapsular parametros recebidos para paginação
 * de registros, e apoiar na validação dos valores recebidos
 */
public class PaginateParamsDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @NotEmpty
    @NotNull
    @Positive
    private String page;

    @NotEmpty
    @NotNull
    @Positive
    private String limite;

    public PaginateParamsDTO(String page, String limite) {
        this.page = page;
        this.limite = limite;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

}
