package br.com.snowmanlabs.api_livros.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.snowmanlabs.api_livros.domain.model.MUsuario;

/**
 * JPA para manipular entidade usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<MUsuario, Long> {
    
    @Query("from MUsuario where usuario = :usuario and password = :password")
    MUsuario findUserByCredentials(@Param("usuario") String usuario, @Param("password") String password);

    @Query("from MUsuario where usuario = :usuario")
    MUsuario findUserByDescUser(@Param("usuario") String usuario);
    
}

