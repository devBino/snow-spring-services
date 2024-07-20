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

import br.com.snowmanlabs.api_livros.domain.service.AuthService;

/**
 * Controller para autenticar usuario e gerar token
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class Auth {
    
    @Autowired
    private AuthService service;

    @GetMapping(
        value = "/token/{user}/{password}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> gerarToken(
        @PathVariable(value = "user") String user,
        @PathVariable(value = "password") String password
    ){
        return service.gerarToken(user, password);
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(){
        return ResponseEntity.ok().body(Map.of("resposta","pong..."));
    }

    @GetMapping(value = "/teste-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authPing(){
        return ResponseEntity.ok().body(Map.of("resposta","token autenticado..."));
    }

}
