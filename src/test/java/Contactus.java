import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Contactus {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        
        WebDriverManager.chromedriver().setup(); // Setup WebDriver using WebDriverManager
    
        driver = new ChromeDriver();             // Initialize ChromeDriver
        driver.manage().window().maximize();     // Maximize browser window
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Test(priority = 0, description = "Verify Title of Dashboard page")
    public void verifyTitle() {
        driver.get("https://www.sportafi.com/");

        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        String expectedTitle = "Sportafi – Where Fans Fuel Greatness.";
        Assert.assertEquals(actualTitle, expectedTitle, "Title is wrong");
    }

    @Test(priority = 1, description = "Verify Logo on Dashboard page")
    public void verifyLogoDashboardPage() {
        driver.get("https://www.sportafi.com/");

        // Correct XPath for logo element
        WebElement logo = driver.findElement(By.xpath("(//*[@class='logo'])[1]"));

        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed!");
    }

    @Test(priority = 2, description = "Verify Contact Us button on Dashboard page")
    public void verifyContactUsButton() {
        driver.get("https://www.sportafi.com/");
        
        // Correct XPath for Contact Us button
        WebElement contactUsButton = driver.findElement(By.xpath("(//a[@class='btn btn_primary'])[1]"));
        
        // Verify button is displayed
        Assert.assertTrue(contactUsButton.isDisplayed(), "Contact Us button is NOT displayed");
        
        // Verify button text
        String actualText = contactUsButton.getText().trim();
        String expectedText = "Contact Us";
        Assert.assertEquals(actualText, expectedText, "Button text is incorrect");
        
        // Verify button is enabled
        Assert.assertTrue(contactUsButton.isEnabled(), "Contact Us button is NOT enabled");
        
        // Optionally verify the href attribute
        String hrefValue = contactUsButton.getAttribute("href");
        Assert.assertNotNull(hrefValue, "Button DOES NOT have href attribute");
    }

    @Test(priority = 3, dataProvider = "emailData", description = "Verify Contact Us form with multiple email data")
    public void contactusform(String email) {
        // Navigate to the webpage
        driver.get("https://www.sportafi.com/");

        // Wait for and click the Contact Us button
        WebElement contactUsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='btn btn_primary'])[1]")));
        contactUsButton.click();

        // Wait for iframe to load and switch to iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hs-form-iframe-0")));
        driver.switchTo().frame(iframe);

        // Wait for the email input field to be visible, then enter the email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.clear();
        emailField.sendKeys(email);

        // Wait for the submit button and click it
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='hs-button primary large']")));
        submitButton.click();

        // You can add assertions here for success or error messages
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");
    }

    // -------------------------
    // DATA PROVIDER
    // -------------------------
    @DataProvider(name = "emailData")
    public Object[][] getEmailData() {
        return new Object[][] {
            {"validemail@example.com"},   // valid email
             //  {"invalidemail"},             // invalid email, missing @
            //   {"test@.com"},                // invalid
             //   {"another.valid@mail.com"}   
        };
    }

    // @AfterMethod
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();  // Close the browser session properly
    //     }
    // }
}
