package br.com.snowmanlabs.api_livros.converters;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.snowmanlabs.api_livros.configs.TestConfigs;
import br.com.snowmanlabs.api_livros.domain.converter.LivroConverter;
import br.com.snowmanlabs.api_livros.domain.dto.LivroDTO;
import br.com.snowmanlabs.api_livros.domain.model.MLivro;
import br.com.snowmanlabs.api_livros.mock.MockTests;

@SpringBootTest
@ActiveProfiles("development")
@ContextConfiguration(classes = {TestConfigs.class})
public class TestLivroConverter {
    
    @Autowired
    private LivroConverter converter;

    private MLivro convertedModel, mockModel;
    private LivroDTO convertedDTO, mockDTO;

    @BeforeEach
    public void preTests(){

        mockModel = MockTests.getLivroModel();
        mockDTO = MockTests.getLivroDTO();
    
        convertedModel = converter.toModel(mockDTO);
        convertedDTO = converter.toDTO(mockModel);

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
            && convertedModel.getAtivo() > 0
        );
    }

    @Test
    public void testIsAttrTituloConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getTitulo())
            && Objects.nonNull(convertedModel.getTitulo())
        );
    }

    @Test
    public void testIsAttrAutorConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getAutorId())
            && Objects.nonNull(convertedModel.getAutor())
            && Objects.nonNull(convertedModel.getAutor().getId())
        );
    }

    @Test
    public void testIsAttrIdiomaConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getIdiomaId())
            && Objects.nonNull(convertedModel.getIdioma())
            && Objects.nonNull(convertedModel.getIdioma().getId())
        );
    }

    @Test
    public void testIsAttrUsuarioConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getUsuarioId())
            && Objects.nonNull(convertedModel.getUsuario())
            && Objects.nonNull(convertedModel.getUsuario().getId())
        );
    }

    @Test
    public void testIsAttrDataPublicacaoConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getDataPublicacao())
            && Objects.nonNull(convertedModel.getDataPublicacao())
        );
    }

    @Test
    public void testIsAttrEditoraConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getEditora())
            && Objects.nonNull(convertedModel.getEditora())
        );
    }

    @Test
    public void testIsAttrNumeroPaginasConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getNumeroPaginas() > 0)
            && Objects.nonNull(convertedModel.getNumeroPaginas() > 0)
        );
    }

    @Test
    public void testIsAttrGeneroConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getGenero())
            && Objects.nonNull(convertedModel.getGenero())
        );
    }

    @Test
    public void testIsAttrSinopseConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getSinopse())
            && Objects.nonNull(convertedModel.getSinopse())
        );
    }

    @Test
    public void testIsAttrValorConverted(){
        assertTrue(
            Objects.nonNull(convertedDTO.getValor())
            && Objects.nonNull(convertedModel.getValor())
        );
    }

}
