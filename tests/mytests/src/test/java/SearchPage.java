import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends LoginPage {

    public SearchPage(WebDriver driver){
        super(driver);
    } 

    //Fill input (text,radio,check...) - 1 point
    //Fill simple form and send (Search) - 3 point
    public WebDriver searchForm(String query) {
    	WebElement searchInput = this.waitAndReturnElement(By.xpath("//input[@id='search_query_field_desktop']"));
	    searchInput.sendKeys(query);

	    //complex xpath (eg. //div//a[@href='asd']) - 1 point
	    WebElement searchBtn = this.waitAndReturnElement(By.xpath("//button[@data-zta='search_form_button_desktop' and @type='submit']"));
        searchBtn.click();

	    return this.driver;
    }

}
