package testesAPI

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder;

import org.junit.*
import static groovy.test.GroovyAssert.*
import org.junit.jupiter.api.Test


/**
 * Classe de teste com JUnit criada para garantir o funcionamento da API
 *
 * @author William Malacarne Boff
 * @date 15/04/2020
 */

class testesAutomaticosAPI {
	
	@Test
	//Testa se o comando GET na URL retorna o esperado
	void TesteAPI() {
		
		/* ========== MONTAGEM DO CENÁRIO ========== */
		given: "URL da API"
			def baseURL = new URL('http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador')
		
		/* ========== EXECUÇÃO ========== */
		when: "Estabelece conexão e obtem o GET"
			HttpURLConnection connection = (HttpURLConnection) baseURL.openConnection();
			connection.addRequestProperty("Accept", "application/json")
			connection.with {
				doOutput = true
				requestMethod = 'GET'
			}
			int responseCode = connection.getResponseCode();
			def resposta = connection.getInputStream().text;
			println "GET obtido:" + resposta
			println "código:" + responseCode
			
		/* ========== VERIFICAÇÃO ========== */
		then: 'verifica resultado obtido do GET com o esperado'
			assert resposta == '{"id":1,"meses":["112","124","136","148"],"valor":["2.802","3.174","3.564","3.971"]}'
			
		and: " HTTP Status Code igual a 200 Ok"
			assert responseCode == 200
	}
	
	
}

