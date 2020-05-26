package vk.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "page_info_wrap")
    private WebElement pageInfo;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void checkPageLoading(){
        wait.until(ExpectedConditions.visibilityOf(pageInfo));
    }
}
