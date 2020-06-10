package ShoppingSite;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelFile {
	
	WebDriver driver;
	WebElement dressesButton;
	private static ExtentReports report;
	private ExtentTest test;
	
	ArrayList<String> firstNames = new ArrayList<String>();
	ArrayList<String> lastNames = new ArrayList<String>();
	ArrayList<String> emails = new ArrayList<String>();
	ArrayList<String> passwords = new ArrayList<String>();
	
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
	
	public void registerUser(String email, String pass, ArrayList<String> customerDetails, String[] customerAddress) {
//		FName, LName, day, month, year of birth
//		String[] customerDetails = {"John","Gill","15","August","1990"};
//		Address, City, State, postcode, Country, phone, alias
//		String[] customerAddress = {"81 Rivington St","London","Alaska","00000","United Kingdom","04858454621","Batman"};
//		String email = "John@gill.com";
//		String pass = "Password";

		String[] customerDetailsArray = customerDetails.toArray(new String[customerDetails.size()]);
		
		WebDriverWait wait = new WebDriverWait(driver, 15L);
		
		this.test = report.createTest("testPOM");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
	
//		Create an account
		driver.findElement(By.xpath("//*[@id=\"email_create\"]"))
			.sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]"))
			.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"account-creation_form\"]/div[1]")));
		
//		Fill in personal information of form
		driver.findElement(By.xpath("//*[@id=\"id_gender1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys(customerDetailsArray[0]);
		driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys(customerDetailsArray[1]);
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(pass);
		Select dayOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"days\"]")));
		dayOfBirth.selectByValue(customerDetailsArray[2]);
		Select monthOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"months\"]")));
		monthOfBirth.selectByValue("8");
		Select yearOfBirth = new Select(driver.findElement(By.xpath("//*[@id=\"years\"]")));
		yearOfBirth.selectByValue(customerDetailsArray[4]);
		
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
//		Select id_country = new Select(driver.findElement(By.xpath("//*[@id=\"id_country\"]")));
//		id_country.selectByVisibleText(customerAddress[4]);
		
//		Mash the register button
		driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"center_column\"]/p"), 
				"Welcome to your account. Here you can manage all of your personal information and orders."));
		
	}
	
	public void readExcel(String fileName, String filePath, String sheetName) throws IOException {

		//		Create object of file

		File file = new File(filePath +"\\" + fileName);

		//		Create fileinputstream
		FileInputStream inputStream;

		inputStream = new FileInputStream(file);

		Workbook workbook;

		workbook = new HSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		for (int i = 1; i < rowCount+1; i++) {

			Row row = sheet.getRow(i);

			// Assign values to array
			emails.add(row.getCell(0).getStringCellValue());
			passwords.add(row.getCell(1).getStringCellValue());
			firstNames.add(row.getCell(2).getStringCellValue());
			lastNames.add(row.getCell(3).getStringCellValue());

			System.out.println(emails.get(i-1).toString());
		}
	}

	@Test
	public void test() throws IOException {
//		String[] customerDetails = {"John","Gill","15","August","1990"};
		String[] customerAddress = {"81 Rivington St","London","Alaska","00000","United Kingdom","04858454621","Batman"};
		
		ArrayList<String> customerDetails = new ArrayList<String>();
		
		readExcel("Book1.xls", "C:\\Users\\premal\\Google Drive\\QAC\\Melium\\SeleniumDemo", "Sheet1");
		
		for (int i=0; i<emails.size(); i++) {
			customerDetails.add(firstNames.get(i));
			customerDetails.add(lastNames.get(i));
			customerDetails.add("15");
			customerDetails.add("August");
			customerDetails.add("1990");
			
			registerUser(emails.get(i).toString(), passwords.get(i).toString(), customerDetails, customerAddress);
			
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
			customerDetails.clear();
		}
	}

}
