package br.com.snowmanlabs.api_livros.domain.service;

import static br.com.snowmanlabs.api_livros.domain.service.constants.ServiceConstants.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.snowmanlabs.api_livros.domain.converter.LivroConverter;
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.domain.dto.ListaLivrosDTO;
import br.com.snowmanlabs.api_livros.domain.model.MLivro;
import br.com.snowmanlabs.api_livros.domain.repository.LivroRepository;
import jakarta.validation.ConstraintViolation;

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

    @Autowired
    private LocalValidatorFactoryBean validator;

    public LivroService(){
        super("Livro não encontrado");
    }

    /**
     * Cria novo Livro
     * @param dto
     * @return
     */
    public ResponseEntity<?> criar(final LivroDTO dto){

        Set<ConstraintViolation<LivroDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        MLivro model = converter.toModel(dto);

        model.setAtivo(1);

        return mensagemProvider.getGenericResponseCreated(
            converter.toDTO( repository.save(model) ) );

    }

    /**
     * Atualiza Livro existente
     * @param dto
     * @return
     */
    public ResponseEntity<?> atualizar(final LivroDTO dto){

        Set<ConstraintViolation<LivroDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MLivro> optModel = repository.findById(dto.getId());

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        final MLivro updatedModel = converter.toModel(dto);

        updatedModel.setId( optModel.get().getId() );

        return mensagemProvider.getGenericResponseSuccess( 
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

        return mensagemProvider.getGenericResponseSuccess( 
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

        MLivro model = optModel.get();

        StringBuilder detalhesLivro = new StringBuilder();

        detalhesLivro.append(String.format("Titulo: %s\n", model.getTitulo()));
        detalhesLivro.append(String.format("Autor: %s\n", model.getAutor().getNome()));
        detalhesLivro.append(String.format("Idioma: %s\n", model.getIdioma().getNome()));
        detalhesLivro.append(String.format("Codigo Idioma: %s\n", model.getIdioma().getNome()));
        detalhesLivro.append(String.format("Total Paginas: %s\n", model.getNumeroPaginas()));

        repository.deleteById( model.getId() );

        String msgRetorno = detalhesLivro.toString();       

        return mensagemProvider.getGenericResponseSuccess(msgRetorno);

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

        return mensagemProvider.getGenericResponseSuccess( dto );
        
    }

    /**
     * Retorna listagem de livros de acordo com filtros
     * @param nome
     * @return
     */
    public ResponseEntity<?> listarFiltros(Map<String, String> filtros){

        String nomeAutor = filtros.get(FILTRO_NOME_AUTOR);
        String tituloLivro = filtros.get(FILTRO_TITULO_LIVRO);

        //caso nenhum parametro tenha sido enviado
        if( nomeAutor.isEmpty() && tituloLivro.isEmpty() ){
            return mensagemProvider.getCustomizedBadRequest(
                mensagemProvider.getGenericMensagemResponse("Enviar pelo menos um dos parâmetros: [nomeAutor ou tituloLivro]"));
        }

        //caso os dois parametros tenha sido enviados
        if( !nomeAutor.isEmpty() && !tituloLivro.isEmpty() ){
            return mensagemProvider.getCustomizedBadRequest(
                mensagemProvider.getGenericMensagemResponse("Não é permitido enviar os dois parâmetros [nomeAutor e tituloLivro] ao mesmo tempo."));
        }

        ResponseEntity<?> responseFiltro = mensagemProvider.getGenericResponseNotFound("Nenhum Livro encontrado...");

        //caso tenha sido enviado nomeAutor
        if( !nomeAutor.isEmpty() ){
            responseFiltro = getLivrosPorNomeAutor(nomeAutor);
        }else if( !tituloLivro.isEmpty() ){
            responseFiltro = getLivrosPorTituloLivro(tituloLivro);
        }
        
        return responseFiltro;

    }

    private ResponseEntity<?> getLivrosPorNomeAutor(String nomeAutor){

        List<MLivro> registros = repository
            .findByAutorNome(nomeAutor);

        if( registros.isEmpty() ){

            return mensagemProvider.getGenericResponseNotFound(new StringBuilder()
                .append("Livros não encontrados para o(a) autor(a) ")
                .append(nomeAutor)
                .toString());

        }

        return mensagemProvider.getGenericResponseSuccess(registros
            .stream()
            .map(converter::toDTO)
            .collect(Collectors.toList()));

    }

    private ResponseEntity<?> getLivrosPorTituloLivro(String tituloLivro){

        List<MLivro> registros = repository
            .findByTitulo(tituloLivro);

        if( registros.isEmpty() ){

            return mensagemProvider.getGenericResponseNotFound(new StringBuilder()
                .append("Livros não encontrados para título ")
                .append(tituloLivro)
                .toString());

        }

        return mensagemProvider.getGenericResponseSuccess(registros
            .stream()
            .map(converter::toDTO)
            .collect(Collectors.toList()));

    }

}
