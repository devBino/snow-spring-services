package br.com.snowmanlabs.servico_email.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * DTO que contém dados dos dias na lista
 * de dias recuperados na previsão do tempo
 */
@Getter
@Setter
@NoArgsConstructor
public class DiaDTO implements Serializable {

    @JsonProperty("date")
    private String date;

    @JsonProperty("weekday")
    private String weekday;

    @JsonProperty("max")
    private BigInteger max;

    @JsonProperty("min")
    private BigInteger min;

    @JsonProperty("humidity")
    private BigInteger humidity;

    @JsonProperty("cloudiness")
    private BigDecimal cloudiness;

    @JsonProperty("rain")
    private BigDecimal rain;

    @JsonProperty("rain_probability")
    private BigInteger rainProbability;

    @JsonProperty("wind_speedy")
    private String windSpeedy;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("moon_phase")
    private String moonPhase;

    @JsonProperty("description")
    private String description;

    @JsonProperty("condition")
    private String condition;
    
}
