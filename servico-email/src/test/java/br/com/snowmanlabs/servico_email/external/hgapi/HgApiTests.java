package br.com.snowmanlabs.servico_email.external.hgapi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.snowmanlabs.servico_email.domain.dto.DiaDTO;
import br.com.snowmanlabs.servico_email.domain.dto.PrevisaoTempoDTO;
import br.com.snowmanlabs.servico_email.domain.dto.ResultadosDTO;
import br.com.snowmanlabs.servico_email.domain.service.ClimaCidadeService;

/**
 * Testes para consumo da api Hg Brasil
 * para previsão do tempo.
 * 
 * Essa classe de testes valida que os principais campos
 * que serão utilizados estão sendo retornados
 * 
 */
@SpringBootTest
@ActiveProfiles("development")
public class HgApiTests {
    
    @Autowired
    private ClimaCidadeService climaCidadeService;

    PrevisaoTempoDTO previsaoTempoDTO;
    ResultadosDTO resultadosDTO;
    List<DiaDTO> listaDias;

    @BeforeEach
    public void preTestes(){

        previsaoTempoDTO = climaCidadeService
            .recuperarDadosClima("Pontal do Paraná,PR");

        resultadosDTO = previsaoTempoDTO.getResultadosDTO();

        if(Objects.nonNull(resultadosDTO)){
            listaDias = resultadosDTO.getDias();
        }
        
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Pontal do Paraná,PR",
        "Curitiba,PR",
        "Campo Largo,PR",
        "Balsa Nova, PR",
        "Santana de Parnaíba,SP",
        "Rio Branco,AC",
        "Brasilia,DF",
        "Fortaleza,CE",
        "Bombinhas,SC",
        "Arraial do Cabo,RJ",
        "Ubatuba,SP",
        "Matinhos,PR",
        "Pinhais,PR",
        "Santos,SP",
        "Guaratuba,PR",
        "Jundiai,SP",
        "Itapevi,SP",
        "Canoas,RS",
        "Porto Alegre,RS"
    })
    public void testeCidade(String cidade){

        PrevisaoTempoDTO localDTO = climaCidadeService
            .recuperarDadosClima(cidade);

        assertTrue(
            Objects.nonNull(localDTO)
            && Objects.nonNull(localDTO.getResultadosDTO())
            && Objects.nonNull(localDTO.getResultadosDTO().getDias())
            && !localDTO.getResultadosDTO().getDias().isEmpty()
        );

    }

    @Test
    public void testeResultadosRecuperados(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() ) );
    }

    @Test
    public void testeCidadeRecuperada(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO().getCity() ) );
    }

    @Test
    public void testeDataRecuperada(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO().getDate() ) );
    }

    @Test
    public void testeDescricaoTempoRecuperada(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO().getDescription() ) );
    }

    @Test
    public void testeTempoDiaRecuperado(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO().getCurrently() ) );
    }

    @Test
    public void testeProximosDiasRecuperados(){
        assertTrue( Objects.nonNull( previsaoTempoDTO )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO() )
            && Objects.nonNull( previsaoTempoDTO.getResultadosDTO().getDias() )
            && !previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() );
    }

    @Test
    public void testeDataPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getDate();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeSemanaPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getWeekday();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeMaxPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getMax();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeMinPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getMin();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeHumidadePrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getHumidity();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeMilimetrosPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getRain();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeVelocidadeVentoPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getWindSpeedy();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeDescricaoTempoPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getDescription();

        assertTrue( Objects.nonNull( campo ) );

    }

    @Test
    public void testeCondicaoPrimeiroDia(){
        
        if( Objects.isNull(previsaoTempoDTO.getResultadosDTO().getDias())
            || previsaoTempoDTO.getResultadosDTO().getDias().isEmpty() ){

                assertTrue(false);
                return;

        }

        Object campo = previsaoTempoDTO.getResultadosDTO().getDias().get(0).getCondition();

        assertTrue( Objects.nonNull( campo ) );

    }


}
