package br.com.restapi.restassured;

import br.com.restapi.restassured.config.TestConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import br.com.restapi.restassured.config.Endpoints;

public class FootballTests extends TestConfig {
	
	@Test
	public void getAllCompetitionsOneSeason(){
		
		given()
			.spec(football_requestSpec)
			.queryParam("season", "2016")
		.when()
			.get("/competitions/")
		.then()
			.log().all();
	}
	
	
}
