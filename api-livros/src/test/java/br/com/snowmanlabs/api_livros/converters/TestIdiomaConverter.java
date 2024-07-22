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
import br.com.snowmanlabs.api_livros.domain.converter.IdiomaConverter;
import br.com.snowmanlabs.api_livros.domain.dto.IdiomaDTO;
import br.com.snowmanlabs.api_livros.domain.model.MIdioma;
import br.com.snowmanlabs.api_livros.mock.MockTests;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class TestIdiomaConverter {
    
    @Autowired
    private IdiomaConverter converter;

    private MIdioma convertedModel, mockModel;
    private IdiomaDTO convertedDTO, modckDTO;

    @BeforeEach
    public void preTests(){

        mockModel = MockTests.getIdiomaModel();
        modckDTO = MockTests.getIdiomaDTO();

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

    @Test
    public void testIsAttrCodIdiomaConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getCodIdioma())
            && Objects.nonNull(convertedModel.getCodIdioma())
        );
    }

    @Test
    public void testIsAttrCodRegiaoConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getCodRegiao())
            && Objects.nonNull(convertedModel.getCodRegiao())
        );
    }

}
