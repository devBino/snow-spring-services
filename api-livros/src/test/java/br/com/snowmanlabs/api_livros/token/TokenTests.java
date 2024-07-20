package br.com.snowmanlabs.api_livros.token;

import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.snowmanlabs.api_livros.domain.dto.TokenDTO;
import io.restassured.response.Response;

@SpringBootTest
@ActiveProfiles("development")
public class TokenTests {
    
    private String tokenGerado;
    private int statusCode;

    @BeforeEach
    public void preTests(){
        gerarToken();
    }

    @Test
    @Order(0)
    public void testGerarToken(){
        assertTrue(statusCode == 200);
        assertNotNull(tokenGerado);
    }

    @Test
    @Order(1)
    public void testPingPongAutenticado(){

        Response response = given()
            .port(SERVER_PORT)
            .header(HEADER_ORIGIN, ORIGIN)
            .header(HEADER_AUTHORIZATION, String.format("Bearer %s", tokenGerado))
            .get(PATH_TESTE_TOKEN);

        response
            .then()
            .assertThat()
            .statusCode(200);

    }

    private void gerarToken(){

        String endPoint = String.format(PATH_AUTH_TOKEN, "demo","demo");

        Response response = given()
            .port(SERVER_PORT)
            .header(HEADER_ORIGIN, ORIGIN)
            .get(endPoint);
            
        statusCode = response
            .then()
            .extract()
            .statusCode();
            
        TokenDTO dto = response
            .then()
            .extract()
            .as(TokenDTO.class);

        tokenGerado = dto.getToken();

    }

}
