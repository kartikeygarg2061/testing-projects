package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	
	WebDriver dr;
	
	@FindBy(xpath = "//div[text() = \"Test.allTheThings() T-Shirt (Red)\"]") WebElement cartProduct;
	@FindBy(id = "checkout") WebElement checkoutBtn;
	@FindBy(id = "first-name") WebElement firstname;
	@FindBy(id = "last-name") WebElement lastname;
	@FindBy(id = "postal-code") WebElement postalCode;
	@FindBy(id = "continue") WebElement continueBtn;
	@FindBy(id = "finish") WebElement finishBtn;
	@FindBy(className = "complete-header") WebElement orderConfirmMsg;
	
	public CheckOutPage(WebDriver dr) {
		this.dr = dr;
		PageFactory.initElements(dr, this);
	}
	
	public void checkOut(String fname, String lname, String pCode) {
		checkoutBtn.click();
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		postalCode.sendKeys(pCode);
		continueBtn.click();
		finishBtn.click();
	}
	
	public String cartProd() {
		return cartProduct.getText();
	}
	
	public String successMsg() {
		return orderConfirmMsg.getText();
	}
}
