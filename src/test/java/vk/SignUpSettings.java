package vk;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import vk.auth.HomePage;
import vk.auth.SignInFormPage;
import vk.auth.SignInPage;
import vk.feed.FeedPage;
import vk.user.UserPage;

public class SignUpSettings extends WebDriverSettings {

    public HomePage homePage;
    public FeedPage feedPage;
    public SignInPage signInPage;
    public UserPage userPage;
    public SignInFormPage signInFormPage;

    @BeforeMethod
    public void setUp() {
        driver = createWebDriver(CHROME);
        //driver = createWebDriver(FIREFOX);
        //driver = createWebDriver(IE);

        pageInit();
        homePage.open();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Override
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        feedPage = PageFactory.initElements(driver, FeedPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        userPage = PageFactory.initElements(driver, UserPage.class);
        signInFormPage = PageFactory.initElements(driver, SignInFormPage.class);
    }
}
