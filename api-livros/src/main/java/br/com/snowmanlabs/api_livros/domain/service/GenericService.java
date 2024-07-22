package br.com.snowmanlabs.api_livros.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.snowmanlabs.api_livros.provider.MensagemHttpStatusProvider;

/**
 * Define padrões genericos para os serviços
 */
public class GenericService {
    
    protected String mensagemEntidadeNaoEncontrado;

    @Autowired
    protected MensagemHttpStatusProvider mensagemProvider;

    public GenericService(String mensagemEntidadeNaoEncontrado) {
        this.mensagemEntidadeNaoEncontrado = mensagemEntidadeNaoEncontrado;
    }

}
