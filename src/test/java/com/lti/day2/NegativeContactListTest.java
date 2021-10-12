package com.lti.day2;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class NegativeContactListTest {
  @Test(enabled = true)
  public void recordNotFound() {
	  given()
	  .when()
	     .get("http://3.13.86.142:3000/contacts/5")
	  .then()
	     .log()
	     .body()
	     .statusCode(404);
	  //System.out.println("Record not found");
  }
  
  @Test(enabled = true, description = "Add a contact with missing details")
  public void addContactWithMissingParameter() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Bengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23@gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
    String error =     given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .post("http://3.13.86.142:3000/contacts")
	    .then()
	        .log()
	        .body()
	        .statusCode(400)
	        .extract()
	        .path("err");
    Assert.assertTrue(error.contains("firstName: First Name is required"));
}
  @Test(enabled = true, description = "Add a contact with characters more than the required characters")
  public void addContactWithTooManyCharacters() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "BengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Michael");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23@gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
    String error =     given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .post("http://3.13.86.142:3000/contacts")
	    .then()
	        .log()
	        .body()
	        .statusCode(400)
	        .extract()
	        .path("err");
    Assert.assertTrue(error.contains("is longer than the maximum allowed length"));
}
  @Test(enabled = true, description = "Add a contact with invalid characters")
  public void addContactWithInvalidCharacters() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Bengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Michael21");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23@gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
    String error =     given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .post("http://3.13.86.142:3000/contacts")
	    .then()
	        .log()
	        .body()
	        .statusCode(400)
	        .extract()
	        .path("err");
    Assert.assertTrue(error.contains("firstName: Validator failed for path `firstName` with value `Michael21`"));
}
  @Test(enabled = true, description = "Add a contact with improper format.Ex - email ID with no @")
  public void addContactWithImproperFormat() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Bengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Michael");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
    String error =     given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .post("http://3.13.86.142:3000/contacts")
	    .then()
	        .log()
	        .body()
	        .statusCode(400)
	        .extract()
	        .path("err");
    Assert.assertTrue(error.contains("email: Validator failed for path `email` with value `jumpman23gmail.com`"));
}
}
