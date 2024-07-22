package br.com.snowmanlabs.api_livros.domain.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.snowmanlabs.api_livros.domain.model.MUsuario;
import br.com.snowmanlabs.api_livros.domain.repository.UsuarioRepository;

/**
 * Permite carregar um {@code UserDetails} pelo campo
 * user em {@code UsuarioRepository}
 */
@Service
public class TokenUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        
        final MUsuario mUsuario = repository.findUserByDescUser(user);

        if( Objects.nonNull(mUsuario)
            && mUsuario.getUsuario().equals(user)){
                return new User(user, mUsuario.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
        
    }

}
