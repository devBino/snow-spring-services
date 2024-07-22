package br.com.snowmanlabs.api_livros.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;

/**
 * Padroniza retorno de mensagens para responses Http Status
 */
@Component
public class MensagemHttpStatusProvider {
    
    /**
     * Retorna mensagem generica para serialização no reponse
     * @param mensagem
     * @return
     */
    public Map<String, String> getGenericMensagemResponse(final String mensagem){
        return Map.of("mensagem", mensagem);
    }

    /**
     * Recebe um objeto e retorna response generico para status created
     * @param body
     * @return
     */
    public ResponseEntity<?> getGenericResponseCreated(final Object body){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
    }

    /**
     * Recebe um objeto e retorna response generico para status sucess
     * @param body
     * @return
     */
    public ResponseEntity<?> getGenericResponseSuccess(final Object body){
        return ResponseEntity.ok().body(body);
    }

    /**
     * Retorna response generico para status not found
     * @return
     */
    public ResponseEntity<?> getGenericResponseNotFound(){
        return ResponseEntity.notFound().build();
    }

    /**
     * Recebe uma mensagem e retorna status not found com uma 
     * mensagem informativa
     * @param mensagem
     * @return
     */
    public ResponseEntity<?> getGenericResponseNotFound(final String mensagem){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body( getGenericMensagemResponse(mensagem) );
    }

    /**
     * Recebe um objeto e retorna serializado com status not found.
     * @param body
     * @return
     */
    public ResponseEntity<?> getCustomizedResponseNotFound(final Object body){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body( body );
    }

    /**
     * Retorna generico para erro interno da aplicação
     * @return
     */
    public ResponseEntity<?> getGenericResponseInternalServerError(){

        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                .body( getGenericMensagemResponse("Ocorreu um erro interno na aplicação, se o erro persistir por favor entre em contato com suporte") );

    }

    /**
     * No content.
     * @return
     */
    public ResponseEntity<?> getNoContent(){
        return ResponseEntity.noContent().build();
    }

    /**
     * Retorna response generico para Bad Request
     * @return
     */
    public ResponseEntity<?> getGenericBadRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .build();
    }

    /**
     * Retorna response customizado para Bad Request
     * @return
     */
    public ResponseEntity<?> getCustomizedBadRequest(final Object body){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(body);
    }

    /**
     * Retorna response com erros de validação
     * @param <T>
     * @param erros
     * @return
     */
    public <T> ResponseEntity<?> getResponseErrosValidations(Set<ConstraintViolation<T>> erros){
        
        Map<String, String> mapErros = new HashMap<>();
       
        erros.forEach(e -> {
            mapErros.put(
                e.getPropertyPath().toString(), 
                new StringBuilder()
                    .append("Campo [")
                    .append( e.getPropertyPath().toString() )
                    .append("] ")
                    .append( e.getMessage() )
                    .toString()
            );
        });

        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

}
