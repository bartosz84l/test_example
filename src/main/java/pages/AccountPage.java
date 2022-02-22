package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AccountPage extends BasePage {

    private WebDriver driver;

    public AccountPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "a.account>span")
    protected WebElement accountName;

    @FindBy (css = "p.info-account")
    protected WebElement accountInformation;

    @FindBy (css = "a.logout")
    protected WebElement logoutButton;


    public String checAccountName(){
        return accountName.getText();
    }

    public Boolean checkIfLogOutButtonIsVisible(){
         return logoutButton.isDisplayed();
    }

    public String checkAccountInformation() {
        return accountInformation.getText();
    }

    public void logoutFromAccount(){
        logoutButton.click();
    }






}
