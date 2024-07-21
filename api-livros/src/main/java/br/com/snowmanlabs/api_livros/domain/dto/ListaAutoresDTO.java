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
 * DTO para encapsular uma lista de Autores
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Autores")
@JsonRootName("Autores")
public class ListaAutoresDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "autor")
    private List<AutorDTO> autores;

    private Long totalRegistros;
    private int totalPaginas;
    
}
