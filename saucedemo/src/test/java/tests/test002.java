package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import pages.LoginPage;
import utilities.common_method;

public class test002 extends common_method {
	
	LoginPage lp;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void launchBrowser() {
		launchbrowser("https://saucedemo.com/");
		String reportpath = "D:\\\\eclipse files\\\\saucedemo\\\\target\\\\reports\\\\test002-report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeMethod
	public void createTest(Method method) {
		test = extent.createTest(method.getName());
	}
	
	@Test
	public void Login() throws IOException {
		lp = new LoginPage(dr);
		lp.loginCred(excelFunction.exceldata(1, 0), excelFunction.exceldata(1, 1));
		Assert.assertEquals(dr.getTitle(), "Swag Labs");
	}
	
	@Test
	public void allUrls() {
		List<WebElement> list = dr.findElements(By.tagName("a"));
		for(WebElement url : list) {
			System.out.println("link is : " + url.getAttribute("href"));
		}
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
