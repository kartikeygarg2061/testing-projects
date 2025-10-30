package pet_store;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import read_csv_data.csv_data;

public class update_pet_data {
	
	@Test(dataProvider = "data")
	public void update_pet(String id, String name) {
		System.out.println("----------------------Updating Pet Data----------------------------");
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
		pet.put("name", "puppy");
		pet.put("photourls", photourls);
		pet.put("tags", tags);
		pet.put("status", "available");
		
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://petstore.swagger.io/v2/pet")
				.body(pet.toString())
			.when()
				.put()
			.then()	
				.statusCode(200)
				.log().body();
	}
	
	@DataProvider(name = "data")
	public Object[][] petdata() throws IOException, CsvException{
		csv_data data = new csv_data();
		return data.readData();
	}
}
