package vk.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInFormPage {

    private final String EMPTY_FIELD_COLOR = "background-color: rgb(250, 234, 234);";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "pass")
    private WebElement passwordField;
    @FindBy(id = "login_button")
    private WebElement submitButton;
    @FindBy(id = "login_message")
    private WebElement errorMessage;

    public SignInFormPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void enterPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys("kot4933744");
    }

    public void checkErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void submitForm(){
        submitButton.click();
    }

    public void checkChangeBackgroundColorOfPasswordField() {
        Assert.assertEquals(passwordField.getAttribute("style"), EMPTY_FIELD_COLOR);
    }
}
