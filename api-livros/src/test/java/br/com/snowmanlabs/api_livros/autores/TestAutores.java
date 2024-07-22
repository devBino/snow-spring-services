package br.com.snowmanlabs.api_livros.autores;

import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.mock.MockTests;
import br.com.snowmanlabs.api_livros.request.Requests;
import io.restassured.response.Response;

@SpringBootTest
@ActiveProfiles("development")
public class TestAutores {

    @Test
    @Order(1)
    public void testCriarAutor(){

        AutorDTO dto = MockTests.getAutorDTO();

        dto.setId(null);

        Response response = Requests
            .responsePost(String.format(PATH_AUTHOR, "criar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(201);

        AutorDTO dtoRetornado = response
            .then()
            .extract()
            .as(AutorDTO.class);

        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getNome().equals( dtoRetornado.getNome() ) );

    }

    @Test
    @Order(2)
    public void testUpdateAutor(){

        AutorDTO dto = MockTests.getAutorDTO();

        dto.setId(1L);

        Response response = Requests
            .responsePut(String.format(PATH_AUTHOR, "atualizar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(200);

        AutorDTO dtoRetornado = response
            .then()
            .extract()
            .as(AutorDTO.class);

        assertTrue( dto.getId().longValue() == dtoRetornado.getId().longValue() );
        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getNome().equals( dtoRetornado.getNome() ) );

    }

    @Test
    @Order(3)
    public void testDelete(){

        AutorDTO dto = criarERetornarRegistro();

        final String endPointDelete = new StringBuilder()
            .append( String.format(PATH_AUTHOR, "deletar/") )
            .append( dto.getId() )
            .toString();

        Response responseDelete = Requests
            .responseDelete(endPointDelete);

        responseDelete
            .then()
            .assertThat()
            .statusCode(204);

    }

    @Test
    @Order(4)
    public void testDetalharPorId(){

        final String endPointDetalhar = new StringBuilder()
            .append( String.format(PATH_AUTHOR, "detalhar/") )
            .append( criarRegistroERetornarId() )
            .toString();

        Response response = Requests
            .responseGet(endPointDetalhar);

        response
            .then()
            .assertThat()
            .statusCode(200);

        AutorDTO dtoRetornado = response
            .then()
            .extract()
            .as(AutorDTO.class);

        assertTrue( Objects.nonNull( dtoRetornado.getId() ) );
        assertTrue( dtoRetornado.getAtivo() > 0 );
        assertTrue( Objects.nonNull( dtoRetornado.getNome() ) );

    }

    private AutorDTO criarERetornarRegistro(){

        AutorDTO dto = MockTests.getAutorDTO();

        dto.setId(null);

        Response response = Requests
            .responsePost(String.format(PATH_AUTHOR, "criar"), dto);

        return response
            .then()
            .extract()
            .as(AutorDTO.class);

    }

    private long criarRegistroERetornarId(){

        AutorDTO dto = MockTests.getAutorDTO();

        Response response = Requests
            .responsePost(String.format(PATH_AUTHOR, "criar"), dto);

        AutorDTO dtoCriado = response
            .then()
            .extract()
            .as(AutorDTO.class);

        return dtoCriado.getId();

    }


}

