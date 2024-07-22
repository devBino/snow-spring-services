package br.com.snowmanlabs.api_livros.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.snowmanlabs.api_livros.domain.model.MLivro;

/**
 * JPA para manipular entidade Livro
 */
@Repository
public interface LivroRepository extends JpaRepository<MLivro, Long> {

    List<MLivro> findByAutorNome(String nome);

    List<MLivro> findByTitulo(String titulo);

}
