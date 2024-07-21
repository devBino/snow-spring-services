package br.com.snowmanlabs.api_livros.provider;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Padroniza retorno de mensagens para responses Http Status
 */
@Component
public class MensagemHttpStatusProvider {
    
    public Map<String, String> getGenericMensagemResponse(final String mensagem){
        return Map.of("mensagem", mensagem);
    }

    public ResponseEntity<?> getGenericResponseCreated(final Object body){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
    }

    public ResponseEntity<?> getGenericResponseSucess(final Object body){
        return ResponseEntity.ok().body(body);
    }

    public ResponseEntity<?> getGenericResponseNotFound(){
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> getGenericResponseNotFound(final String mensagem){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body( getGenericMensagemResponse(mensagem) );
    }

    public ResponseEntity<?> getCustomizedResponseNotFound(final Object body){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body( body );
    }

    public ResponseEntity<?> getGenericResponseInternalServerError(){

        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                .body( getGenericMensagemResponse("Ocorreu um erro interno na aplicação, se o erro persistir por favor entre em contato com suporte") );

    }

    public ResponseEntity<?> getNoContent(){
        return ResponseEntity.noContent().build();
    }

}
