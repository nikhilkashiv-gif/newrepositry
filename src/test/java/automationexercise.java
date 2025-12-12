import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class automationexercise {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Setup WebDriver using WebDriverManager
        driver = new ChromeDriver();             // Initialize ChromeDriver
        driver.manage().window().maximize();     // Maximize browser window
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

//    @Test(priority = 0, description = "Verify Title of Dashboard page")
//    public void verifyTitle() {
//        driver.get("https://automationexercise.com/");
//
//        String actualTitle = driver.getTitle();
//        System.out.println("Page title is: " + actualTitle);
//
//        String expectedTitle = "Automation Exercise";
//        Assert.assertEquals(actualTitle, expectedTitle, "Title is wrong");
//    }

    @Test(priority = 0, description = "scroll")
    public void verifyTitle22() {
        driver.get("https://automationexercise.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
    //    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='material-icons card_travel'])[1]")));
        product.click();        
        		 WebElement serachbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='search_product'])[1]")));
        		 serachbox.sendKeys("blue");
        		 WebElement serachicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='submit_search'])[1]")));       		 
        		 serachicon.click();
//        		 WebElement serahicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='submit_search'])[1]")));       		 
//        		 serachicon.click(       		 
        		  WebElement elementToHover = driver.findElement(By.xpath("(//*[@class='productinfo text-center'])[1]"));
        		  js.executeScript("arguments[0].scrollIntoView(true);", elementToHover);
        	        // Create an Actions object to perform hover and click actions
        	        Actions actions = new Actions(driver);
        	        // Hover over the element
        	        actions.moveToElement(elementToHover).perform();
        	        // Wait for the "Add to Cart" button to appear after hover
        	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='btn btn-default add-to-cart'])[1]")));
        	        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton); 
        	        // Click the "Add to Cart" button
        	        addToCartButton.click();       	        
        	        WebElement viewcart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[normalize-space() = 'View Cart'])[1]")));       		 
        	        viewcart.click();
        	        WebElement viewcart1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[normalize-space() = 'Proceed To Checkout'])[4]")));       		 
        	        viewcart1.click();
        	        WebElement viewcart2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[normalize-space() = 'Continue On Cart'])[2]")));       		 
        	        viewcart2.click();     	           	       
    }

     @AfterMethod
     public void tearDown() {
         if (driver != null) {
             driver.quit();  // Close the browser session properly
         }
     }
}
