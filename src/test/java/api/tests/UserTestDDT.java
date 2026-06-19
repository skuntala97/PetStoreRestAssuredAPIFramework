package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.paylods.User;
import api.utilies.DataProviders;
import io.restassured.response.Response;

public class UserTestDDT {
	
	User payload;
	
	@Test(priority=1,dataProvider="GetAllUsersData",dataProviderClass=DataProviders.class)
	public void postUserTest(String userId, String userName,String fname,String lname, String email, String pass, String phone) {
		System.out.println("********************Post user test  *************************");
		payload = new User();
		payload.setId(Integer.parseInt(userId));
		payload.setUsername(userName);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPassword(pass);
		payload.setPhone(phone);
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	
	@Test(priority=2,dataProvider="GetUserName",dataProviderClass=DataProviders.class) 
	public void deleteUserTest(String userName) {
		Response response = UserEndPoints.removeUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
