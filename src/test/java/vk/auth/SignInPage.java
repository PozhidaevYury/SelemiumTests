package vk.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInPage {

    private final String EMPTY_FIELD_COLOR = "background-color: rgb(250, 234, 234);";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "login_message")
    private WebElement errorMessage;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "pass")
    private WebElement passwordField;
    @FindBy(id = "login_button")
    private WebElement submitButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void enterEmailField(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void checkErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void submitForm() {
        submitButton.click();
    }

    public void clearEmailField() {
        emailField.clear();
    }

    public void checkChangeBackgroundColorOfEmailField() {
        Assert.assertEquals(emailField.getAttribute("style"), EMPTY_FIELD_COLOR);
    }

    public void checkChangeBackgroundColorOfPasswordField() {
        Assert.assertEquals(passwordField.getAttribute("style"), EMPTY_FIELD_COLOR);
    }
}
