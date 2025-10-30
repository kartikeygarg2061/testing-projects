package pet_store;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import read_csv_data.csv_data;

public class add_a_new_pet {
	
	@Test(dataProvider = "data")
	public void addNewPet(String id, String name	) {
		System.out.println("----------------------Adding a New Pet----------------------------");
		JSONObject category = new JSONObject();
		category.put("id", 101);
		category.put("name", "blackie");
//		------------------------------------------------------
		JSONArray photourls = new JSONArray();
		photourls.add("https://example.com/photos/buddy.jpg");
//		------------------------------------------------------
		JSONObject tag = new JSONObject();
		tag.put("id", 20);
		tag.put("name", "demo");
//		-------------------------------------------------------
		JSONArray tags = new JSONArray();
		tags.add(tag);
//		------------------------------------------------------
		JSONObject pet = new JSONObject();
		pet.put("id", id);
		pet.put("category", category);
		pet.put("name", name);
		pet.put("photourls", photourls);
		pet.put("tags", tags);
		pet.put("status", "available");
		
		Response res = 
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://petstore.swagger.io/v2/pet")
				.body(pet.toString())
			.when()
				.post()
			.then()
				.statusCode(200)
			.extract()
				.response();
		
		String response = res.jsonPath().getString("status");
		System.out.println("status is : " + response);
		Assert.assertEquals(response, "available");
	}
	
	@DataProvider(name = "data")
	public Object[][] petdata() throws IOException, CsvException {
		csv_data cd = new csv_data();
		return cd.readData();
	}
}
