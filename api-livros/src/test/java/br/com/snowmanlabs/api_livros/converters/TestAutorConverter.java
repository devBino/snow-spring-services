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
import br.com.snowmanlabs.api_livros.mock.MockTests;

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

        mockModel = MockTests.getAutorModel();
        modckDTO = MockTests.getAutorDTO();
        
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
