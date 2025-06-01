import java.net.MalformedURLException;
import java.util.Random;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Properties;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchPageTest extends BasePageTest {
    private SearchPage searchPage;
    private WebElement resultElement;

    ConfigurationReader reader = new ConfigurationReader();
    Properties config = reader.loadConfig();


    //locators
    String baseUrl = config.getProperty("baseUrl");
    String validEmail = config.getProperty("validEmail");
    String validPassword = config.getProperty("validPassword");

    @BeforeEach
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
        this.driver.get(baseUrl);
        searchPage = new SearchPage(driver);
        searchPage.acceptCookies();
        searchPage.goToLoginPage();
    }

    //Fill simple form and send (Search) - 3 point
    //Form sending with user - 3 point
    //Send a form - 1 point
    @Test
    @Order(1)
    public void testSearch() {
        searchPage.logInToPage(validEmail, validPassword);

        // Fill input (text,radio,check...) - 1 point
        searchPage.searchForm("csont");
        this.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "latok:"));
        resultElement = waitVisibiiltyAndFindElement(By.xpath("//h1[contains(@class,'z-h1')]"));
        Assertions.assertTrue(resultElement.getText().contains("csont"));
    }

    //Fill simple form and send (Search) - 3 point
    //Form sending with user - 3 point
    //Test with random data - 8 point
    //Send a form - 1 point
    @Test
    @Order(2)
    public void testSearchRandomValue() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 10;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        String randomString = sb.toString();
        
	    searchPage.logInToPage(validEmail, validPassword);
        // Fill input (text,radio,check...) - 1 point
	    searchPage.searchForm(randomString);

        this.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), randomString));
        resultElement = waitVisibiiltyAndFindElement(By.xpath("//h1[contains(@class,'z-h1')]"));
        Assertions.assertTrue(resultElement.getText().contains(randomString));
    }
}
