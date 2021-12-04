package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	/*
	 * 
	 * WebDriver 
	 * Properties 
	 * Logs 
	 * ExtentReports 
	 * DB 
	 * Excel 
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				or.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			} else if(config.getProperty("browser").equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			} else if(config.getProperty("browser").equals("safari")) {
				
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				
			} else if(config.getProperty("browser").equals("opera")) {
				
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				
			}
			
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			
		}

	}

	@AfterSuite
	public void tearDown() {

		if(driver != null) {
			driver.quit();
		}

	}

}
