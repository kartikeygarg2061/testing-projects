package read_csv_data;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class csv_data {
	
	public Object[][] readData() throws IOException, CsvException{
		CSVReader reader = new CSVReader(new FileReader("D:\\eclipse files\\petstoreapi\\src\\test\\resource\\utilities\\pet data.csv"));
		List<String[]> rows = reader.readAll();
		
		Object data[][] = new Object[rows.size()-1][2];
		for(int i=1; i<rows.size(); i++) {
			data[i-1][0] = rows.get(i)[0];
			data[i-1][1] = rows.get(i)[1];
		}
		reader.close();
		return data;
	}
	
	public Object[] readFirstData() throws IOException, CsvException{
		CSVReader reader = new CSVReader(new FileReader("D:\\eclipse files\\petstoreapi\\src\\test\\resource\\utilities\\pet data.csv"));
		List<String[]> rows = reader.readAll();
		
		Object data[] = new Object[rows.size()-1];
		for(int i=1; i<rows.size(); i++) {
			data[i-1] = rows.get(i)[0];
//			data[i-1][1] = rows.get(i)[1];
		}
		reader.close();
		return data;
	}
}
