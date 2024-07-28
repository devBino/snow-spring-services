package br.com.snowmanlabs.servico_email.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.snowmanlabs.servico_email.domain.model.MUsuario;

/**
 * Repository para entidade Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<MUsuario, Long> {}
