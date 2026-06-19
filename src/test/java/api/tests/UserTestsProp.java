package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsProp;
import api.paylods.User;
import io.restassured.response.Response;

public class UserTestsProp {
	
	Faker faker;
	User payload;
	
	@BeforeClass
	public void genratePayloads() {
		faker = new Faker();
		payload = new User();
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void postUserTest() {
		System.out.println("********************Post user test  *************************");
		Response response = UserEndPointsProp.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	
	@Test(priority=2)
	public void getUserTest() {
		System.out.println("********************Get user test  *************************");
		Response response = UserEndPointsProp.getUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	
	@Test(priority=3)
	public void updateUserTest() {
		System.out.println("********************Update user test  *************************");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		Response response = UserEndPointsProp.updateUser(payload, this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		System.out.println("********************Updated Response *************************");
		Response updatedResponse = UserEndPointsProp.getUser(this.payload.getUsername());
		updatedResponse.then().log().all();
		
	}
	
	@Test(priority=4)
	public void deleteUserTest(){
		System.out.println("******************** Delete user test *************************");
		Response response = UserEndPointsProp.getUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	
	
}
