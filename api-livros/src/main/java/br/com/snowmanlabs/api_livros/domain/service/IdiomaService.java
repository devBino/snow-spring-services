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

import br.com.snowmanlabs.api_livros.domain.converter.IdiomaConverter;
import br.com.snowmanlabs.api_livros.domain.dto.GenericParamsIdDTO;
import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.domain.dto.ListaIdiomasDTO;
import br.com.snowmanlabs.api_livros.domain.dto.PaginateParamsDTO;
import br.com.snowmanlabs.api_livros.domain.model.MIdioma;
import br.com.snowmanlabs.api_livros.domain.repository.IdiomaRepository;
import jakarta.validation.ConstraintViolation;

/**
 * Serve a API respondendo as requisições da camada 
 * de controllers da entidade Idioma
 */
@Service
public class IdiomaService
    extends GenericService {

    @Autowired
    private IdiomaRepository repository;

    @Autowired
    private IdiomaConverter converter;

    @Autowired
    private LocalValidatorFactoryBean validator;

    public IdiomaService(){
        super("Idioma não encontrado");
    }

    /**
     * Cria novo Idioma
     * @param dto
     * @return
     */
    public ResponseEntity<?> criar(final IdiomaDTO dto){

        Set<ConstraintViolation<IdiomaDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        MIdioma model = converter.toModel(dto);

        model.setAtivo(1);
        
        return mensagemProvider.getGenericResponseCreated(
            converter.toDTO( repository.save(model) ) );

    }

    /**
     * Atualiza Idioma existente
     * @param dto
     * @return
     */
    public ResponseEntity<?> atualizar(final IdiomaDTO dto){

        Set<ConstraintViolation<IdiomaDTO>> erros = validator.validate(dto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MIdioma> optModel = repository.findById(dto.getId());

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        final MIdioma updatedModel = converter.toModel(dto);

        updatedModel.setId( optModel.get().getId() );

        return mensagemProvider.getGenericResponseSuccess( 
            converter.toDTO(repository.save(updatedModel)) );

    }

    /**
     * Retorna os dados de um Idioma pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> detalhar(final String id){

        GenericParamsIdDTO idDto = new GenericParamsIdDTO(id);

        Set<ConstraintViolation<GenericParamsIdDTO>> erros = validator.validate(idDto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MIdioma> optModel = repository.findById(Long.valueOf(id));

        if( !optModel.isPresent() ){
            return mensagemProvider.getGenericResponseNotFound(mensagemEntidadeNaoEncontrado);
        }

        return mensagemProvider.getGenericResponseSuccess( 
            converter.toDTO(optModel.get()) );

    }

    /**
     * Deleta um Idioma pelo id
     * @param id
     * @return
     */
    public ResponseEntity<?> deletar(final String id){

        GenericParamsIdDTO idDto = new GenericParamsIdDTO(id);

        Set<ConstraintViolation<GenericParamsIdDTO>> erros = validator.validate(idDto);

        if(!erros.isEmpty()){
            return mensagemProvider.getResponseErrosValidations(erros);
        }

        final Optional<MIdioma> optModel = repository.findById(Long.valueOf(id));

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

        final Page<IdiomaDTO> registros = repository
            .findAll(paginacao)
            .map(converter::toDTO);

        final ListaIdiomasDTO dto = new ListaIdiomasDTO();

        dto.setIdiomas( registros.getContent() );
        dto.setTotalPaginas( registros.getTotalPages() );
        dto.setTotalRegistros( registros.getTotalElements() );

        return mensagemProvider.getGenericResponseSuccess( dto );
        
    }

}
