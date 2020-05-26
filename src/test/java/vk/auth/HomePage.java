package vk.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

    private final String EMPTY_FIELD_COLOR = "background-color: rgb(250, 234, 234);";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "index_email")
    private WebElement emailField;
    @FindBy(id = "index_pass")
    private WebElement passwordField;
    @FindBy(id = "index_login_button")
    private WebElement submitButton;
    @FindBy(id = "index_user_row597019808")
    private WebElement userForm;
    @FindBy(id = "login_button")
    private WebElement loginButton;
    @FindBy(id = "pass")
    private WebElement passwordFormField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void open() {
        driver.get("https://vk.com/");
    }

    public void enterEmailField(String number) {
        emailField.sendKeys(number);
    }

    public void enterPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void submitForm() {
        submitButton.click();
    }

    public void userFormClick() {
        userForm.click();
    }

    public void clickLoginButtonOnUserForm() {
        loginButton.click();
    }

    public void enterPasswordFormField(String password) {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        passwordFormField.sendKeys(password);
    }

    public void checkChangeBackgroundColorOfEmailField() {
        Assert.assertEquals(emailField.getAttribute("style"), EMPTY_FIELD_COLOR);
    }

    public void checkChangeBackgroundColorOfPasswordField() {
        Assert.assertEquals(passwordField.getAttribute("style"), EMPTY_FIELD_COLOR);
    }
}
