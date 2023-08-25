package br.com.coruja.application.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.application.model.Aluno;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoControllerTest {
	
	@Autowired
	protected WebTestClient webClient;
	
	@BeforeEach
    public void setUp() {
		webClient = webClient.mutate().responseTimeout(Duration.ofMillis(10000)).build();
    }
	
	@Test
	public void deve_inserir_aluno_quando_passar_o_id() throws Exception {

		Aluno aluno = new Aluno(null, "Fabio Rabin", "fabio@gmail.com");
				
		webClient.post().uri("/alunos").accept(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromValue(aluno))
		.exchange().expectStatus().isCreated()
		.expectBody(Aluno.class)
		.value(objAluno -> assertTrue(objAluno.getId() == 9));		
	}

}
