package br.com.snowmanlabs.api_livros.request;

import static br.com.snowmanlabs.api_livros.constants.TestConstants.*;
import static io.restassured.RestAssured.given;

import br.com.snowmanlabs.api_livros.domain.dto.TokenDTO;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Facilita execução de requests para esse projeto de testes
 */
public class Requests {
    
    /**
     * Recebe um end point e realiza requisição GET, 
     * retornando response do GET realizado
     * @param pathEndPoint
     * @return
     */
    public static Response responseGet(final String pathEndPoint){

        RequestSpecification request = given()
            .basePath(pathEndPoint)
            .port(SERVER_PORT)
            .contentType("application/json")
            .header(HEADER_ORIGIN, ORIGIN)
            .header(HEADER_AUTHORIZATION, String.format("Bearer %s", gerarToken()));

        return request.get();

    }

    /**
     * Recebe um end point, um body representando a mensagem a ser enviada no corpo da requisição
     * e realiza requisição POST, retornando response do POST realizado
     * @param pathEndPoint
     * @param body
     * @return
     */
    public static Response responsePost(final String pathEndPoint, final Object body){

        RequestSpecification request = given()
            .basePath(pathEndPoint)
            .port(SERVER_PORT)
            .contentType("application/json")
            .header(HEADER_ORIGIN, ORIGIN)
            .header(HEADER_AUTHORIZATION, String.format("Bearer %s", gerarToken()));

        return request
            .body(body)
            .post();

    }

    /**
     * Recebe um end point, um body representando a mensagem a ser enviada no corpo da requisição
     * e realiza requisição PUT, retornando response do PUT realizado
     * @param pathEndPoint
     * @param body
     * @return
     */
    public static Response responsePut(final String pathEndPoint, final Object body){

        RequestSpecification request = given()
            .basePath(pathEndPoint)
            .port(SERVER_PORT)
            .contentType("application/json")
            .header(HEADER_ORIGIN, ORIGIN)
            .header(HEADER_AUTHORIZATION, String.format("Bearer %s", gerarToken()));

        return request
            .body(body)
            .put();

    }

    /**
     * Recebe um end point
     * e realiza requisição DELETE, retornando response do DELETE realizado
     * @param pathEndPoint
     * @return
     */
    public static Response responseDelete(final String pathEndPoint){

        RequestSpecification request = given()
            .basePath(pathEndPoint)
            .port(SERVER_PORT)
            .contentType("application/json")
            .header(HEADER_ORIGIN, ORIGIN)
            .header(HEADER_AUTHORIZATION, String.format("Bearer %s", gerarToken()));

        return request.delete();
            
    }

    private static String gerarToken(){

        String endPoint = String.format(PATH_AUTH_TOKEN, "demo","demo");

        Response response = given()
            .port(SERVER_PORT)
            .header(HEADER_ORIGIN, ORIGIN)
            .get(endPoint);
            
        TokenDTO dto = response
            .then()
            .extract()
            .as(TokenDTO.class);

        return dto.getToken();

    }

}
