package br.com.snowmanlabs.api_livros.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.domain.model.MAutor;
import br.com.snowmanlabs.api_livros.domain.model.MIdioma;
import br.com.snowmanlabs.api_livros.domain.model.MLivro;
import br.com.snowmanlabs.api_livros.domain.model.MUsuario;

public class MockTests {
    
    public static MIdioma getIdiomaModel(){
        
        MIdioma mk = new MIdioma();

        mk.setId(1L);
        mk.setAtivo(1);
        mk.setNome("Inglês (Unitad States)");
        mk.setCodIdioma("en");
        mk.setCodRegiao("en_US");

        return mk;

    }

    public static IdiomaDTO getIdiomaDTO(){

        IdiomaDTO mk = new IdiomaDTO();

        String hashTeste = UUID.randomUUID().toString();

        String nomeIdioma = hashTeste.length() > 20
            ? hashTeste.substring(0, 20) : hashTeste;

        mk.setId(1L);
        mk.setAtivo(1);
        mk.setNome(nomeIdioma);
        mk.setCodIdioma(hashTeste.substring(0, 4));
        mk.setCodRegiao(hashTeste.substring(0, 4));

        return mk;

    }


    public static MAutor getAutorModel(){
        
        MAutor mk = new MAutor();

        mk.setId(1L);
        mk.setAtivo(1);
        mk.setNome("Autor Teste 1");

        return mk;

    }

    public static AutorDTO getAutorDTO(){

        AutorDTO mk = new AutorDTO();

        String hashTeste = UUID.randomUUID().toString();

        String nomeAutor = hashTeste.length() > 15
            ? hashTeste.substring(0, 15) : hashTeste;

        mk.setId(1L);
        mk.setAtivo(1);
        mk.setNome(nomeAutor);

        return mk;

    }

    public static MLivro getLivroModel(){
        
        MLivro mk = new MLivro();

        mk.setId(1L);
        mk.setAtivo(1);
        mk.setTitulo("O Homem mais Rico da Babilônia");

        mk.setAutor(new MAutor());
        mk.getAutor().setId(1L);

        mk.setIdioma(new MIdioma());
        mk.getIdioma().setId(1L);

        mk.setUsuario(new MUsuario());
        mk.getUsuario().setId(1L);

        mk.setDataPublicacao(LocalDateTime.now());
        mk.setEditora("Editora Teste Model");
        mk.setNumeroPaginas(105);
        mk.setGenero("Finanças");
        mk.setSinopse("Excelente Livro para te ensinar a economizar seu dinheiro...");
        mk.setValor(new BigDecimal("199.78"));

        return mk;

    }

    public static LivroDTO getLivroDTO(){

        LivroDTO mk = new LivroDTO();
        
        mk.setId(1L);
        mk.setAtivo(1);
        mk.setTitulo("O Homem mais Rico da Babilônia");

        mk.setAutorId(1L);
        mk.setIdiomaId(1L);
        mk.setUsuarioId(1L);

        mk.setDataPublicacao(LocalDateTime.now());
        mk.setEditora("Editora Teste Model");
        mk.setNumeroPaginas(105);
        mk.setGenero("Finanças");
        mk.setSinopse("Excelente Livro para te ensinar a economizar seu dinheiro...");
        mk.setValor(new BigDecimal("199.78"));

        return mk;
        
    }

}
