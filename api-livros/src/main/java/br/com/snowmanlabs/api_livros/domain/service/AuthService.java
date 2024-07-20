package br.com.snowmanlabs.api_livros.domain.service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.snowmanlabs.api_livros.domain.model.MUsuario;
import br.com.snowmanlabs.api_livros.domain.repository.UsuarioRepository;
import br.com.snowmanlabs.api_livros.domain.dto.TokenDTO;
import br.com.snowmanlabs.api_livros.provider.MensagemHttpStatusProvider;
import br.com.snowmanlabs.api_livros.provider.TokenProvider;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

/**
 * Serviço que responde a camada de controller
 * para autenticar usuário e gerar um token
 */
@Service
public class AuthService {

    @Autowired
    private MensagemHttpStatusProvider mensagemProvider;
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenProvider tokenProvider;

    /**
     * Recebe as credenciais do usuário e as usa para gerar o token
     * @param user
     * @param password
     * @return
     */
    public ResponseEntity<?> gerarToken(final String user, final String password){

        TokenDTO dto = new TokenDTO();

        dto.setMensagem("Não foi possível obter um token com as credenciais informadas");

        try{

            final String encodePass = Base64.getEncoder().encodeToString(password.getBytes());

            final MUsuario mUsuario = repository.findUserByCredentials(user, encodePass);

            if(Objects.isNull(mUsuario)){
                return mensagemProvider.getCustomizedResponseNotFound(dto);
            }

            final UserDetails userDetails = new User(mUsuario.getUsuario(), mUsuario.getPassword(), 
                new ArrayList<>());

            final String token = tokenProvider.generateToken(userDetails);

            dto.setSucesso(true);
            dto.setMensagem("Token Gerado com sucesso");
            dto.setToken(token);
            
        }catch(final Exception exception){
            return mensagemProvider.getGenericResponseInternalServerError();
        }

        return mensagemProvider.getGenericResponseSucess(dto);

    }

}
