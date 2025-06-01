import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Properties;

//BasePage object class - 1 point 
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By bodyLocator = By.tagName("body");

    ConfigurationReader reader = new ConfigurationReader();
    Properties config = reader.loadConfig();

    //locators
    int implicitWait = Integer.parseInt(config.getProperty("implicitWait"));


    public BasePage(WebDriver driver) {
        this.driver = driver;
        //Explicit wait - 3 point
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(implicitWait));
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText(){
	    WebElement bodyElement = waitAndReturnElement(bodyLocator);
	    return bodyElement.getText();
   }

   public WebDriver acceptCookies() {
        WebElement acceptCookiesBtn = this.waitAndReturnElement(By.id("onetrust-accept-btn-handler"));
        acceptCookiesBtn.click();

        return this.driver;
    }
}
