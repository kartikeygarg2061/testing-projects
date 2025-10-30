package pet_store;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import read_csv_data.csv_data;

public class get_pet_by_id {
	@Test(dataProvider = "data")
	public void getpet(String id, String name) {
//		int petid = Integer.parseInt(id);
		System.out.println("----------------------Fetching Pet Data Using Id----------------------------");
		Response res = 
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.baseUri("https://petstore.swagger.io/v2")
				.pathParam("id", id)
			.when()
				.get("/pet/{id}")
			.then()
				.statusCode(200)
				.log().body()
			.extract()
				.response();
		int petid = res.jsonPath().getInt("id");
		System.out.println("pet id is : " + petid);
	}
	
	@DataProvider(name = "data")
	public Object[][] petdata() throws IOException, CsvException {
		csv_data data = new csv_data();
		return data.readData();
	}
}
