package br.com.snowmanlabs.servico_email.domain.dto;
  
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que contém resultados da previsão do tempo,
 * do dia atual e dos próximos dias
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("temp")
    private BigInteger temp;

    @JsonProperty("date")
    private String date;

    @JsonProperty("time")
    private String time;

    @JsonProperty("condition_code")
    private String conditionCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("currently")
    private String currently;

    @JsonProperty("cid")
    private String cid;

    @JsonProperty("city")
    private String city;

    @JsonProperty("img_id")
    private String imgId;

    @JsonProperty("humidity")
    private BigInteger humidity;

    @JsonProperty("cloudiness")
    private BigDecimal cloudiness;

    @JsonProperty("rain")
    private BigDecimal rain;

    @JsonProperty("wind_speedy")
    private String windSpeedy;

    @JsonProperty("wind_direction")
    private BigInteger windDirection;

    @JsonProperty("wind_cardinal")
    private String windCardinal;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("moon_phase")
    private String moonPhase;

    @JsonProperty("condition_slug")
    private String conditionSlug;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("forecast")
    private List<DiaDTO> dias;

}
