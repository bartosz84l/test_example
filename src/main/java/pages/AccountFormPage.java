package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AccountFormPage extends BasePage {

    private WebDriver driver;

    public AccountFormPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath ="//form[@id='account-creation_form']")
    protected WebElement accountForm;

    @FindBy (xpath ="//input[@id='customer_firstname']")
    protected WebElement firstNamePersonalData;

    @FindBy (xpath ="//input[@id='customer_lastname']")
    protected WebElement lastNamePersonalData;

    @FindBy (xpath ="//input[@id='email']")
    protected WebElement emailPersonalData;

    @FindBy (xpath ="//input[@id='passwd']")
    protected WebElement passwordPersonalData;

    @FindBy (xpath ="//input[@id='id_gender1']")
    protected WebElement maleGenderCheckbox;

    @FindBy (xpath ="//select[@id='days']")
    protected WebElement daysDropown;

    @FindBy (xpath ="//select[@id='months']")
    protected WebElement monthsDropdown;

    @FindBy (xpath ="//select[@id='years']")
    protected WebElement yearsDropdown;

    @FindBy (xpath ="//input[@id='firstname']")
    protected WebElement firstNameAddressInput;

    @FindBy (xpath ="//input[@id='lastname']")
    protected WebElement lastNameAddresssInput;

    @FindBy (xpath ="//input[@id='address1']")
    protected WebElement addressInput;

    @FindBy (xpath ="//input[@id='city']")
    protected WebElement cityInput;

    @FindBy (xpath ="//select[@id='id_state']")
    protected WebElement stateDropdown;

    @FindBy (xpath ="//div[@id='uniform-id_state']/span")
    protected WebElement stateField;

    @FindBy (xpath ="//input[@id='postcode']")
    protected WebElement postalCodeInput;

    @FindBy (xpath ="//select[@id='id_country']")
    protected WebElement countryDropdown;

    @FindBy (xpath ="//div[@id='uniform-id_country']/span")
    protected WebElement countryField;

    @FindBy (xpath ="//input[@id='phone_mobile']")
    protected WebElement mobilePhoneNumberInput;

    @FindBy (xpath ="//input[@id='alias']")
    protected WebElement aliasAddressInput;

    @FindBy (xpath ="//button[@id='submitAccount']")
    protected WebElement registerAccountButton;

    public Boolean checkIfFormIsDisplayed (){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(accountForm));
        return accountForm.isDisplayed();
    }

    public void chooseMaleGender(){
        maleGenderCheckbox.click();
    }

    public Boolean checkIfMaleMaleGenderSelected(){

        return maleGenderCheckbox.isSelected();
    }

    public void fillPersonalData(String firstName, String lastName, String email, String password){

        WebElement personalDataFields[] = {firstNamePersonalData, lastNamePersonalData, emailPersonalData, passwordPersonalData};
        String personelData[]= {firstName,lastName,email,password};

        int i = 0;
        for(WebElement f:personalDataFields){
            f.clear();
            f.sendKeys(personelData[i++]);
        }
    }

    public ArrayList<String>  checkFilledPersonalData(){

        WebElement [] personalDataFields = {firstNamePersonalData, lastNamePersonalData, emailPersonalData, passwordPersonalData};
        ArrayList<String> personalData = new ArrayList<>();

        for(WebElement f:personalDataFields){
           personalData.add(f.getAttribute("value"));
        }

        return personalData;
    }

    public void chooseDateOfBirth (String day, String month, String year){

        Select days = new Select(daysDropown);
        Select months = new Select(monthsDropdown);
        Select years = new Select(yearsDropdown);

        daysDropown.click();
        days.selectByValue(day);
        monthsDropdown.click();
        months.selectByValue(month);
        yearsDropdown.click();
        years.selectByValue(year);

    }

    public void fillAddressData(String firstName, String lastName, String address, String city, String postalCode, String country, String phoneNumber, String aliasAddress) throws InterruptedException{

        WebElement firstAddresInputs  [] = {addressInput, cityInput, postalCodeInput, mobilePhoneNumberInput, aliasAddressInput};
        WebElement secondAddresInputs [] = {firstNameAddressInput, lastNameAddresssInput, addressInput, cityInput, postalCodeInput, mobilePhoneNumberInput, aliasAddressInput};

        String firstData [] = {address,city,postalCode,phoneNumber,aliasAddress};
        String secondData [] = {firstName,lastName,address,city,postalCode,phoneNumber,aliasAddress};

        Select countryD = new Select(countryDropdown);

        int i = 0;
        if(countryField.getText().equals(country)&& lastNameAddresssInput.getAttribute("value").equals(lastName)&& firstNameAddressInput.getAttribute("value").equals(firstName)){

            for (WebElement a:firstAddresInputs){
                a.clear();
                a.sendKeys(firstData[i++]);
            }

        }else {
            countryDropdown.click();
            countryD.selectByVisibleText(country);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            for (WebElement a:secondAddresInputs){
                a.clear();
                a.sendKeys(secondData[i++]);
            }
        }
    }

    public String checkChosenState(String state){
        Select stateD = new Select(stateDropdown);

        stateDropdown.click();
        stateD.selectByVisibleText(state);
        return stateField.getText().trim();
    }

    public void registerAccount() throws InterruptedException {
        if (registerAccountButton.isEnabled()) {
            registerAccountButton.click();
        }
    }
}
