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

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.service.AutorService;

/**
 * Recebe requisições para manipular registros
 * da entidade Autor
 */
@CrossOrigin
@RestController
@RequestMapping("/autor")
public class AutorController {
    
    @Autowired
    private AutorService service;

    /**
     * Recebe requisição POST para criar novo registro
     * @param body
     * @return
     */
    @PostMapping(
        value = "/criar", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> criar(@RequestBody AutorDTO body){
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
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> atualizar(@RequestBody AutorDTO body){
        return service.atualizar(body);
    }
    
    /**
     * Recebe requisição GET para detalhar o registro
     * @param id
     * @return
     */
    @GetMapping(
        value = "/detalhar/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE
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
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id){
        return service.deletar(id);
    }
    
}
