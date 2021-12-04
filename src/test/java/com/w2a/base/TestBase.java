package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	 * WebDriver - done
	 * Properties - done
	 * Logs - log4j, .log
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
	public static Logger log = LogManager.getLogger(TestBase.class.getName());

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
				log.debug("Config file loaded");
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
				log.debug("Object file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("Firefox browser loaded");
				
			} else if(config.getProperty("browser").equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome browser loaded");
				
			} else if(config.getProperty("browser").equals("safari")) {
				
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				log.debug("Safari browser loaded");
				
			} else if(config.getProperty("browser").equals("opera")) {
				
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				log.debug("Opera browser loaded");
				
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
			log.debug("Test execution completed");
		}

	}

}
