package vk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.function.Supplier;

public enum Browser {
    FIREFOX("gecko", FirefoxDriver::new),
    CHROME("chrome", ChromeDriver::new);
    //IE("ie", InternetExplorerDriver::new);

    private final String name;
    private final Supplier<WebDriver> driverSupplier;

    Browser(String name, Supplier<WebDriver> driverSupplier) {
        this.name = name;
        this.driverSupplier = driverSupplier;
    }

    public String getName() {
        return name;
    }

    public WebDriver getDriver() {
        return driverSupplier.get();
    }

    public static Browser fromString(String value) {
        for (Browser browser : values()) {
            if (value != null && value.toLowerCase().equals(browser.getName())) {
                return browser;
            }
        }
        System.out.println("Invalid driver name passed as 'browser' system property. "
                + "One of: " + Arrays.toString(values()) + " is expected. Defaulting to Chrome.");
        return CHROME;
    }
}
