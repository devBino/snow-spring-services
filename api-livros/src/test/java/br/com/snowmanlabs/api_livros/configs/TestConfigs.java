package br.com.snowmanlabs.api_livros.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.snowmanlabs.api_livros.domain.converter.AutorConverter;
import br.com.snowmanlabs.api_livros.domain.converter.IdiomaConverter;
import br.com.snowmanlabs.api_livros.domain.converter.LivroConverter;

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

    @Bean
    @Primary
    public LivroConverter livroConverter(){
        return new LivroConverter();
    }

    @Bean
    @Primary
    public LocalValidatorFactoryBean validatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }

}
