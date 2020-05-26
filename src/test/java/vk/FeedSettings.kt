package vk

import org.openqa.selenium.support.PageFactory
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import vk.feed.FeedPage

open class FeedSettings : WebDriverSettings() {

    lateinit var feedPage: FeedPage

    @BeforeMethod
    fun setUp() {
        driver = createWebDriver(CHROME)
        pageInit()
        feedPage.open()
    }

    @AfterMethod
    fun close() {
       // driver.quit()
    }

    override fun pageInit() {
        feedPage = PageFactory.initElements(driver, FeedPage::class.java)
    }
}