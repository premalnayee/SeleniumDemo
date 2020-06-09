package stuff;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MercuryTours {

	WebDriver driver;
	WebElement dressesButton;
	private static ExtentReports report;
	private ExtentTest test;
	
	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-reports/FTSEScrape/extentReport.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}
	
	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void registerUser() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
//		Navigate to website
		driver.get("http://www.newtours.demoaut.com/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/p[1]/img")));
		
//		Click register button and wait for page to load
		WebElement registerButton = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"));
		registerButton.click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[18]/td/input")));
		
//		Fill in form, the list contains the following values: First Name, Last Name, Phone, Email, Address, Address 2, City, State/province, Postal Code, User Name, Password, Confirm password
		String[] formDetails = {"John","conor","4324","john@cyberdynesystem.com","Lands End Way","","Oakham","Penwith","LE15 6US","JohnHatesRobots","password","password"};
		

		List<WebElement> fieldVar = driver.findElements(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[*]/td[2]/input"));
		System.out.println(fieldVar);
		System.out.println(fieldVar.size());
		
		int fieldNum = 0;
		for (WebElement field : fieldVar) {
			field.sendKeys(formDetails[fieldNum]);
			fieldNum++;
		}
		
//		Select the drop down
		Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[12]/td[2]/select")));
		dropdown.selectByVisibleText("UNITED KINGDOM");
		
//		Click the submit button and wait for confirmation
		WebElement submitButton = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[18]/td/input"));
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b")));

//		Check for confirmation message
		WebElement confirmationMessage = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b"));
		assertEquals("Dear John conor,", confirmationMessage.getText());
		
		
//		Navigate to log in page
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).click();
		
		WebElement userNameLogIn = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td[2]/input"));
		userNameLogIn.sendKeys(formDetails[9]);
		WebElement passwordLogIn = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/input"));
		passwordLogIn.sendKeys(formDetails[10]);
		
//		Click Submit button and wait
		WebElement submitLoginButton = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/input"));
		submitLoginButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img")));
		
		WebElement loggedIn = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img"));
		assertEquals("(492, 30)", loggedIn.getSize().toString());
		
//		Fill in flight details
		Select numPassengers = new Select(driver.findElement(By.name("passCount")));
		numPassengers.selectByVisibleText("2");
		
		Select fromPort = new Select(driver.findElement(By.name("fromPort")));
		fromPort.selectByVisibleText("London");
		
		Select toPort = new Select(driver.findElement(By.name("toPort")));
		toPort.selectByVisibleText("Paris");
		
		WebElement servClass = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font/input[2]"));
		servClass.click();
		
		Select airline = new Select(driver.findElement(By.name("airline")));
		airline.selectByVisibleText("Unified Airlines");
	
//		Click continue and wait
		WebElement contineFlight = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input"));
		contineFlight.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img")));
		
//		Click continue on the selected flights
		WebElement contineSelectedFlight = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input"));
		contineSelectedFlight.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img")));
		
//		Fill in flight details
		WebElement passenger1fname = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input"));
		passenger1fname.sendKeys("John");
		
		WebElement passenger1lname = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input"));
		passenger1lname.sendKeys("Connor");
		
		WebElement passenger2fname = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td/table/tbody/tr[2]/td[1]/input"));
		passenger2fname.sendKeys("Robert");
		
		WebElement passenger2lname = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td/table/tbody/tr[2]/td[2]/input"));
		passenger2lname.sendKeys("Paulson");
		
		WebElement creditNum = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td/table/tbody/tr[2]/td[2]/input"));
		creditNum.sendKeys("0546546465465");
		
		// Fill in billing info
		WebElement billAddress1 = driver.findElement(By.name("billAddress1"));
		billAddress1.sendKeys(formDetails[4]);
		
		WebElement billAddress2 = driver.findElement(By.name("billAddress2"));
		billAddress2.sendKeys(formDetails[5]);
		
		WebElement billCity = driver.findElement(By.name("billCity"));
		billCity.clear();
		billCity.sendKeys(formDetails[6]);
		
		WebElement billState = driver.findElement(By.name("billState"));
		billState.clear();
		billState.sendKeys(formDetails[7]);
		
		WebElement billZip = driver.findElement(By.name("billZip"));
		billZip.clear();
		billZip.sendKeys(formDetails[8]);
		
		Select billCountry = new Select(driver.findElement(By.name("billCountry")));
		billCountry.selectByVisibleText("UNITED KINGDOM");
		
		WebElement sameAs = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[15]/td[2]/input"));
		sameAs.click();
		
//		Click secure purchase and wait
		WebElement securePurchase = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[24]/td/input"));
		securePurchase.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")));
		
		assertEquals("Your itinerary has been booked!", driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]"))
				.getText().toString());
	}
	

	@After
	public void tearDown() {
		//driver.quit();
	}
	
	@AfterClass
	public static void tearDownClass() {
		report.flush();
	}

}
