package br.com.restapi.restassured;
	
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import br.com.restapi.restassured.config.Endpoints;
import br.com.restapi.restassured.config.TestConfig;
	
	public class VideoGameAPITests extends TestConfig {
	
		@Test
		public void GetAllGames(){
			
			given()
				.spec(videoGame_requestSpec)
			.when().get(Endpoints.GET_VIDEOGAMES)
			.then()
				.log().all();
		}
		
		@Test
		public void getGameById(){	
			
			given()
				.spec(videoGame_requestSpec)
				.pathParam("videoGameId", 12)
				.log().ifValidationFails()
		    .when()
				.get(Endpoints.GET_VIDEOGAMES_BY_ID)
			.then()
				.log().all()
					.and()
						.assertThat()
							.body("id", is(12))
							.body("name", containsString("Test"))
							.body("reviewScore", equalTo(50))
							.body("category", equalToIgnoringCase("Driving"))
							.body("rating", is("Mature"));
		}
		
		@Test
		public void createNewGame(){
			
			//1ª Forma 
			String gameBodyJson = "{\n" 
					  + " \"id\": 12,\n" 
					  + " \"name\": \"MyGameTest\",\n"
					  + " \"releaseDate\": \"2018-12-05T01:30:28.475Z\",\n"
					  + " \"reviewScore\": 50,\n"
					  + " \"category\": \"Driving\",\n"
					  + " \"rating\": \"Mature\"\n"
					  + "}";
			
			given()
				.spec(videoGame_requestSpec)
				.body(gameBodyJson)
			.when()
				.post(Endpoints.POST_VIDEOGAMES)
			.then()
				.log().all()
					.and()
						.assertThat().body("status", equalTo("Record Added Successfully"));
		}
		
		@Test
		public void updateGameById(){
			
			//2ª Forma
			Map<String,String> updateParam = new HashMap<>();
			updateParam.put("id", "12");
			updateParam.put("name", "MyUpdatedGameTest");
			
			given()
				.spec(videoGame_requestSpec)
				.pathParam("videoGameId", 12)
				.body(updateParam)
			.when()
				.put(Endpoints.PUT_VIDEOGAMES)
			.then()
				.log().all()
					.and()
						.assertThat().body("name", equalTo("MyUpdatedGameTest"));
			
		}
		
		@Test
		public void deleteGameById(){
						
			given()
				.spec(videoGame_requestSpec)
				.pathParam("videoGameId", 12)
				.log().ifValidationFails()
	        .when()
	        	.delete(Endpoints.DELETE_VIDEOGAMES)
	        .then()
	        	.log().all()
	        		.and()
						.assertThat().body("status", equalTo("Record Deleted Successfully"));
		}
		
}
