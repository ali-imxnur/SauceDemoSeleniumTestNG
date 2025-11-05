package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class YourCartPage {
public WebDriver driver;
	
	public YourCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//span[text()='Your Cart']")
	WebElement yourCartHeader;
	
	@FindBy(xpath = "//button[@data-test=\"checkout\"]")
	WebElement checkOutButton;
	
	public void navigateToCartPage() {
		boolean yourCartDisplayed = yourCartHeader.isDisplayed();
		Assert.assertEquals(yourCartDisplayed, true, "❌ User is not on Your Cart Page");
		
		System.out.println("✅ User is on Your Cart Page");
	}
	
	public void clickOnCheckOutButton() {
		checkOutButton.click();
		Assert.assertEquals(driver.getCurrentUrl().contains("checkout"), true, "❌ User is not on checkout page");
		System.out.println("✅ User is on Checkout: Your Information page");
	}
	
	
	
}
