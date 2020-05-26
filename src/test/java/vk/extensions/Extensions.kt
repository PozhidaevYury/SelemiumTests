package vk.extensions

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


fun WebDriver.findElementByCssSelector(cssLocator: String): WebElement =
        this.findElement(By.cssSelector(cssLocator))

fun WebDriver.findElementByXpath(xPath: String): WebElement =
        this.findElement(By.cssSelector(xPath))

fun WebDriver.findElementById(id: String): WebElement =
        this.findElement(By.cssSelector(id))

fun WebDriver.findElementsByXpath(xPath: String): MutableList<WebElement> =
    this.findElements(By.xpath(xPath))

fun WebElement.findElementByXpath(xPath: String): WebElement =
        this.findElement(By.xpath(xPath))

fun WebElement.findElementByCssSelector(cssLocator: String): WebElement =
        this.findElement(By.cssSelector(cssLocator))

