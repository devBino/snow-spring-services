package br.com.snowmanlabs.api_livros.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para encapsular uma lista de Livros
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Livros")
@JsonRootName("Livros")
public class ListaLivrosDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "livro")
    private List<LivroDTO> livros;

    private Long totalRegistros;
    private int totalPaginas;

}
