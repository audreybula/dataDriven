package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {

        log.debug("Inside Bank manager login test");

        driver.findElement(By.xpath(or.getProperty("bmlBtn"))).click();
        log.debug("Bank Manager button clicked");

        Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("addCustBtn"))), "Add customer button not present");

        log.debug("Executed Bank manager login test successfully");

        Assert.fail("Failing bank manager login test");

    }

}
