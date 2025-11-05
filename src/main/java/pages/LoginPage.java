package pages;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	
	@FindBy(xpath = "//input[@id='user-name']")
	WebElement usernameTxtField;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordTxtField;
	
	
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginBtn;
	
	
	public void enterUsername(String username) {
		usernameTxtField.sendKeys(username);
	}
	
	public void enterPassword(String pass) {
		passwordTxtField.sendKeys(pass);
	}
	
	public void clickLoginBtn() throws InterruptedException {
		loginBtn.click();
		assertEquals(driver.getTitle(), "Swag Labs");
		Thread.sleep(2000);
	}
	
}
