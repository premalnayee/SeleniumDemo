package stuff;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class thedemosite {
	
	WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() {
		String userName = "cyberdynesy";
		String userPass = "password";
		driver.manage().window().maximize();
		driver.get("http://thedemosite.co.uk/addauser.php");
//		WebElement searchBar = driver.findElement()
		
		// Create user
		WebElement userNameBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		userNameBox.sendKeys(userName);
		
		WebElement userPassBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		userPassBox.sendKeys(userPass);
		
		WebElement saveBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		saveBox.click();
		
		// Login
		WebElement loginButtPage = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
		loginButtPage.click();
		
		WebElement userNameBoxLogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		userNameBoxLogin.sendKeys(userName);
		
		WebElement userPassBoxLogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		userPassBoxLogin.sendKeys(userPass);
		
		WebElement loginButt = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		loginButt.click();
		
		// Test
		WebElement sucess = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("**Successful Login**", sucess.getText());
	}
}
