package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    protected WebElement signInButton;

    String url;
    private WebDriver driver;

    public MainPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.url ="http://automationpractice.com/";
    }

    public void goToPage(){
        navigate(this.url);
    }

    public void goToSignInPage(){
        WebDriverWait wait = new WebDriverWait(driver, 20 );
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }
}
