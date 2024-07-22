package br.com.snowmanlabs.api_livros.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.snowmanlabs.api_livros.domain.model.MAutor;

/**
 * JPA para manipular entidade Autor
 */
public interface AutorRepository extends JpaRepository<MAutor, Long> {}
