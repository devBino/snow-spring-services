package br.com.snowmanlabs.api_livros.livros;

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
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.mock.MockTests;
import br.com.snowmanlabs.api_livros.request.Requests;
import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class TestLivros {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    @Order(0)
    public void testValidacoesLivroDTO(){
        Set<ConstraintViolation<LivroDTO>> erros = validator.validate(new LivroDTO());
        assertTrue(!erros.isEmpty());
    }

    @Test
    @Order(1)
    public void testCriarLivro(){

        LivroDTO dto = MockTests.getLivroDTO();

        dto.setId(null);

        Response response = Requests
            .responsePost(String.format(PATH_LIVRO, "criar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(201);

        LivroDTO dtoRetornado = response
            .then()
            .extract()
            .as(LivroDTO.class);

        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getTitulo().equals( dtoRetornado.getTitulo() ) );
        assertTrue( dto.getAutorId().longValue() == dtoRetornado.getAutorId().longValue() );
        assertTrue( dto.getIdiomaId().longValue() == dtoRetornado.getIdiomaId().longValue() );
        assertTrue( dto.getUsuarioId().longValue() == dtoRetornado.getUsuarioId().longValue() );
        assertTrue( dto.getEditora().equals( dtoRetornado.getEditora() ) );
        assertTrue( dto.getNumeroPaginas() == dtoRetornado.getNumeroPaginas() );
        assertTrue( dto.getGenero().equals( dtoRetornado.getGenero() ) );
        assertTrue( dto.getSinopse().equals( dtoRetornado.getSinopse() ) );
        assertTrue( dto.getValor().compareTo( dtoRetornado.getValor() ) == 0 );

    }

    @Test
    @Order(2)
    public void testUpdateLivro(){

        LivroDTO dto = criarERetornarRegistro();

        Response response = Requests
            .responsePut(String.format(PATH_LIVRO, "atualizar"), dto);
        
        response
            .then()
            .assertThat()
            .statusCode(200);

        LivroDTO dtoRetornado = response
            .then()
            .extract()
            .as(LivroDTO.class);

        assertTrue( dto.getAtivo() == dtoRetornado.getAtivo() );
        assertTrue( dto.getTitulo().equals( dtoRetornado.getTitulo() ) );
        assertTrue( dto.getAutorId().longValue() == dtoRetornado.getAutorId().longValue() );
        assertTrue( dto.getIdiomaId().longValue() == dtoRetornado.getIdiomaId().longValue() );
        assertTrue( dto.getUsuarioId().longValue() == dtoRetornado.getUsuarioId().longValue() );
        assertTrue( dto.getEditora().equals( dtoRetornado.getEditora() ) );
        assertTrue( dto.getNumeroPaginas() == dtoRetornado.getNumeroPaginas() );
        assertTrue( dto.getGenero().equals( dtoRetornado.getGenero() ) );
        assertTrue( dto.getSinopse().equals( dtoRetornado.getSinopse() ) );
        assertTrue( dto.getValor().compareTo( dtoRetornado.getValor() ) == 0 );

    }

    @Test
    @Order(3)
    public void testDelete(){

        LivroDTO dto = criarERetornarRegistro();

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_LIVRO, "deletar/") )
            .append( dto.getId() )
            .toString();

        Response responseDelete = Requests
            .responseDelete(endPoint);

        responseDelete
            .then()
            .assertThat()
            .statusCode(200);

    }

    @Test
    @Order(4)
    public void testDetalharPorId(){

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_LIVRO, "detalhar/") )
            .append( criarRegistroERetornarId() )
            .toString();

        Response response = Requests
            .responseGet(endPoint);

        response
            .then()
            .assertThat()
            .statusCode(200);

        LivroDTO dtoRetornado = response
            .then()
            .extract()
            .as(LivroDTO.class);

        assertTrue( Objects.nonNull( dtoRetornado.getId() ) );
        assertTrue( dtoRetornado.getAtivo() > 0 );
        assertTrue( Objects.nonNull( dtoRetornado.getTitulo() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getAutorId() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getIdiomaId() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getUsuarioId() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getDataPublicacao() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getEditora() ) );
        assertTrue( dtoRetornado.getNumeroPaginas() > 0 );
        assertTrue( Objects.nonNull( dtoRetornado.getGenero() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getSinopse() ) );
        assertTrue( Objects.nonNull( dtoRetornado.getValor() ) );

    }

    @Test
    @Order(5)
    public void testDeleteIdInvalido(){

        final String endPoint = new StringBuilder()
            .append( String.format(PATH_LIVRO, "deletar/") )
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
            .append( String.format(PATH_LIVRO, "detalhar/") )
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
            String.format(PATH_LIVRO, "listar"), 
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

    private LivroDTO criarERetornarRegistro(){

        LivroDTO dto = MockTests.getLivroDTO();

        dto.setId(null);

        Response response = Requests
            .responsePost(String.format(PATH_LIVRO, "criar"), dto);

        return response
            .then()
            .extract()
            .as(LivroDTO.class);

    }

    private long criarRegistroERetornarId(){

        LivroDTO dto = MockTests.getLivroDTO();

        Response response = Requests
            .responsePost(String.format(PATH_LIVRO, "criar"), dto);

        LivroDTO dtoCriado = response
            .then()
            .extract()
            .as(LivroDTO.class);

        return dtoCriado.getId();

    }


}
