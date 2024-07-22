package br.com.snowmanlabs.api_livros.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.snowmanlabs.api_livros.domain.dto.TokenDTO;
import br.com.snowmanlabs.api_livros.domain.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para autenticar usuario e gerar token
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Endpoints para Gerenciar Authenticação")
public class Auth {
    
    @Autowired
    private AuthService service;

    @GetMapping(
        value = "/token/{user}/{password}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Autenticação e Geração de Token",
        description = "Autenticação e Geração de Token",
        tags = {"Auth"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TokenDTO.class)
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<?> gerarToken(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){
        return service.gerarToken(user, password);
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Endpoint ping pong",
        description = "Endpoint ping pong",
        tags = {"Auth"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content
            )
        }
    )
    public ResponseEntity<?> ping(){
        return ResponseEntity.ok().body(Map.of("resposta","pong..."));
    }

    @GetMapping(value = "/teste-token", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Endpoint para testar token",
        description = "Endpoint para testar token",
        tags = {"Auth"},
        responses = {
            @ApiResponse(
                description = "Success", responseCode = "200",
                content = @Content
            ),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
        }
    )
    public ResponseEntity<?> authPing(){
        return ResponseEntity.ok().body(Map.of("resposta","token autenticado..."));
    }

}
