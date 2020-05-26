package vk.auth;

import org.testng.annotations.Test;
import vk.SignUpSettings;

public class SignInTest extends SignUpSettings {

    @Test
    public void signInEnterDataTest() {
        homePage.enterEmailField("number");
        homePage.enterPasswordField("password");
        homePage.submitForm();
        feedPage.checkTitlePage();
        feedPage.saveCookies();
    }

    // If login or password is incorrect, user will be redirected to login page
    @Test
    public void signInAfterIncorrectDataTest() {
        // homePage.enterEmailField("incorrectEmail");
        // homePage.enterPasswordField("qwerty123");
        // homePage.submitForm();
        driver.get("https://vk.com/login?m=1&email=79511822926");
        signInPage.checkErrorMessage();
        signInPage.enterEmailField("number");
        signInPage.enterPasswordField("password");
        signInPage.submitForm();
        userPage.checkPageLoading();
    }

    // To get a useForm on the homePage you must first login
    @Test
    public void signInWithUserFormTest() {
        signInEnterDataTest();
        feedPage.logout();
        homePage.userFormClick();
        homePage.enterPasswordFormField("password");
        homePage.clickLoginButtonOnUserForm();
        feedPage.checkTitlePage();
    }

    @Test
    public void signInWithUserFormIncorrectPasswordTest() {
        signInEnterDataTest();
        feedPage.logout();
        homePage.userFormClick();
        homePage.enterPasswordFormField("incorrectPassword");
        homePage.clickLoginButtonOnUserForm();
        signInFormPage.checkErrorMessage();
        signInFormPage.enterPasswordField("password");
        signInFormPage.submitForm();
        userPage.checkPageLoading();
    }

    @Test
    public void signInWithEmptyFieldsTest() {
        homePage.submitForm();
        homePage.checkChangeBackgroundColorOfEmailField();
    }

    @Test
    public void signInWithEmptyEmailFieldTest() {
        homePage.enterPasswordField("qwerty123");
        homePage.submitForm();
        homePage.checkChangeBackgroundColorOfEmailField();
    }

    @Test
    public void signInWithEmptyPasswordFieldTest() {
        homePage.enterEmailField("qwerty123");
        homePage.submitForm();
        homePage.checkChangeBackgroundColorOfPasswordField();
    }

    @Test
    public void signInWithSpacesInEmail() {
        homePage.enterEmailField("      number       ");
        homePage.enterPasswordField("password");
        homePage.submitForm();
        feedPage.checkTitlePage();
    }

    @Test
    public void signInWithEmptyFieldsOnSignInPage() {
        // homePage.enterEmailField("correctEmail");
        // homePage.enterPasswordField("incorrectPassword");
        // homePage.submitForm();
        driver.get("https://vk.com/login?m=1&email=79511822926");
        signInPage.clearEmailField();
        signInPage.submitForm();
        signInPage.checkChangeBackgroundColorOfEmailField();
    }

    @Test
    public void signInWithEmptyPasswordFieldOnSignInPage() {
        // homePage.enterEmailField("correctEmail");
        // homePage.enterPasswordField("incorrectPassword");
        // homePage.submitForm();
        driver.get("https://vk.com/login?m=1&email=79511822926");
        signInPage.enterEmailField("correctEmail");
        signInPage.submitForm();
        signInPage.checkChangeBackgroundColorOfPasswordField();
    }
}
