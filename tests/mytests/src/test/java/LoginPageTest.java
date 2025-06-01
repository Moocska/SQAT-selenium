import java.net.MalformedURLException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Properties;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTest extends BasePageTest {
    private LoginPage loginPage;
    private WebElement resultElement;
    ConfigurationReader reader = new ConfigurationReader();
    Properties config = reader.loadConfig();


    //locators
    String baseUrl = config.getProperty("baseUrl");
    String invalidEmail = config.getProperty("invalidEmail");
    String invalidPassword = config.getProperty("invalidPassword");
    String validEmail = config.getProperty("validEmail");
    String validPassword = config.getProperty("validPassword");

    @BeforeEach
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        this.driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.goToLoginPage();
    }

    // Fill simple form and send (eg. Login) - 3 points
    //Send a form - 1 point
    @Test
    @Order(1)
    public void testBadLogin() {
        // Fill input (text,radio,check...) - 1 point + 1 point
        loginPage.logInToPage(invalidEmail, invalidPassword);

        resultElement = waitVisibiiltyAndFindElement(bodyLocator);
        Assertions.assertTrue(resultElement.getText().contains("nytelen e-mail c"));
    }

    // Fill simple form and send (eg. Login) - 3 points
    //Send a form - 1 point
    @Test
    @Order(2)
    public void testGoodLogin() {
        // Fill input (text,radio,check...) - 1 point + 1 point
        loginPage.logInToPage(validEmail, validPassword);

        this.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Hello Moo"));
        resultElement = waitVisibiiltyAndFindElement(bodyLocator);
        Assertions.assertTrue(resultElement.getText().contains("Hello Moo"));
    }

    //Hover test - 6 point
    @Test
    @Order(3)
    public void testHover() {
        loginPage.logInToPage(validEmail, validPassword);
        loginPage.hoverMenu();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopHeaderAccountFlyout")));
        resultElement = waitVisibiiltyAndFindElement(By.xpath("//div[@id='shopHeaderAccountFlyout']"));
        Assertions.assertTrue(resultElement.getText().contains("Kuponjaim"));
    }

    //Logout - 2 point
    //Send a form - 1 point
    @Test
    @Order(4)
    public void testLogout() {
        loginPage.logInToPage(validEmail, validPassword);
        loginPage.hoverMenu();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopHeaderAccountFlyout")));
        loginPage.logout();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopHeaderAccountLink")));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.zooplus.hu/#feedback_logout", currentUrl);
    }
}
