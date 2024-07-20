package br.com.snowmanlabs.servico_email.configs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("development")
public class TestConfigs {
    
    @Test
    public void testConfigIsOkParaCI(){
        assertTrue(true);
    }

}

