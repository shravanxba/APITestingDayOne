package com.lti.day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
  @Test(enabled = false,description = "Getting weather information of specific city")
  public void getWeatherDetails() {
	  RestAssured.given()  //Some precondition like authentication
	             .when()   //Performs some steps
	               .get("https://api.openweathermap.org/data/2.5/weather?q=Bengaluru&appid=b45fec8fa77d7585eade562380f835e5")
	             .then()  //Some post condition like verification
	               .log() //Print Data in console
	               .body() //Print the body
	               .statusCode(200);
  }
  
  @Test(enabled = false,description = "Getting weather information of specific city")
  public void getWeatherDetails2() {
	Response res =  RestAssured.given()  //Some precondition like authentication
	             .when()   //Performs some steps
	               .get("https://api.openweathermap.org/data/2.5/weather?q=Bengaluru&appid=b45fec8fa77d7585eade562380f835e5");
	 System.out.println(res.prettyPrint());
	 System.out.println(res.getTime());
	 System.out.println(res.getStatusCode());
	 System.out.println(res.getContentType());
	 Assert.assertEquals(res.getStatusCode(), 200); //checking with the help of TestNG Assertion
  }
  
	  @Test(enabled = false,description = "Getting weather information of specific city")
	  public void getWeatherDetails3() {
		  RestAssured.given()  //Some precondition like authentication
		                .queryParam("q", "Bengaluru")
		                .queryParam("appid", "b45fec8fa77d7585eade562380f835e5")
		             .when()   //Performs some steps
		               .get("https://api.openweathermap.org/data/2.5/weather")
		             .then()  //Some post condition like verification
		               .log() //Print Data in console
		               .body() //Print the body
		               .statusCode(200);
	  }
	  
	  @Test(enabled = true,description = "Getting weather information of specific city")
	  public void getWeatherDetails4() {
		  Map<String, String> param = new HashMap<String, String>();
		  param.put("q", "Bengaluru");
		  param.put("appid", "b45fec8fa77d7585eade562380f835e5");
		  RestAssured.given()  //Some precondition like authentication
		                .queryParams(param)
		             .when()   //Performs some steps
		               .get("https://api.openweathermap.org/data/2.5/weather")
		             .then()  //Some post condition like verification
		               .log() //Print Data in console
		               .body() //Print the body
		               .statusCode(200);
	  }
}
