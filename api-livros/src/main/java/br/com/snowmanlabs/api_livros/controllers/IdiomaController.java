package br.com.snowmanlabs.api_livros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.domain.dto.ListaIdiomasDTO;
import br.com.snowmanlabs.api_livros.domain.service.IdiomaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Recebe requisições para manipular registros
 * da entidade Idioma
 */
@CrossOrigin
@RestController
@RequestMapping("/api/idioma")
@Tag(name = "Idioma", description = "Endpoints para Gerenciar Idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaService service;

    /**
     * Recebe requisição POST para criar novo registro
     * @param body
     * @return
     */
    @PostMapping(
        value = "/criar", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Cria um Novo Idioma",
        description = "Cria um Novo Idioma",
        tags = {"Idioma"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "201",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> criar(@RequestBody IdiomaDTO body){
        return service.criar(body);
    }

    /**
     * Recebe requisição PUT para atualizar o Registro
     * @param body
     * @return
     */
    @PutMapping(
        value = "/atualizar", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Atualiza um Idioma Existente",
        description = "Atualiza um Idioma Existente",
        tags = {"Idioma"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> atualizar(@RequestBody IdiomaDTO body){
        return service.atualizar(body);
    }
    
    /**
     * Recebe requisição GET para detalhar o registro
     * @param id
     * @return
     */
    @GetMapping(
        value = "/detalhar/{id}", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Detalhar Idioma pelo ID",
        description = "Detalhar Idioma pelo ID",
        tags = {"Idioma"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = IdiomaDTO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> detalhar(@PathVariable(value = "id") String id){
        return service.detalhar(id);
    }
    
    /**
     * Recebe requisição GET para para listagem paginada
     * dos registros
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(
        value = "/listar", 
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Operation(
        summary = "Listagem paginada dos Idiomas",
        description = "Listagem paginada dos Idiomas",
        tags = {"Idioma"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ListaIdiomasDTO.class)
                    ),
                    @Content(
                        mediaType = MediaType.APPLICATION_XML_VALUE,
                        schema = @Schema(implementation = ListaIdiomasDTO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> listar(
        @RequestParam(value = "page", defaultValue = "1") String page,
        @RequestParam(value = "limit", defaultValue = "10") String limit
    ){
        return service.listarRegistrosPaginacao(page, limit);
    }

    /**
     * Recebe requisição DELETE para remover o registro
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deletar/{id}")
    @Operation(
        summary = "Deleta um Idioma pelo seu ID",
        description = "Deleta um Idioma pelo seu ID",
        tags = {"Idioma"},
        responses = {
            @ApiResponse(
                description = "No Content", responseCode = "204",
                content = @Content(
                    mediaType = "application/json"
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id){
        return service.deletar(id);
    }

}
