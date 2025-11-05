package Tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;


public class EndToEndTestJourney {
	
	public WebDriver driver;
	LoginPage page;
	HomePage homepage;
	YourCartPage cartpage;
	CheckoutPage checkout;
	
	@BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.credentials_enable_service", false);
        
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        System.out.println("=== Starting Browser ===");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
    }

	@Test(priority = 1)
	public void login() throws InterruptedException {
		
		driver.get("https://www.saucedemo.com/v1/");
		
		page = new LoginPage(driver);
		page.enterUsername("standard_user");
		page.enterPassword("secret_sauce");
		page.clickLoginBtn();
	}
	
	@Test(priority = 2)
	public void verifySortedItem() {
		homepage = new HomePage(driver);
		homepage.sortByPrice();
		
	}
	
	@Test(priority = 3)
	public void verifyItemsCountAddedToCart() throws InterruptedException {
		homepage.addFirstThreeProductToCart();
		homepage.countAddedProduct();
		homepage.navigateToCartPage();
	}
	
	@Test(priority = 4)
	public void verifyUserIsOnCartPage() throws InterruptedException {
		cartpage = new YourCartPage(driver);
		cartpage.navigateToCartPage();
	}
	
	@Test(priority = 5)
	public void verifyUserIsOnCheckoutPage() {
		cartpage.clickOnCheckOutButton();
	}
	
	@Test(priority = 6)
	
	public void verifyFinishButton() {
		checkout  = new CheckoutPage(driver);
		checkout.enterUserInformationAndClickContinueButton();
		checkout.clickOnFinishButton();
	}
	
	@AfterClass
    public void teardown() {
        System.out.println("=== Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
	}
	
}
