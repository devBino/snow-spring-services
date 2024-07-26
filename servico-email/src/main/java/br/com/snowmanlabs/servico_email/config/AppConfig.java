package br.com.snowmanlabs.servico_email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configurações de Beans para aplicação
 */
@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public String apiKey(@Value("${api.key.hg-brasil}") String apiKey) {
        return apiKey;
    }

    @Bean
    public String apiEndPoint(){
        return "https://api.hgbrasil.com/weather?key=%s&city_name=%s";
    }

}
