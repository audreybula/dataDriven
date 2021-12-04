package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {

        log.debug("Inside login test");
        driver.findElement(By.xpath(or.getProperty("bmlBtn"))).click();
        log.debug("Bank Manager button clicked");
        Thread.sleep(3000);

    }

}
