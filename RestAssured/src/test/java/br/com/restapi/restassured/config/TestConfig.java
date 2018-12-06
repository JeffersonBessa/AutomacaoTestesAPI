package br.com.restapi.restassured.config;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {

	public static RequestSpecification videoGame_requestSpec;
	public static ResponseSpecification videoGame_responseSpec;
	public static RequestSpecification football_requestSpec;
	public static ResponseSpecification football_responseSpec;

	@BeforeClass
	public static void setup() {
		
		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setPort(8080)
				.setBasePath("/app")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();
		
		RestAssured.requestSpecification = videoGame_requestSpec;
		
		
		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		RestAssured.responseSpecification = videoGame_responseSpec;
		
		
		football_requestSpec = new RequestSpecBuilder()
			.setBaseUri("http://api.football-data.org")
			.setBasePath("/v1")
			.addParam("X-Auth-Token", "9aa3ccfb5f6d469eaaa68a36ceb45762")
			.addParam("X-Response-Control", "minified")
			.build();
		
		RestAssured.requestSpecification = football_requestSpec;
		
		
		football_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
		RestAssured.responseSpecification = football_responseSpec;
		
	}
	
}
