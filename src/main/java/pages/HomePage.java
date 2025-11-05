package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//select[@data-test='product-sort-container']")
	WebElement sortByDropdown;
	
	@FindBy(xpath = "//div[@data-test='inventory-item-price']")
	List<WebElement> itemPricesOfEachProduct;
	
	
	@FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-onesie\"]")
	WebElement addToCartButtonForOnesie;
	
	
	
	@FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-bike-light\"]")
	WebElement addToCartButtonForBikeLight;
	
	@FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")
	WebElement addToCartButtonForBoltTShirt;
	
	@FindBy(xpath = "//span[@data-test=\"shopping-cart-badge\"]")
	WebElement addedItemsCount;
	
	@FindBy(xpath = "//div[@id=\"shopping_cart_container\"]")
	WebElement cartContainer;
	
	public List<Double> getAllPrices() {
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : itemPricesOfEachProduct) {
            String text = priceElement.getText().replace("$", "").trim();
            double value = Double.parseDouble(text);
            prices.add(value);
        }

        return prices;
    }
	
	public void sortByPrice() {
		
		Select select  = new Select(sortByDropdown);
		select.selectByValue("lohi");
		
		List<Double> prices = getAllPrices();
        System.out.println("Prices: " + prices);
        
        
        double firstPrice = prices.get(0);
        double minPrice = Collections.min(prices);

        System.out.println("First Price: " + firstPrice);
        System.out.println("Lowest Price: " + minPrice);

        Assert.assertEquals(firstPrice, minPrice, 
            "❌ First element is NOT the lowest price!");
        
        System.out.println("✅ Verified: First element has the lowest price.");
		
	}
	
	public void addFirstThreeProductToCart() throws InterruptedException {
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(addToCartButtonForOnesie)).click();
		wait.until(ExpectedConditions.visibilityOf(addToCartButtonForBikeLight)).click();
		wait.until(ExpectedConditions.visibilityOf(addToCartButtonForBoltTShirt)).click(); 
	}

	
	public void countAddedProduct() throws InterruptedException {
		String counts = addedItemsCount.getText();
	    System.out.println("✅ Added items count: " + counts);
		assertEquals(counts, "3");
		Thread.sleep(2000);
	}
	
	public void navigateToCartPage() {
		cartContainer.click();
		Assert.assertEquals(driver.getCurrentUrl().contains("cart"), true, "User is not on cart page");
		System.out.println("✅ User is on Your Cart page");
	}
	
	
}
