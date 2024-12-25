package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification request;
	public RequestSpecification requestSpecBuilder() throws IOException
	{
		if(request==null) // used if condition to not execute this block again and again once its executed otherwise its will replace the existing test log with current logs 
		{
		//created one file with PrintStream type to logs the log at run time 
		PrintStream logs = new PrintStream(new FileOutputStream("logging.txt"));
		// used the RequestSpecBuilder class to minimize, reuse the code for initial setup by setting the base URI, headers, and query parameters etc 
		request= new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				//used to logging  the logs for request  
				.addFilter(RequestLoggingFilter.logRequestTo(logs))
				//used to logging  the logs for response   
				.addFilter(ResponseLoggingFilter.logResponseTo(logs))
				.setContentType(ContentType.JSON).build();
		 
		return request;
		}
		return request;
		
	}
	
	// Method to read the values from global.properties files 
	public String getGlobalValue(String key) throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\APICucumberFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		String keyValue = prop.getProperty(key);
		return keyValue;
	}
	
	public String getJsonPath(Response response, String Key)
	{
		String resp= response.asString();
		JsonPath js =new JsonPath(resp);
		String result = js.get(Key).toString();
		return result;
		 
	}
	

}
