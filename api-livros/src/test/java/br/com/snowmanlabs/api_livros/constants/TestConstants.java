package br.com.snowmanlabs.api_livros.constants;

/**
 * Define constantes para utilização
 * durante execução dos testes
 */
public class TestConstants {

    /**
     * Constants para configurações das requisições a API
     * durante os testes de integração
     */
    public static final String HEADER_ORIGIN = "Origin";

    public static final String ORIGIN = "http://localhost:8091";

    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final int SERVER_PORT = 8091;

    /**
     * Constants para compor endpoints da API
     * durante os testes
     */
    public static final String PATH_AUTH_TOKEN = "/auth/token/%s/%s";

    public static final String PATH_TESTE_TOKEN = "/auth/teste-token";

    public static final String PATH_IDIOMA = "/idioma/%s";

    public static final String PATH_AUTHOR = "/autor/%s";

    public static final String PATH_LIVRO = "/livro/%s";

}
