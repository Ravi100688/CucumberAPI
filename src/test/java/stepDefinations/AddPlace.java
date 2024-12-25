package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.assertEquals;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlace extends Utils {
	//RequestSpecification request;
	ResponseSpecification res1;
	RequestSpecification req;
	Response actRes; 
	JsonPath js;
	TestDataBuild data = new TestDataBuild();
	APIResources resource;
	static String placeId;
	@Given("Add place playload")
	public void add_place_playload() throws IOException {
		
	    
		
		// used the ResponseSpecBuilder class to minimize, reuse the code for common validation line status code code , content type etc . 
	   
		req =given().spec(requestSpecBuilder()).body(data.addPlaceData());
		
	}
	
	@Given("Add place playload with {string} {string} {string}")
	public void add_place_playload_with(String name, String language, String address) throws IOException {
		req =given().spec(requestSpecBuilder()).body(data.addPlaceDataWithParameter(name, language, address));
	}
	@When("User call {string} with Post http request")
	public void user_call_with_post_http_request(String string) {
		 res1 =new ResponseSpecBuilder().expectStatusCode(200).build();
		 //Used enum class (APIResources) to fetch the post values resources-- constructor will be called with values of resources which you passed 
		resource = APIResources.valueOf(string);
		actRes =req.when().post(resource.getResource())
				.then().spec(res1).extract().response();
	}
	
	@When("User call {string} with {string} http request")
	public void user_call_with_http_request(String resources, String httpMethod) {
		res1 =new ResponseSpecBuilder().expectStatusCode(200).build();
	    // Write code here that turns the phrase above into concrete actions
		 resource = APIResources.valueOf(resources);
		if(httpMethod.equalsIgnoreCase("POST")) 
		{
		actRes =req.when().post(resource.getResource()).then().spec(res1).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("GET")) 
		{
			actRes =req.when().get(resource.getResource());
		}
		
		else if(httpMethod.equalsIgnoreCase("DELETE")) 
			actRes =req.when().delete(resource.getResource());
	}
	
	@Then("The API call get success With status code {int}")
	public void the_api_call_get_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		  assertEquals(actRes.getStatusCode(), 200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
//		String resp = actRes.asString();
//		JsonPath js = new JsonPath(resp);
		String Keydetails =getJsonPath(actRes, KeyValue);
	    assertEquals(Keydetails, expectedValue);
	}
	
	@Then("Verify place_Id created map to {string} using {string}")
	public void verify_place_id_created_map_to_using(String name, String httpMethos) throws IOException {
	  
		//get API call 
		placeId= getJsonPath(actRes, "place_id");
		req=given().spec(requestSpecBuilder()).queryParam("place_id", placeId);
		user_call_with_http_request(httpMethos,"GET");
		String actualName =getJsonPath(actRes, "name");
		assertEquals(actualName, name);
	
	}
	
	@Given("DeletePlace paylod")
	public void delete_place_paylod() throws IOException {
		req =given().spec(requestSpecBuilder()).body(data.deletePaylod(placeId));
	}

}


















