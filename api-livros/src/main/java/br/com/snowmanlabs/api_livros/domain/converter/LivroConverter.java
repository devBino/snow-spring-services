package br.com.snowmanlabs.api_livros.domain.converter;

import org.springframework.stereotype.Component;

import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.domain.model.MAutor;
import br.com.snowmanlabs.api_livros.domain.model.MIdioma;
import br.com.snowmanlabs.api_livros.domain.model.MLivro;
import br.com.snowmanlabs.api_livros.domain.model.MUsuario;

/**
 * Converte DTO para Model e Model para DTO
 * em representações de objetos da entidade Livro
 */
@Component
public class LivroConverter {
    
    public LivroDTO toDTO(final MLivro origin){

        final LivroDTO destin = new LivroDTO();

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setTitulo( origin.getTitulo() );

        destin.setAutorId( origin.getAutor().getId() );
        destin.setIdiomaId( origin.getIdioma().getId() );
        destin.setUsuarioId( origin.getUsuario().getId() );

        destin.setDataPublicacao( origin.getDataPublicacao() );
        destin.setEditora( origin.getEditora() );
        destin.setNumeroPaginas( origin.getNumeroPaginas() );
        destin.setGenero( origin.getGenero() );
        destin.setSinopse( origin.getSinopse() );
        destin.setValor( origin.getValor() );

        return destin;

    }

    public MLivro toModel(final LivroDTO origin){

        final MLivro destin = new MLivro();

        MAutor mAutor = new MAutor();
        mAutor.setId( origin.getAutorId() );

        MIdioma mIdioma = new MIdioma();
        mIdioma.setId( origin.getIdiomaId() );

        MUsuario mUsuario = new MUsuario();
        mUsuario.setId( origin.getUsuarioId() );

        destin.setId( origin.getId() );
        destin.setAtivo( origin.getAtivo() );
        destin.setTitulo( origin.getTitulo() );

        destin.setAutor(mAutor);
        destin.setIdioma(mIdioma);
        destin.setUsuario(mUsuario);

        destin.setDataPublicacao( origin.getDataPublicacao() );
        destin.setEditora( origin.getEditora() );
        destin.setNumeroPaginas( origin.getNumeroPaginas() );
        destin.setGenero( origin.getGenero() );
        destin.setSinopse( origin.getSinopse() );
        destin.setValor( origin.getValor() );

        return destin;

    }

}
