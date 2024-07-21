package br.com.snowmanlabs.api_livros.idiomas;
import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.request.Requests;
import io.restassured.response.Response;

@SpringBootTest
@ActiveProfiles("development")
public class TestIdiomas {

    @Test
    @Order(1)
    public void testCriarIdioma(){

        IdiomaDTO dto = criarERetornarRegistro();

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(201);

        IdiomaDTO dtoRetornado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getNome().equals( dtoRetornado.getNome() ) );
        assertTrue( dto.getCodIdioma().equals( dtoRetornado.getCodIdioma() ) );
        assertTrue( dto.getCodRegiao().equals( dtoRetornado.getCodRegiao() ) );

    }

    @Test
    @Order(2)
    public void testUpdateIdioma(){

        IdiomaDTO dto = criarERetornarRegistro();

        Response response = Requests
            .responsePut(String.format(PATH_IDIOMA, "atualizar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(200);

        IdiomaDTO dtoRetornado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        assertTrue( dto.getId().longValue() == dtoRetornado.getId().longValue() );
        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getNome().equals( dtoRetornado.getNome() ) );
        assertTrue( dto.getCodIdioma().equals( dtoRetornado.getCodIdioma() ) );
        assertTrue( dto.getCodRegiao().equals( dtoRetornado.getCodRegiao() ) );

    }

    @Test
    @Order(3)
    public void testDelete(){

        IdiomaDTO dto = criarERetornarRegistro();

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(201);

        IdiomaDTO dtoRetornado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        final String endPointDelete = new StringBuilder()
            .append( String.format(PATH_IDIOMA, "deletar/") )
            .append( dtoRetornado.getId() )
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
            .append( String.format(PATH_IDIOMA, "detalhar/") )
            .append( criarRegistroERetornarId() )
            .toString();

        Response response = Requests
            .responseGet(endPointDetalhar);

        response
            .then()
            .assertThat()
            .statusCode(200);

        IdiomaDTO dtoRetornado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        assertTrue( Objects.nonNull( dtoRetornado.getId() ) );
        assertTrue( dtoRetornado.getAtivo() > 0 );
        assertTrue( Objects.nonNull( dtoRetornado.getNome() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getCodIdioma() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getCodRegiao() ) );

    }

    private IdiomaDTO criarERetornarRegistro(){

        IdiomaDTO dto = new IdiomaDTO();

        String hashTeste = UUID.randomUUID().toString();

        String nomeIdioma = hashTeste.length() > 20
            ? hashTeste.substring(0, 20) : hashTeste;

        dto.setAtivo(1);
        dto.setNome(nomeIdioma);
        dto.setCodIdioma(hashTeste.substring(0, 4));
        dto.setCodRegiao(hashTeste.substring(0, 4));

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);

        return response
            .then()
            .extract()
            .as(IdiomaDTO.class);

    }

    private long criarRegistroERetornarId(){

        IdiomaDTO dto = new IdiomaDTO();

        String hashTeste = UUID.randomUUID().toString();

        String nomeIdioma = hashTeste.length() > 20
            ? hashTeste.substring(0, 20) : hashTeste;

        dto.setAtivo(1);
        dto.setNome(nomeIdioma);
        dto.setCodIdioma(hashTeste.substring(0, 4));
        dto.setCodRegiao(hashTeste.substring(0, 4));

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);

        IdiomaDTO dtoCriado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        return dtoCriado.getId();

    }


}
