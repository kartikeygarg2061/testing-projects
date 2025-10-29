package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage {
	
	WebDriver dr;
	
	@FindBy(className = "product_sort_container") WebElement dropdown;
	@FindBy(xpath = "//div[@class = \"inventory_item\"][1]//button") WebElement addProductBtn;
	@FindBy(className = "shopping_cart_link") WebElement cartBtn;
	@FindBy(xpath = "//div[text() = \"Test.allTheThings() T-Shirt (Red)\"]") WebElement productName;
	
	public ProductsPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	public void addProduct() {
		Select s = new Select(dropdown);
		s.selectByVisibleText("Name (Z to A)");
		
		addProductBtn.click();
	}
	
	public String prodName() {
		return productName.getText();
	}
	
	public void moveToCart() {
		cartBtn.click();
	}
}
