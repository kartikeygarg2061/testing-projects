package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class common_method {
	
	protected static WebDriver dr;
	
	public void launchbrowser(String url) {
		dr = new EdgeDriver();
		dr.get(url);
		dr.manage().window().maximize();
	}
}
