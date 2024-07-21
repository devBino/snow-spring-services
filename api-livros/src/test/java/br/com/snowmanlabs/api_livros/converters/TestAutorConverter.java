package br.com.snowmanlabs.api_livros.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.snowmanlabs.api_livros.configs.TestConfigs;
import br.com.snowmanlabs.api_livros.domain.converter.AutorConverter;

import br.com.snowmanlabs.api_livros.domain.dto.AutorDTO;
import br.com.snowmanlabs.api_livros.domain.model.MAutor;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class TestAutorConverter {
    
    @Autowired
    private AutorConverter converter;

    private MAutor convertedModel, mockModel;
    private AutorDTO convertedDTO, modckDTO;

    @BeforeEach
    public void preTests(){

        mockModel = new MAutor();

        mockModel.setId(123L);
        mockModel.setAtivo(1);
        mockModel.setNome("Autor Teste 1");
       
        modckDTO = new AutorDTO();

        modckDTO.setId(123L);
        modckDTO.setAtivo(1);
        modckDTO.setNome("Autor Teste 2");
        
        convertedDTO = converter.toDTO(mockModel);
        convertedModel = converter.toModel(convertedDTO);

    }

    @Test
    public void testIsAttrIdConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getId())
            && Objects.nonNull(convertedModel.getId())
        );
    }

    @Test
    public void testIsAttrAtivoConverted(){
        assertTrue(
            convertedDTO.getAtivo() > 0
            && convertedModel.getId() > 0
        );
    }

    @Test
    public void testIsAttrNomeConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getNome())
            && Objects.nonNull(convertedModel.getNome())
        );
    }

}
