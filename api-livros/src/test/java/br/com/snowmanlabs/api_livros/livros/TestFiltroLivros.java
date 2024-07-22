package br.com.snowmanlabs.api_livros.livros;

import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.mock.MockTests;
import br.com.snowmanlabs.api_livros.request.Requests;
import io.restassured.response.Response;

@SpringBootTest
@ActiveProfiles("development")
public class TestFiltroLivros {

    private String nomeAutor, tituloLivro;

    @BeforeEach
    public void preTests(){
        nomeAutor = "Fernando Bino Machado";
        tituloLivro = "Batatinha Frita 1 - 2 - 3";
    }
    
    @Test
    @Order(1)
    public void testCriarEFiltrarLivroPorNomeAutor(){

        //atualiza autor para usar na criação do livro
        atualizarAutor();

        //cria o livro relacionado ao autor
        LivroDTO dto = MockTests.getLivroDTO();

        dto.setId(null);
        dto.setAutorId(1L);

        Response response = Requests
            .responsePost(String.format(PATH_LIVRO, "criar"), dto);

        response
            .then()
            .assertThat()
            .statusCode(201);

        //realiza filtro dos livros pelo nome do autor,
        //para aplicar teste da funcionalidade do filtro
        Response responseFiltro = Requests
            .responseGet(
                String.format(PATH_LIVRO, "listar-filtro"),
                Map.of("nomeAutor", nomeAutor)
            );

        responseFiltro
            .then()
            .assertThat()
            .statusCode(200);
        

    }

    @Test
    @Order(2)
    public void testCriarEFiltrarLivroPorTituloLivro(){

        //atualiza autor para usar na criação do livro
        atualizarAutor();

        //cria o livro relacionado ao autor
        LivroDTO dto = MockTests.getLivroDTO();

        dto.setId(null);
        dto.setAutorId(1L);
        dto.setTitulo(tituloLivro);

        Response response = Requests
            .responsePost(String.format(PATH_LIVRO, "criar"), dto);

        response
            .then()
            .assertThat()
            .statusCode(201);

        //realiza filtro dos livros pelo nome do autor,
        //para aplicar teste da funcionalidade do filtro
        Response responseFiltro = Requests
            .responseGet(
                String.format(PATH_LIVRO, "listar-filtro"),
                Map.of("tituloLivro", tituloLivro)
            );

        responseFiltro
            .then()
            .assertThat()
            .statusCode(200);
        

    }

    @Test
    @Order(3)
    public void testParametrosVazios(){

        Response responseFiltro = Requests
            .responseGet(String.format(PATH_LIVRO, "listar-filtro"));

        responseFiltro
            .then()
            .assertThat()
            .statusCode(400);

    }

    @Test
    @Order(3)
    public void testParametrosEnviadosAoMesmoTempo(){

        Response responseFiltro = Requests
            .responseGet(
                String.format(PATH_LIVRO, "listar-filtro"),
                Map.of(
                    "tituloLivro", tituloLivro,
                    "nomeAutor", nomeAutor
                )
            );

        responseFiltro
            .then()
            .assertThat()
            .statusCode(400);

    }

    private void atualizarAutor(){

        AutorDTO dto = MockTests.getAutorDTO();

        dto.setId(1L);
        dto.setNome(nomeAutor);

        Response response = Requests
            .responsePut(String.format(PATH_AUTHOR, "atualizar"), dto);

        response
            .then()
            .assertThat()
            .statusCode(200);

    }

}
