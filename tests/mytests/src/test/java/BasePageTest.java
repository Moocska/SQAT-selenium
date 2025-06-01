import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final By bodyLocator = By.tagName("body");

    private BasePage basePage;

    protected WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    //WebDriver configuration (modify something in the browser options) - 4 point
    @BeforeEach
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--enable-javascript");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-gpu");
	    options.addArguments("--start-maximized");
	    options.addArguments("--disable-extensions");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void endSession() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
