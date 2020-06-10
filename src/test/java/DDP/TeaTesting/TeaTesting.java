package DDP.TeaTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import DDP.TeaTesting.WebPages.HomePage;

public class TeaTesting {
	
	WebDriver driver;
	WebElement dressesButton;
	private static ExtentReports report;
	private ExtentTest test;
	
	HomePage navToMenu;

//	@BeforeClass
//	@SuppressWarnings("deprecation")
//	public static void setup() {
//		report = new ExtentReports();
//		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-reports/ShoppingWebsite/extentReport.html");
//		htmlReport.config().setAutoCreateRelativePathMedia(true);
//		report.attachReporter(htmlReport);
//	}
	
	
	@Before
	public void init() {;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://www.practiceselenium.com/menu.html");
		
	    navToMenu = new HomePage(driver);
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		navToMenu = new HomePage(driver);
		assertTrue(navToMenu.onRightPage());
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		navToMenu = new HomePage(driver);
		assertTrue(navToMenu.checkOutReady());
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		HomePage checkOutGreenTea = new HomePage(driver);
		checkOutGreenTea.greenTeaClick();
		
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@After
	public void endIt() {
		driver.close();
	}


}
