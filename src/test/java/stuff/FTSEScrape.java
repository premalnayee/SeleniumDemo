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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FTSEScrape {

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
	
	void extractFromPage() {
		WebElement foo = new WebDriverWait(driver, 5L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer")));
		
		List<WebElement> CompanyNames = driver.findElements(By.xpath("/html/body/main/div/div/div[3]/div[4]/div[1]/div[2]/table/tbody/tr[*]/td[2]"));
		
		List<WebElement> companyPrice = driver.findElements(By.xpath("/html/body/main/div/div/div[3]/div[4]/div[1]/div[2]/table/tbody/tr[*]/td[3]"));
		
		List<WebElement> CompanyDayChangePercentage = driver.findElements(By.xpath("/html/body/main/div/div/div[3]/div[4]/div[1]/div[2]/table/tbody/tr[*]/td[5]"));
				
		for (int i=0; i<3; i++) {
			System.out.println("Company name: " + CompanyNames.get(i).getText() + " company price: " + companyPrice.get(i).getText() + " percentage change: " + CompanyDayChangePercentage.get(i).getText() );
		}
	}

	@Test
	public void test() {
		this.test = report.createTest("testPOM");
		
//		Get risers
		System.out.println("Risers:");
		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100/risers");
		extractFromPage();
		
//		Get fallers
		System.out.println("Fallers:");
		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100/fallers");
		extractFromPage();
		
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
