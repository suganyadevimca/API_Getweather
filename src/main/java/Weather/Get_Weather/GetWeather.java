package Weather.Get_Weather;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetWeather {
 
	@Test
	public void GettingWeather() throws IOException
	{
		 Properties prop=new Properties();
		 FileInputStream file=new FileInputStream("Properties\\Input.Properties");
		 prop.load(file);
		 RestAssured.baseURI = prop.getProperty("baseURL");;
		    RequestSpecification httpRequest = RestAssured.given();
		    // Set HTTP Headers
		    httpRequest.header("Content-Type", prop.getProperty("Content-type"));
		    httpRequest.header("unitGroup", prop.getProperty("unit"));
		    
		    Response response = httpRequest.get(prop.getProperty("city")+"?key="+prop.getProperty("key"));
		    
		    // Get Response Body 
		    ResponseBody body = response.getBody();
		    
		    // Get Response Body as String 
		    String bodyStringValue = body.asString();
		    
		    System.out.println(bodyStringValue);
		    // Validate if Response Body Contains a specific String
		    Assert.assertTrue(bodyStringValue.contains(prop.getProperty("element")));
		    
		    
		    // Get JSON Representation from Response Body 
		    JsonPath jsonPathEvaluator = response.jsonPath();
		    // Get specific element from JSON document 
		    
		    String address = jsonPathEvaluator.get(prop.getProperty("element"));
		    System.out.println(address);
		    
		    // Validate if the specific JSON element is equal to expected value
		    Assert.assertTrue(address.equalsIgnoreCase(prop.getProperty("city")));
		}
	
	

	
		        
	}
			      
			     
	
	


	
