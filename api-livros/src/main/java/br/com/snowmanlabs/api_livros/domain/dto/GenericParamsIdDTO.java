package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO para encapsular ids recebidos em requests que usam ids, 
 * e apoiar na validação do id
 */
public class GenericParamsIdDTO implements Serializable {
    
    public static final Long serialVersionUID = 1L;

    @NotEmpty
    @NotNull
    @Positive
    private String id;

    public GenericParamsIdDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
