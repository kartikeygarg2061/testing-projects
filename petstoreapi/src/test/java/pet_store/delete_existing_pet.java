package pet_store;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import read_csv_data.csv_data;

public class delete_existing_pet {
	
	@Test(dataProvider = "data")
	public void delete_pet(String id, String name) {
		System.out.println("----------------------Deleting Pet Data----------------------------");
		
		Response res =
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://petstore.swagger.io/v2")
				.pathParam("id", id)
			.when()
				.delete("/pet/{id}")
			.then()
				.statusCode(200)
			.extract()
				.response();
		
		int msg = res.jsonPath().getInt("message");
		System.out.println("deleted pet id is : " + msg);
	}
	
	
	@DataProvider(name = "data")
	public Object[][] petdata() throws IOException, CsvException{
		csv_data data = new csv_data();
		return data.readData();
	}
}
