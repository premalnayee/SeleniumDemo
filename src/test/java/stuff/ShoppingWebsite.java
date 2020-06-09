package stuff;

import static org.junit.Assert.*;

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
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport.html");
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
		this.test = report.createTest("testPOM");
		
		driver.get("http://automationpractice.com/index.php");
		
		List<WebElement> scrape = driver.findElements(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[*]"));
		for (WebElement item :  scrape) {
			if (item.getText().contains("DRESSES")) {
				dressesButton = item;
			}
		}
		dressesButton.click();
		
		WebElement element = driver.findElement(By.tagName("p"));
		System.out.println(element.getText());
		
		List<WebElement> itema = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li[*]/div/div[2]/h5/a"));
		for (WebElement items : itema) {
			System.out.println(items.getText());
		}
		
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
