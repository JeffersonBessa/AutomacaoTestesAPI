package br.com.restapi.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostmanAPITests {
	
	private static final String URI = "https://postman-echo.com/get";
	
	@Test
	public void GETRequest() {
		
		given()
			.relaxedHTTPSValidation()
			.param("foo1", "bar1")
			.param("foo2", "bar2")
	   .when()
			.get(URI)
			.then().log().all()
			.statusCode(200) // O status http retornado foi 200
			.contentType(ContentType.JSON) // O response foi retornado no formato JSON
			.body("args.foo1",is("bar1")) // A chave "foo1" possui exatamente o valor "bar1"
			.body("headers.host", is("postman-echo.com")) // A chave "host" possui exatamente o valor "postman-echo.com"
			.body("args.foo1", containsString("bar")); //A chave "foo1" contém o valor "bar"
	}
	
	//@Test
	public void GETResponseBody() {
		
		Response res =
				
			given()
				.relaxedHTTPSValidation()
				.param("foo1", "bar1")
				.param("foo2", "bar2")
			.when()
				.get(URI);
		
		System.out.println(res.body().prettyPrint());	
	}
	
}
