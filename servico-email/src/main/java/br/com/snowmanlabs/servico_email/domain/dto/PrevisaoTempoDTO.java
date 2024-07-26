package br.com.snowmanlabs.servico_email.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO que contém dados gerais da previsão do tempo
 */
@Getter
@Setter
@NoArgsConstructor
public class PrevisaoTempoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("by")
    private String by;

    @JsonProperty("valid_key")
    private Boolean validKey;

    @JsonProperty("execution_time")
    private BigDecimal executionTime;

    @JsonProperty("from_cache")
    private Boolean fromCache;

    @JsonProperty("results")
    private ResultadosDTO resultadosDTO;
    
}
