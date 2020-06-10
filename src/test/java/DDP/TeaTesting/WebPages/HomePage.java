package DDP.TeaTesting.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 15L);
	
	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]/span")
	WebElement greenTea;
	
	@FindBy(xpath="//*[@id=\"wsb-button-00000000-0000-0000-0000-000451959280\"]/span")
	WebElement redTea;
	
	@FindBy(xpath="//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1")
	WebElement menuText;
	
	@FindBy(xpath="//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]")
	WebElement checkoutButton;
	
	
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
	
	public boolean onRightPage() {
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(menuText, "Menu"));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean checkOutReady() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
