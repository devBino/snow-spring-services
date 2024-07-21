package br.com.snowmanlabs.api_livros.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import br.com.snowmanlabs.api_livros.domain.converter.AutorConverter;
import br.com.snowmanlabs.api_livros.domain.converter.IdiomaConverter;

@Configuration
public class TestConfigs {
    
    @Bean
    @Primary
    public IdiomaConverter idiomaConverter(){
        return new IdiomaConverter();
    }

    @Bean
    @Primary
    public AutorConverter autorConverter(){
        return new AutorConverter();
    }

}
