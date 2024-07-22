package br.com.snowmanlabs.api_livros.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.snowmanlabs.api_livros.domain.model.MIdioma;

/**
 * JPA para manipular entidade Idioma
 */
@Repository
public interface IdiomaRepository extends JpaRepository<MIdioma, Long> {}
