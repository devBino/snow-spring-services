package br.com.snowmanlabs.api_livros.idiomas;
import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.snowmanlabs.api_livros.configs.TestConfigs;
import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.mock.MockTests;
import br.com.snowmanlabs.api_livros.request.Requests;
import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class TestIdiomas {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    @Order(0)
    public void testValidacoesIdiomaDTO(){
        Set<ConstraintViolation<IdiomaDTO>> erros = validator.validate(new IdiomaDTO());
        assertTrue(!erros.isEmpty());
    }

    @Test
    @Order(1)
    public void testCriarIdioma(){

        IdiomaDTO dto = MockTests.getIdiomaDTO();

        dto.setId(null);

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

        IdiomaDTO dto = MockTests.getIdiomaDTO();

        dto.setId(1L);

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

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_IDIOMA, "deletar/") )
            .append( dto.getId() )
            .toString();

        Response responseDelete = Requests
            .responseDelete(endPoint);

        responseDelete
            .then()
            .assertThat()
            .statusCode(204);

    }

    @Test
    @Order(4)
    public void testDetalharPorId(){

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_IDIOMA, "detalhar/") )
            .append( criarRegistroERetornarId() )
            .toString();

        Response response = Requests
            .responseGet(endPoint);

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

    @Test
    @Order(5)
    public void testDeleteIdInvalido(){

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_IDIOMA, "deletar/") )
            .append("abc")
            .toString();

        Response responseDelete = Requests
            .responseDelete(endPoint);

        responseDelete
            .then()
            .assertThat()
            .statusCode(400);

    }

    @Test
    @Order(6)
    public void testDetalharPorIdInvalido(){

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_IDIOMA, "detalhar/") )
            .append("abc")
            .toString();

        Response response = Requests
            .responseGet(endPoint);

        response
            .then()
            .assertThat()
            .statusCode(400);

    }

    @Test
    @Order(7)
    public void testPaginacaoInvalida(){

        Response response = Requests.responseGet(
            String.format(PATH_IDIOMA, "listar"), 
            Map.of(
                "limite","abc",
                "page","abc"
            )
        );

        response
            .then()
            .assertThat()
            .statusCode(400);
            
    }

    private IdiomaDTO criarERetornarRegistro(){

        IdiomaDTO dto = MockTests.getIdiomaDTO();

        dto.setId(null);

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);

        return response
            .then()
            .extract()
            .as(IdiomaDTO.class);

    }

    private long criarRegistroERetornarId(){

        IdiomaDTO dto = MockTests.getIdiomaDTO();

        Response response = Requests
            .responsePost(String.format(PATH_IDIOMA, "criar"), dto);

        IdiomaDTO dtoCriado = response
            .then()
            .extract()
            .as(IdiomaDTO.class);

        return dtoCriado.getId();

    }


}
