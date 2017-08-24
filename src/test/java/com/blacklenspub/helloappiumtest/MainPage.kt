package com.blacklenspub.helloappiumtest

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class MainPage(driver: AppiumDriver<WebElement>) {
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @AndroidFindBy(id = "com.blacklenspub.helloappium:id/tvLabel")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[1]")
    lateinit var myLabel: WebElement
    @AndroidFindBy(id = "com.blacklenspub.helloappium:id/btnGreet")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[1]")
    lateinit var myButton: WebElement
}