package vk.feed

import org.openqa.selenium.*
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import vk.extensions.findElementByCssSelector
import vk.extensions.findElementByXpath
import vk.extensions.findElementsByXpath
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class FeedPage(private val driver: WebDriver) {

    private val TITLE_OF_PAGE = "Новости"
    private val wait: WebDriverWait = WebDriverWait(driver, 5)

    @FindBy(id = "top_profile_link")
    private lateinit var profileLink: WebElement

    @FindBy(id = "top_logout_link")
    private lateinit var logoutButton: WebElement

    @FindBy(className = "reply_fakebox")
    private lateinit var editCommentField: WebElement

    fun open() {
        driver.navigate().to("https://vk.com/feed")
        readCookies()
    }

    fun checkTitlePage() {
        wait.until(ExpectedConditions.titleIs(TITLE_OF_PAGE))
    }

    fun saveCookies() {
        val file = File("Cookies.data")
        try {
            file.delete()
            file.createNewFile()
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)
            for (cookie in driver.manage().cookies) {
                bufferedWriter.write(
                        cookie.name + ";" +
                                cookie.value + ";" +
                                cookie.domain + ";" +
                                cookie.path + ";" +
                                cookie.expiry + ";" +
                                cookie.isSecure
                )
                bufferedWriter.newLine()
            }
            bufferedWriter.close()
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readCookies() {
        val file = File("cookies.data")
        val fileReader = FileReader(file)
        val bufferReader = BufferedReader(fileReader)
        var strline: String?
        val cookies: MutableSet<Cookie> = HashSet<Cookie>()
        while (bufferReader.readLine().also { strline = it } != null) {
            val token = StringTokenizer(strline, ";")
            while (token.hasMoreTokens()) {
                val name = token.nextToken()
                val value = token.nextToken()
                val domain = token.nextToken()
                val path = token.nextToken()
                var expiry: Date? = null
                var str: String?
                val formatter = SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH)
                if (token.nextToken().also { str = it } != "null") {
                    expiry = formatter.parse(str)
                }
                val isSecure = token.nextToken().toBoolean()
                cookies.add(Cookie(name, value, domain, path, expiry, isSecure))
            }
        }

        for (cookie in cookies) {
            driver.manage().addCookie(cookie)
        }
        driver.navigate().to("https://vk.com/feed")
    }

    fun checkNumberOfPosts() {
        val posts = driver.findElementsByXpath("//div[contains(@class, 'feed_row ')]")
        wait.until(ExpectedConditions.visibilityOfAllElements(posts))
        Assert.assertEquals(posts.size, 10)
    }

    fun loadPosts() {
        var numberOfPosts = 10
        for (i in 0..5) {
            val jsExecutor = driver as JavascriptExecutor
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)")
            val posts = driver.findElementsByXpath("//div[contains(@class, 'feed_row ')]")
            wait.until(ExpectedConditions.visibilityOfAllElements(posts))
            Assert.assertEquals(posts.size, numberOfPosts)
            numberOfPosts += 10
        }
    }

    fun clickLikeButton() {
        val posts: MutableList<WebElement> = driver.findElementsByXpath("//div[contains(@class, 'feed_row ')]")
        val likeButton = posts[0]
                .findElementByXpath("//div[contains(@class, 'like_btns')]")
                .findElementByCssSelector("[class=\"like_btn like _like\"]")

        likeButton.click()

        Assert.assertEquals(likeButton.getAttribute("class"), "like_btn like _like animate active")
    }

    fun editComment(text: String) {
        wait.until(ExpectedConditions.visibilityOf(editCommentField))
        editCommentField.sendKeys(text)
    }

    fun logout() {
        wait.until(ExpectedConditions.visibilityOf(profileLink))
        profileLink.click()
        wait.until(ExpectedConditions.visibilityOf(logoutButton))
        logoutButton.click()
    }

}