package stuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelenumTest {

	WebDriver driver;
	
	@Before
	public void init() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() {
		driver.manage().window().maximize();
		driver.get("https://www.duckduckgo.com");
//		WebElement searchBar = driver.findElement()
		System.out.println("hi");
		
	}

}
