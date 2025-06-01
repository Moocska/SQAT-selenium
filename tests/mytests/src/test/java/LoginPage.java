import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public WebDriver goToLoginPage() {
	WebElement profileBtn = this.waitAndReturnElement(By.xpath("//div[@id='shopHeaderAccountLink']"));
	    profileBtn.click();
        return this.driver;
    }

    // Fill input (text,radio,check...) - 1 point + 1 point
    public WebDriver logInToPage(String userName, String password) {
        WebElement usernameInput = this.waitAndReturnElement(By.xpath("//input[@id='username']"));
        usernameInput.sendKeys(userName);

        WebElement passwordInput = this.waitAndReturnElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys(password);
	
	    //complex xpath (eg. //div//a[@href='asd']) - 1 point
        WebElement loginButton = this.waitAndReturnElement(By.xpath("//button[@name='login' and @type='submit']"));
        loginButton.click();

        return this.driver;
    }

    //Hover test - 6 point
    public WebDriver hoverMenu() {
        WebElement menu = this.waitAndReturnElement(By.xpath("//div[@id='shopHeaderAccountLink']"));
        Actions actions = new Actions(this.driver);
        actions.moveToElement(menu).perform();

        return this.driver;
    }

    //Logout - 2 point
    public WebDriver logout() {
        WebElement logoutBtn = this.waitAndReturnElement(By.xpath("//a[@href='/checkout/logout.htm']"));
        logoutBtn.click();

        return this.driver;
    }

}
