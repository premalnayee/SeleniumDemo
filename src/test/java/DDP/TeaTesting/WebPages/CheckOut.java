package DDP.TeaTesting.WebPages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOut {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 15L);
	
	@FindBy(xpath="//*[@id=\"wsb-element-00000000-0000-0000-0000-000451989411\"]/div")
	WebElement title;
	
	@FindBy(xpath="//*[@id=\"email\"]")
	WebElement email;
	
	@FindBy(xpath="//*[@id=\"address\"]")
	WebElement address;
	
	@FindBy(xpath="//*[@id=\"card_type\"]")
	WebElement card_type;
	
	@FindBy(xpath="//*[@id=\"card_number\"]")
	WebElement card_number;
	
	@FindBy(xpath="//*[@id=\"cardholder_name\"]")
	WebElement cardholder_name;
	
	@FindBy(xpath="//*[@id=\"verification_code\"]")
	WebElement verification_code;
	
	@FindBy(xpath="//*[@id=\"wsb-element-00000000-0000-0000-0000-000452010925\"]/div/div/form/div/button")
	WebElement placeOrderButton;
	
	public boolean areWeThereYet() {
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(title, "Pay with Credit Card or Log In"));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void typeIntoEmail(String text) {
		email.sendKeys(text);
	}
	
	public void typeIntoAddress(String text) {
		address.sendKeys(text);
	}
	
	public void selectCard_type(String text) {
	}
	
	public void typeIntoCard_number(String text) {
		card_number.sendKeys(text);
	}
	
	public void typeIntoCardholder_name(String text) {
		cardholder_name.sendKeys(text);
	}
	
	public void typeIntoVerification_code(String text) {
		verification_code.sendKeys(text);
	}
	
	public void clickPlaceOrder() {
		placeOrderButton.click();
	}
}
