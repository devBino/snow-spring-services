package br.com.snowmanlabs.api_livros.domain.converter;

import org.springframework.stereotype.Component;

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.model.MAutor;

/**
 * Converte DTO para Model e Model para DTO
 * em representações de objetos da entidade Autor
 */
@Component
public class AutorConverter {
    
    public AutorDTO toDTO(final MAutor origin){

        final AutorDTO destin = new AutorDTO();

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setNome( origin.getNome() );

        return destin;

    }

    public MAutor toModel(final AutorDTO origin){

        final MAutor destin = new MAutor();

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setNome( origin.getNome() );

        return destin;

    }

}