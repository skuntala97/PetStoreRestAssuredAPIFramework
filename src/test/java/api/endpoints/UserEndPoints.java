package api.endpoints;

import api.paylods.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(payload)
							.when()
								.post(Routes.post_url);
		return response;
	}
	
	
	public static Response getUser(String userName) {
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(User payload ,String userName) {
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
							.when()
								.put(Routes.put_url);
		return response;
	}
	
	public static Response removeUser(String userName) {
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.delete(Routes.delete_url);
		return response;
	}
	

}
