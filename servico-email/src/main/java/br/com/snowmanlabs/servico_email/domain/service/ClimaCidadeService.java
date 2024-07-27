package br.com.snowmanlabs.servico_email.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.snowmanlabs.servico_email.domain.dto.PrevisaoTempoDTO;

/**
 * Serviço para recuperar dados de previsão do tempo
 */
@Service
public class ClimaCidadeService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private String apiKey;

    @Autowired
    private String apiEndPoint;

    public PrevisaoTempoDTO recuperarDadosClima(final String nomeCidadeUf){

        String url = String.format(apiEndPoint, apiKey, nomeCidadeUf);

        ResponseEntity<PrevisaoTempoDTO> previsaoTempo = restTemplate.getForEntity(
            url, PrevisaoTempoDTO.class);

        PrevisaoTempoDTO dadosPrevisao = previsaoTempo.getBody();

        if( "default".equals( previsaoTempo.getBody().getBy() ) ){
            return null;
        }

        return dadosPrevisao;

    }

}
