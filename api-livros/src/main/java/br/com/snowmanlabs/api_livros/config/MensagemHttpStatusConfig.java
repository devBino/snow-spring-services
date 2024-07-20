package br.com.snowmanlabs.api_livros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.snowmanlabs.api_livros.provider.MensagemHttpStatusProvider;

@Configuration
public class MensagemHttpStatusConfig {
    
    @Bean
    public MensagemHttpStatusProvider mensagemProvider(){
        return new MensagemHttpStatusProvider();
    }

}
