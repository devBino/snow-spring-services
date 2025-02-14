package br.com.snowmanlabs.api_livros.domain.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.snowmanlabs.api_livros.domain.converter.AutorConverter;
import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.dto.GenericParamsIdDTO;
import br.com.snowmanlabs.api_livros.domain.dto.ListaAutoresDTO;
import br.com.snowmanlabs.api_livros.domain.dto.PaginateParamsDTO;
import br.com.snowmanlabs.api_livros.domain.model.MAutor;
import br.com.snowmanlabs.api_livros.domain.repository.AutorRepository;
import jakarta.validation.ConstraintViolation;

/**
 * Serve a API respondendo as requisições da camada 
 * de controllers da entidade Autor
 */
@Service
public class AutorService
    extends GenericService {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private AutorConverter converter;

    @Autowired
    private LocalValidatorFactoryBean validator;

    public AutorService(){
        super("Autor não encontrado");
    }

    /**
     * Cria novo Autor
     * @param dto
     * @return
     */
    public ResponseEntity<?> criar(final AutorDTO dto){

        Set<ConstraintViolation<AutorDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }
        
        MAutor model = converter.toModel(dto);

        model.setAtivo(1);

        return mensagemProvider.getGenericResponseCreated(
            converter.toDTO( repository.save(model) ) );

    }

    /**
     * Atualiza Autor existente
     * @param dto
     * @return
     */
    public ResponseEntity<?> atualizar(final AutorDTO dto){

        Set<ConstraintViolation<AutorDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }
        
        final Optional<MAutor> optModel = repository.findById(dto.getId());

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        final MAutor updatedModel = converter.toModel(dto);

        updatedModel.setId( optModel.get().getId() );

        return mensagemProvider.getGenericResponseSuccess( 
            converter.toDTO(repository.save(updatedModel)) );

    }

    /**
     * Retorna os dados de um Autor pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> detalhar(final String id){

        GenericParamsIdDTO idDto = new GenericParamsIdDTO(id);

        Set<ConstraintViolation<GenericParamsIdDTO>> erros = validator.validate(idDto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MAutor> optModel = repository.findById(Long.valueOf(id));

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        return mensagemProvider.getGenericResponseSuccess( 
            converter.toDTO(optModel.get()) );

    }

    /**
     * Deleta um Autor pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> deletar(final String id){

        GenericParamsIdDTO idDto = new GenericParamsIdDTO(id);

        Set<ConstraintViolation<GenericParamsIdDTO>> erros = validator.validate(idDto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MAutor> optModel = repository.findById(Long.valueOf(id));

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

        PaginateParamsDTO paramsDTO = new PaginateParamsDTO(page, limit);

        Set<ConstraintViolation<PaginateParamsDTO>> erros = validator.validate(paramsDTO);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        Integer vPage = Integer.valueOf(page);
        
        Pageable paginacao = PageRequest.of(--vPage, Integer.valueOf(limit));

        final Page<AutorDTO> registros = repository
            .findAll(paginacao)
            .map(converter::toDTO);

        final ListaAutoresDTO dto = new ListaAutoresDTO();

        dto.setAutores( registros.getContent() );
        dto.setTotalPaginas( registros.getTotalPages() );
        dto.setTotalRegistros( registros.getTotalElements() );

        return mensagemProvider.getGenericResponseSuccess( dto );
        
    }

}
