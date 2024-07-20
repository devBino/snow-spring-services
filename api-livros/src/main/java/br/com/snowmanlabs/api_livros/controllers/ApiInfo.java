package br.com.snowmanlabs.api_livros.controllers;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-info")
public class ApiInfo {
    
    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(){
        return ResponseEntity.ok().body(Map.of("resposta","pong..."));
    }

}
