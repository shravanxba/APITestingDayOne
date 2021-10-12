package com.lti.day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GitHubTest {
	@BeforeTest
	public void beforeTest() {
		RestAssured.baseURI = "https://api.github.com/user/repos";
		RestAssured.authentication = RestAssured.oauth2("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");
		
	}
  @Test(enabled = true, description = "Getting all the repositories")
  public void gettingAllRepositories() {
	  given()
	  .when()
	     .get()
	  .then()
	     .log()
	     .body()
	     .statusCode(200);
  }
  
  @Test(enabled = false, description = "Create Repository")
  public void createRepositories() {
	  JSONObject data = new JSONObject();
	  data.put("name", "RestAssuredCreation");
	  data.put("descirption", "Repo created using RestAssured Tool");
	  data.put("homepage", "https://github.com/shravanxba");
	  given()
	     .auth()
	     .oauth2("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")
	     .header("Content-Type", "application/json")
	     .body(data.toJSONString())
	  .when()
	     .post(baseURI)
	  .then()
	     .log()
	     .body()
	     .statusCode(201)
	     .time(Matchers.lessThan(3000L), TimeUnit.MILLISECONDS);
  }
  
}
