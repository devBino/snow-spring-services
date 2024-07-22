package br.com.snowmanlabs.api_livros.domain.converter;

import org.springframework.stereotype.Component;

import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.domain.model.MIdioma;

/**
 * Converte DTO para Model e Model para DTO
 * em representações de objetos da entidade Idioma
 */
@Component
public class IdiomaConverter {
    
    public IdiomaDTO toDTO(final MIdioma origin){

        final IdiomaDTO destin = new IdiomaDTO();

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setNome( origin.getNome() );
        destin.setCodIdioma( origin.getCodIdioma() );
        destin.setCodRegiao( origin.getCodRegiao() );

        return destin;

    }

    public MIdioma toModel(final IdiomaDTO origin){

        final MIdioma destin = new MIdioma();

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setNome( origin.getNome() );
        destin.setCodIdioma( origin.getCodIdioma() );
        destin.setCodRegiao( origin.getCodRegiao() );

        return destin;

    }

}
