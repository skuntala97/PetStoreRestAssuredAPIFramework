package api.endpoints;

import api.paylods.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

public class UserEndPointsProp {
	
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // to load properties file routes.properties
		return routes;
	}
	
	public static Response createUser(User payload) {
		String post_url = getUrl().getString("post_url");
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(payload)
							.when()
								.post(post_url);
		return response;
	}
	
	
	public static Response getUser(String userName) {
		String get_url = getUrl().getString("get_url");
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.get(get_url);
		return response;
	}
	
	public static Response updateUser(User payload ,String userName) {
		String put_url = getUrl().getString("put_url");
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
							.when()
								.put(put_url);
		return response;
	}
	
	public static Response removeUser(String userName) {
		String delete_url = getUrl().getString("delete_url");
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("username", userName)
							.when()
								.delete(delete_url);
		return response;
	}
	

}
