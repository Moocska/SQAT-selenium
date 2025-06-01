import java.net.MalformedURLException;
import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Cookie;
import java.util.Properties;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CookiesTest extends BasePageTest {
    private LoginPage loginPage;
    private WebElement resultElement;

    ConfigurationReader reader = new ConfigurationReader();
    Properties config = reader.loadConfig();

    //locators
    String baseUrl = config.getProperty("baseUrl");

    @BeforeEach
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
	    this.driver.get(baseUrl);
    }


    /* Manipulate cookie meaningfully (without ui), e.g. avoid showing up consent popup without clicking onto it - 6 point
     * At first, test if no cookie is set then a pop up about accepting cookies as usual, then in the second test
     * set the cookie that's responsible for signaling that we already accepted cookie usage, refresh the page, and verify that
     * the very same pop up is not present, thus proving successful and meaningful cookie modification */
    @Test
    @Order(1)
    public void cookiesModalVisible() {
        WebElement cookiesModal = waitVisibiiltyAndFindElement(By.id("onetrust-consent-sdk"));
        Assertions.assertTrue(cookiesModal.isDisplayed());
    }

    @Test
    @Order(2)
    public void setCookieModalHidden() {
        Cookie cookiesAccepted = new Cookie("OptanonAlertBoxClosed", "2025-05-31T19:28:36.983Z");
        this.driver.manage().addCookie(cookiesAccepted);
        this.driver.navigate().refresh();

        List<WebElement> cookiesModalRes = this.driver.findElements(By.id("onetrust-consent-sdk"));
        Assertions.assertTrue(cookiesModalRes.isEmpty());
    }

}
