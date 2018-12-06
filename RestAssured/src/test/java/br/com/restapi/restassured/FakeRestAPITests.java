package br.com.restapi.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class FakeRestAPITests {

	@BeforeClass
	public static void setUp()
	{
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
	}
	
	@Test
	public void getRequest()
	{
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("/posts/1").
		then().log().all()
			.statusCode(200)
			.body("userId", is(1))
			.body("id", is(1))
		    .body("title", containsString("sunt aut facere"))
			.body("body", containsString("quia et suscipit"));
	}
	
	@Test
	public void getRequestWithParameters()
	{
		Response res = 
		
		given()
			.param("userId", "1")
			.param("id", "8")
		.when()
			.get("/posts");
		
		System.out.println(res.body().prettyPrint());
	}
	
	@Test
	public void postRequest()
	{
		given().
			contentType("application/json").
			body("{ \"title\":\"VR Dev Summit\", \"body\":\"blabla\", \"userId\":123 }").
		when()
			.post("posts")
		.then().log().all()
			.statusCode(201)
			.body("title", containsString("VR Dev Summit"))
			.body("body", containsString("blabla"))
			.body("userId", is(123));
	}
	
	@Test
	public void putRequest() {
		
		Map<String,String> param = new HashMap<>();
		param.put("title", "teste put");
		param.put("userId", "50");
		
		given()
			.contentType("application/json")
			.body(param)
		.when()
			.put("/posts/1")
		.then().log().all()
			.statusCode(200);
	}
	
	@Test
    public void deleteRequest() { 
				
		given()
			.param("userId", "1")
			.param("id", "8")
        .when()
        	.delete("/posts/1")
        .then().log().all()
        .statusCode(200);
    }
	
	
	@Test
	public void endpointNotFound()
	{
		given()
			.contentType("application/json").
		when()
			.post("/naoexiste").
		then()
			.assertThat()
				.statusCode(equalTo(404));
	}
	
	
}