package tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import excelfunc.excelFunction;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.common_method;

public class test001 extends common_method {
	
	LoginPage lp;
	ProductsPage pp;
	CheckOutPage cp;
	
	ExtentReports extent;
	ExtentTest test;
	@BeforeClass
	public void launchBrowser() {
		launchbrowser("https://www.saucedemo.com/");
		String reportPath = "D:\\eclipse files\\saucedemo\\target\\reports\\test001-report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeMethod
	public void createTest(Method method) {
		test = extent.createTest(method.getName());
	}
	
	
	@Test(priority = 1)
	public void LoginTest() throws IOException {
		lp = new LoginPage(dr);
		lp.loginCred(excelFunction.exceldata(1, 0), excelFunction.exceldata(1, 1));
		Assert.assertEquals(dr.getTitle(), "Swag Labs");
	}
	
	@Test(priority = 2, dependsOnMethods = "LoginTest")
	public void ProductsTest() {
		pp = new ProductsPage(dr);
		pp.addProduct();
		pp.moveToCart();
	}
	
	@Test(priority = 3, dependsOnMethods = "ProductsTest")
	public void validProductTest() {
		cp = new CheckOutPage(dr);
		Assert.assertEquals(pp.prodName(), cp.cartProd());
	}
	
	@Test(priority = 4, dependsOnMethods = "validProductTest")
	public void CheckOutTest() {
		cp.checkOut("virus", "X", "101010");
		Assert.assertEquals(cp.successMsg(), "Thank you for your order!");
	}
	
	@AfterMethod
	public void testResult(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Pass");
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Fail");
		}
		else {
			test.log(Status.SKIP, "Test Skipped");
		}
	}
	
	@AfterClass
	public void exit() {
		dr.quit();
		extent.flush();
	}
}
