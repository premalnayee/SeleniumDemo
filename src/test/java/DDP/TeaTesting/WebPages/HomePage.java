package DDP.TeaTesting.WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]/span")
	WebElement greenTea;
	
	@FindBy(xpath="//*[@id=\"wsb-button-00000000-0000-0000-0000-000451959280\"]/span")
	WebElement redTea;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	
	public void greenTeaClick() {
		greenTea.click();
	}
	
	public void redTeaClick() {
		redTea.click();
	}

}
