package org.shirokuma.steps;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.Screenshot;
import org.shirokuma.utils.FolderUtil;
import org.shirokuma.utils.ImageMagickUtil;
import org.shirokuma.utils.JSUtil;
import org.shirokuma.utils.ScreenshotUtil;

import java.io.File;

public class Steps {
    public WebDriver driver;
    public WebDriverWait wait;
    public JSUtil jsUtil;
    public String url = "https://www.blibli.com/anchor/samsung";
    public Screenshot googleScreenshot;
    public FolderUtil folderUtil = new FolderUtil();
    public ScreenshotUtil screenshotUtil = new ScreenshotUtil();
    public ImageMagickUtil imageMagickUtil = new ImageMagickUtil();

    public Steps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        jsUtil = new JSUtil(wait, driver);
    }

    @SneakyThrows
    public void preTestSetup() {
        //Create screenshot and differences folders if they are not exist
        folderUtil.createFolder(folderUtil.parentScreenShotsLocation);
        folderUtil.createFolder(folderUtil.parentDifferencesLocation);
        //Clean Differences Root Folder
        File differencesFolder = new File(folderUtil.parentDifferencesLocation);
        FileUtils.cleanDirectory(differencesFolder);
        driver.navigate().to(url);
        jsUtil.waitJS();
        driver.findElement(By.xpath("//button[@class='splide__arrow splide__arrow--next']")).click();
        Thread.sleep(2000);
//        jsUtil.hideDynamicContent();
    }

    public Steps givenITakeScreenShot() {
        //Take ScreenShot with AShot
        googleScreenshot = screenshotUtil.takeScreenshot(driver);
        return this;
    }

    @SneakyThrows
    public Steps whenISaveTheScreenShotsToFolders() {
        //Write actual screenshot to the actual screenshot path
        folderUtil.writeScreenshotToFolder(googleScreenshot);
        return this;
    }

    @SneakyThrows
    public Steps thenIShouldCompareScreenshotsSuccessfully() {
        //Do image comparison
        imageMagickUtil.doComparison(googleScreenshot, folderUtil);
        return this;
    }
}
