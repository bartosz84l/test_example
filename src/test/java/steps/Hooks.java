package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.logging.Logger;


public class Hooks {

    protected WebDriver driver;

    private static Logger log = Logger.getLogger(Hooks.class.getName());

    public Hooks(WebDriver driver){
        this.driver=driver;
    }

    @Before
    public void clearCookies(){
        driver.manage().deleteAllCookies();
    }

    @After
    public void takeScreenshotWhenFail(Scenario scenario){
        if (scenario.isFailed()) {
            try {
                log.info(scenario.getName() + " is Failed");
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException e) {
                e.printStackTrace();
            }
        }
    }
}
