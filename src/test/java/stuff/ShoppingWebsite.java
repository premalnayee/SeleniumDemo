package stuff;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ShoppingWebsite {
	
	WebDriver driver;
	WebElement dressesButton;
	private static ExtentReports report;
	private ExtentTest test;
	
	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-reports/ShoppingWebsite/extentReport.html");
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
	public void test() {
//		FName, LName, day, month, year of birth
		String[] customerDetails = {"John","Gill","15","August","1990"};
//		Address, City, State, postcode, Country, phone, alias
		String[] customerAddress = {"81 Rivington St","London","Alaska","00000","United Kingdom","0485845462196","Batman"};
		String email = "John@gill.com";
		String pass = "Password";
		
		WebDriverWait wait = new WebDriverWait(driver, 15L);
		
		this.test = report.createTest("testPOM");
		
		driver.get("http://automationpractice.com/index.php");
		
		List<WebElement> scrape = driver.findElements(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[*]"));
		for (WebElement item :  scrape) {
			if (item.getText().contains("DRESSES")) {
				dressesButton = item;
			}
		}
		dressesButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header_logo\"]/a/img")));
		
		List<WebElement> dresses = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li[*]/div/div[2]/h5/a"));
		for (WebElement dress : dresses) {
			if (dress.getText().equals("Printed Dress")) {
				dress.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_logo\"]/a/img")));
		
//		Add to cart and wait
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span"))
			.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]")));
		
//		Proceed to checkout
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"))
			.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")));
		
//		Proceed to checkout again
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span"))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/h1")));
	
//		Create an account
		driver.findElement(By.xpath("//*[@id=\"email_create\"]"))
			.sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]"))
			.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"account-creation_form\"]/div[1]")));
		
//		Fill in personal information of form
		driver.findElement(By.xpath("//*[@id=\"id_gender1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys(customerDetails[0]);
		driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys(customerDetails[1]);
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(pass);
		Select dayOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"days\"]")));
		dayOfBirth.selectByValue(customerDetails[2]);
		Select monthOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"months\"]")));
		monthOfBirth.selectByValue("8");
		Select yearOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"years\"]")));
		yearOfBirth.selectByValue(customerDetails[4]);
		
//		Fill in address
//		driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys(customerDetails[0]);
//		driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys(customerDetails[1]);
		driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys(customerAddress[0]);
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(customerAddress[1]);
		driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys(customerAddress[3]);
		driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys(customerAddress[5]);
		driver.findElement(By.xpath("//*[@id=\"alias\"]")).sendKeys(customerAddress[6]);
		Select id_state = new Select(driver.findElement(By.xpath("//*[@id=\"id_state\"]")));
		id_state.selectByVisibleText(customerAddress[2]);
		Select id_country = new Select(driver.findElement(By.xpath("//*[@id=\"id_country\"]")));
		id_country.selectByVisibleText(customerAddress[4]);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")));
		
//		Proceed to checkout
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/p/button/span")));
		
//		Proceed further
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")));
		
//		Pay by bank wire
		driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_navigation\"]/button/span")));
		
//		Confirm order
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
		
		assertEquals("Your order on My Store is complete.", driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).toString() );
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
