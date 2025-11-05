package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {
public WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//input[@id='first-name']")
	WebElement userFirstName;

	@FindBy(xpath = "//input[@id='last-name']")
	WebElement userLastName;
	
	@FindBy(xpath = "//input[@id='postal-code']")
	WebElement userZIPCode;
	
	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueButton;
	
	@FindBy(xpath = "//button[@id='finish']")
	WebElement finishButton;
	
	public void enterUserInformationAndClickContinueButton() {
		userFirstName.sendKeys("Test");
		userLastName.sendKeys("Ali");
		userZIPCode.sendKeys("700012");
		continueButton.click();
		Assert.assertEquals(finishButton.isDisplayed(), true, "❌ Finish Button is not on displayed");
		System.out.println("✅ Finish Button is displayed");
	}
	
	public void clickOnFinishButton() {
		if(finishButton.isDisplayed()) {
			finishButton.click();
		}
		Assert.assertEquals(driver.getCurrentUrl().contains("checkout-complete"), true, "❌ Checkout not completed");
		System.out.println("✅ Checkout is completed : Thank you for your order");
	}
	
}
