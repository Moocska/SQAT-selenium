import java.net.MalformedURLException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Properties;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StaticPagesTest extends BasePageTest {
    private LoginPage loginPage;
    private WebElement resultElement;

    ConfigurationReader reader = new ConfigurationReader();
    Properties config = reader.loadConfig();

    //locators
    String[] staticPages = config.getProperty("staticPages").split(",");
    String[] staticPagesResults = config.getProperty("staticPagesResults").split(",");

    @BeforeEach
    @Override
    public void setup() throws MalformedURLException {
        super.setup();
    }

    /* Static Page test - 2 point
     * Simply loading a static page and verifying the expected page loaded */
    @Test
    @Order(1)
    public void testStatic() {
        this.driver.get("https://www.zooplus.hu/shop/kisallat");
        this.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "llat Shop")); //Kisallat Shop
        //Explicit wait - 3 point
        resultElement = waitVisibiiltyAndFindElement(bodyLocator);
        Assertions.assertTrue(resultElement.getText().contains("llat Shop"));
    }

    // Reading the page title - 1 point
    @Test
    @Order(2)
    public void testTitle() {
        this.driver.get("https://www.zooplus.hu/shop/macska");
        String title = driver.getTitle();
        Assertions.assertTrue(title.contains("Macska shop"));
    }

    /* Multiple page test from array (easily extendable static page tests) - 3 point
     * Having 2 lists, one holding the static pages to load and the other holding the expected values to be found on said
     * static pages. Very easily extendable & usable */
    @Test
    @Order(3)
    public void testStaticList() {
        for (int i = 0; i < staticPages.length; i++) {
            this.driver.get(staticPages[i]);
            this.wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), staticPagesResults[i]));
            resultElement = waitVisibiiltyAndFindElement(bodyLocator);
            Assertions.assertTrue(resultElement.getText().contains(staticPagesResults[i]));
        }
    }

}
