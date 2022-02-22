package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class BasePage {

   private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
//        this.driver.manage().window().maximize();
    }


    public BasePage navigate(String url)
    {
        driver.get(url);
        return this;
    }
}
