package br.com.snowmanlabs.api_livros.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.snowmanlabs.api_livros.domain.converter.LivroConverter;
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.domain.dto.ListaLivrosDTO;
import br.com.snowmanlabs.api_livros.domain.model.MLivro;
import br.com.snowmanlabs.api_livros.domain.repository.LivroRepository;

/**
 * Serve a API respondendo as requisições da camada 
 * de controllers da entidade Livro
 */
@Service
public class LivroService
    extends GenericService {
    
    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroConverter converter;

    public LivroService(){
        super("Livro não encontrado");
    }

    /**
     * Cria novo Livro
     * @param dto
     * @return
     */
    public ResponseEntity<?> criar(final LivroDTO dto){

        MLivro model = converter.toModel(dto);

        return mensagemProvider.getGenericResponseCreated(
            converter.toDTO( repository.save(model) ) );

    }

    /**
     * Atualiza Livro existente
     * @param dto
     * @return
     */
    public ResponseEntity<?> atualizar(final LivroDTO dto){

        final Optional<MLivro> optModel = repository.findById(dto.getId());

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        final MLivro updatedModel = converter.toModel(dto);

        updatedModel.setId( optModel.get().getId() );

        return mensagemProvider.getGenericResponseSucess( 
            converter.toDTO( repository.save(updatedModel)) );

    }

    /**
     * Retorna os dados de um Livro pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> detalhar(final String id){

        final Optional<MLivro> optModel = repository.findById(Long.valueOf(id));

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        return mensagemProvider.getGenericResponseSucess( 
            converter.toDTO(optModel.get()) );

    }

    /**
     * Deleta um Livro pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> deletar(final String id){

        final Optional<MLivro> optModel = repository.findById(Long.valueOf(id));

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        repository.deleteById( optModel.get().getId() );

        return mensagemProvider.getNoContent();

    }

    /**
     * Retorna registros de maneira paginada, de acordo com parametros recebidos
     * @param pagem
     * @param limit
     * @return
     */
    public ResponseEntity<?> listarRegistrosPaginacao(String page, String limit){

        Integer vPage = Integer.valueOf(page);
        
        Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));

        final Page<LivroDTO> registros = repository
            .findAll(paginacao)
            .map(converter::toDTO);

        final ListaLivrosDTO dto = new ListaLivrosDTO();

        dto.setLivros( registros.getContent() );
        dto.setTotalPaginas( registros.getTotalPages() );
        dto.setTotalRegistros( registros.getTotalElements() );

        return mensagemProvider.getGenericResponseSucess( dto );
        
    }

}
