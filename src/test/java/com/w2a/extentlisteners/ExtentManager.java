package com.w2a.extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.w2a.base.TestBase;

public class ExtentManager {

    private static ExtentReports extent;
    public static String fileName;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Olivia Bula");
        extent.setSystemInfo("Organization", "Capmation Inc.");
        extent.setSystemInfo("Build no", "Compeer-001");

        return extent;
    }

    public static void captureScreenshot() throws IOException {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

        File screenshot = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/reports/" + fileName));
    }

    public static void captureElementScreenshot(WebElement element) throws IOException {
        Date d = new Date();
        String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

        File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/screenshot/" + "Element_" + fileName));
    }

}
