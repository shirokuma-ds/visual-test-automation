package org.shirokuma.tests;

import java.lang.reflect.Method;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.shirokuma.steps.Steps;
import org.shirokuma.utils.ChromeOptionsUtil;

public class BaseTest {
    ChromeOptionsUtil chromeOptionsUtil = new ChromeOptionsUtil();
    public WebDriver driver;
    Steps steps;

    @BeforeClass
    public void setupTestClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptionsUtil.getChromeOptions());
        driver.manage().window().maximize();
        steps = new Steps(driver);
        steps.preTestSetup();
    }

    @BeforeMethod
    public void setupTestMethod(Method method) {
        steps.folderUtil.setUpFilesAndFolders(method.getName());
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
