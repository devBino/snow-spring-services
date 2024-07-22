package br.com.snowmanlabs.api_livros.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.snowmanlabs.api_livros.domain.model.MLivro;

/**
 * JPA para manipular entidade Livro
 */
@Repository
public interface LivroRepository extends JpaRepository<MLivro, Long> {}
