package stepdefination;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Resources.APIResources;
import Resources.TestData;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefination extends Utils {
	RequestSpecification req;
	//ResponseSpecification response;
	Response resp;
	TestData data=new TestData();
    static String ResponsePlaceId;
	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String string1, String string2, String string3) throws IOException {
			 
		
				
			req=given().spec(RequestSpecification())
				.body(data.AddPlace(string1, string2, string3));
		
			}
	@When("User calls {string} API with {string} http request")
	public void user_calls_api_with_post_http_request(String resource,String HttpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(HttpMethod.equalsIgnoreCase("POST"))
		{
		
		resp=req.when().post(resourceAPI.getResource());
		}
		else if(HttpMethod.equalsIgnoreCase("GET"))
				{
			resp=req.when().get(resourceAPI.getResource());
				}
				
	}
	@Then("The API call got success with status code is {int}")
	public void the_api_call_got_success_with_status_code_is(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		resp.then().spec(responseSpecification()).extract().response();
	Assert.assertEquals(resp.getStatusCode(),200);
	}

   // @And("^  The \"([^\"]*)\" field in the response body is \"([^\"]*)\"$")
	@And("The {string} field in the response body is {string}") 
	public void the_field_in_the_response_body_is(String keyvalue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		
		Assert.assertEquals(getJsonPath(resp,keyvalue), Expectedvalue);
		
		}
	
	@Then("verify place_id created in the maps to {string} using {string}")
	public void verify_place_id_created_in_the_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		ResponsePlaceId=getJsonPath(resp,"place_id");
		req =given().spec(RequestSpecification().queryParam("place_id", ResponsePlaceId));
		user_calls_api_with_post_http_request(resource,"GET");
		String ResponseName=getJsonPath(resp,"name");
		System.out.println(ResponseName);
		Assert.assertEquals(ResponseName, expectedName);
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req =given().spec(RequestSpecification().body(data.deletePlacePayload(ResponsePlaceId)));
		
	}

	
	

}
