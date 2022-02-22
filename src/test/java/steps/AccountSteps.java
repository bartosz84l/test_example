package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AccountFormPage;
import pages.AccountPage;
import pages.SignInPage;
import pages.MainPage;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class AccountSteps {
    MainPage mainPage;
    SignInPage signInPage;
    AccountPage accountPage;
    AccountFormPage accountFormPage;


    public AccountSteps(MainPage mainPage, SignInPage signInPage, AccountPage accountPage,AccountFormPage accountFormPage) {
        this.mainPage = mainPage;
        this.signInPage = signInPage;
        this.accountPage = accountPage;
        this.accountFormPage = accountFormPage;
    }

    private static String timeStamp(){
        return new SimpleDateFormat("hhmm").format(new Date());
    }

    private String randomEmail = "barp"+timeStamp()+"@gmail.com";

    @Given("^User is on a main page$")
    public void userIsonAMainPage() {
        mainPage.goToPage();
    }

    @When("^User provides his email address$")
    public void userRegisterOnThePage() {
        mainPage.goToSignInPage();
        signInPage.goToAccountFormPage(randomEmail);

    }

    @And("^User fills the registration form with his personal data: ([^\"]*),([^\"]*),email,([^\"]*),([^\"]*),([^\"]*),([^\"]*)$")
    public void userFillPersonalData(String firstName, String lastName, String password, String day, String month, String year) throws Throwable{

        assertTrue(accountFormPage.checkIfFormIsDisplayed());
        accountFormPage.chooseMaleGender();
        assertTrue(accountFormPage.checkIfMaleMaleGenderSelected());
        accountFormPage.fillPersonalData(firstName, lastName, randomEmail, password);
        List actualPersonalData = accountFormPage.checkFilledPersonalData();
        List<String> expectedPersonalData = Arrays.asList(firstName, lastName, randomEmail, password);
        assertEquals(expectedPersonalData,actualPersonalData);
        accountFormPage.chooseDateOfBirth(day,month,year);

    }

    @And("^User provides address data:([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*)$")
    public void userProvidesAddressData(String firstName, String lastName, String address, String city, String state, String postalCode, String country, String phoneNumber, String aliasAddress) throws Throwable{

        String actualState = accountFormPage.checkChosenState(state);
        accountFormPage.fillAddressData(firstName,lastName,address,city,postalCode,country,phoneNumber,aliasAddress);
        assertEquals(state,actualState);
        accountFormPage.registerAccount();

    }


    @Then("^User has successfully created an account as a ([^\"]*),([^\"]*)$")
    public void userIsOnHisAccountPage(String firstName, String lastName) {
        String expectedAccountName = firstName +" "+ lastName;
        String actualAccountName = accountPage.checAccountName();
        String expectedAccountInformation = "Welcome to your account. Here you can manage all of your personal information and orders.";
        String actualAccountInformation = accountPage.checkAccountInformation();
        assertEquals(expectedAccountName, actualAccountName);
        assertEquals(expectedAccountInformation, actualAccountInformation);

    }

    @And("^User can login to his account at any time with email and ([^\"]*)$")
    public void userCanLoginToHisAccount(String password){
        accountPage.logoutFromAccount();
        signInPage.fillLoginData(randomEmail,password);
        signInPage.loginToThePage();
        assertTrue(accountPage.checkIfLogOutButtonIsVisible());






    }


}
