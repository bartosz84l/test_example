package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignInPage extends BasePage {

    private WebDriver driver;

    public SignInPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//button[@id='SubmitCreate']")
    protected WebElement registerButton;

    @FindBy (xpath = "//input[@id='email_create']")
    protected WebElement emailField;

    @FindBy (xpath ="//input[@id='email']")
    protected  WebElement emailLoginPageInput;

    @FindBy (xpath ="//input[@id='passwd']")
    protected  WebElement passwordLoginPageInput;

    @FindBy (xpath ="//button[@id='SubmitLogin']")
    protected  WebElement submitLoginButton;


    public void goToAccountFormPage (String email) {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (emailField.isEnabled()) {
            emailField.sendKeys(email);
        }
        try {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(registerButton));
            registerButton.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillLoginData(String email, String password){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailLoginPageInput));
        emailLoginPageInput.sendKeys(email);
        passwordLoginPageInput.sendKeys(password);
    }

    public void loginToThePage(){
        submitLoginButton.click();
    }

}
