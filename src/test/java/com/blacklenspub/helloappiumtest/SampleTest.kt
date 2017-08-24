package com.blacklenspub.helloappiumtest

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.MobilePlatform
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

class SampleTest {

    lateinit var driver: AppiumDriver<WebElement>
    val platform = MobilePlatform.IOS

    @Before
    fun setup() {
        if (platform == MobilePlatform.ANDROID) {
            val dir = System.getProperty("user.dir")
            val path = Paths.get(dir, "apps/hello-appium.apk")
            val capabilities = DesiredCapabilities().apply {
                setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
                setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
                setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2)
                setCapability(MobileCapabilityType.APP, path.toAbsolutePath().toString())
            }
            driver = AppiumDriver<WebElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        } else if (platform == MobilePlatform.IOS) {
            val dir = System.getProperty("user.dir")
            val path = Paths.get(dir, "apps/HelloAppium.app")
            val capabilities = DesiredCapabilities().apply {
                setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
                setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7")
                setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3")
                setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST)
                setCapability(MobileCapabilityType.APP, path.toAbsolutePath().toString())
            }
            driver = AppiumDriver<WebElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        }
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS)
    }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun testGreet() {
        val mainPage = MainPage(driver)
        Assert.assertTrue(mainPage.myLabel.text == "Label")
        mainPage.myButton.click()
        Assert.assertTrue(mainPage.myLabel.text == "Hello Appium")
    }
}