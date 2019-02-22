package com.bdd.dou.task.steps;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Base steps for JBehave framework.
 * Class can provide general steps for interaction with system, or contain pre/post conditional
 */
public class BaseSteps {

    //driver object has protected modifier to be shared between other Steps classes
    protected WebDriver driver;

    /**
     * To use webdriver we should download webdriver binary (e.g. chromedriver.exe) and set system variable with path
     * before you can use it. WebDriverManager can do it itself.
     * WebDriverManager used to download and setup webdriver executable.
     *
     * @see <a href="https://github.com/bonigarcia/webdrivermanager">webdriver manager/</a>
     * @see <a href="https://github.com/bonigarcia/webdrivermanager-examples">webdriver manager examples/</a>
     */
    @BeforeStory
    public void setUp() {
        WebDriverManager.getInstance(getDriverType()).setup();
    }

    /**
     * should clean up and reconfigure web driver before each scenario
     */
    @BeforeScenario(uponType = ScenarioType.ANY)
    public void beforeScenario() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     * should close web driver after each UI story
     */
    @AfterScenario(uponType = ScenarioType.ANY)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Returns webdriver type, if specified in command line (or VM Options)
     * Can be specified like: -Ddriver=chrome
     * Returns CHROME type by default.
     *
     * @return DriverManagerType object
     */
    private DriverManagerType getDriverType() {
        String driverTypeStr = System.getProperty("driver", "chrome");
        return DriverManagerType.valueOf(driverTypeStr.toUpperCase());
    }

}
