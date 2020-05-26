package vk;

import org.openqa.selenium.WebDriver;

abstract public class WebDriverSettings {

    public final String CHROME = "chrome";
    public final String FIREFOX = "gecko";
    public final String IE = "ie";
    public WebDriver driver;

    public WebDriver createWebDriver(String browserName) {
        Browser browser = Browser.fromString(browserName);
        String driverFileName = browser.getName() + "driver";
        String driverFilePath = "src/main/resources/drivers/";
        System.setProperty("webdriver." + browser.getName() + ".driver", driverFilePath + driverFileName);
        return browser.getDriver();
    }

    abstract public void pageInit();
}
