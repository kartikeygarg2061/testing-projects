package excelfunc;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelFunction {
	
	public static String exceldata(int row, int col) throws IOException {
		
		String filepath = "D:\\eclipse files\\saucedemo\\src\\test\\resources\\excelfile\\testdata.xlsx";
		String sheetname = "Sheet1";
		
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(sheetname);
		
		String value = sh.getRow(row).getCell(col).getStringCellValue();
		return value;
		
	}
}
