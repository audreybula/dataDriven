package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String fname, String lname, String postCode, String alertMsg) {

        log.debug("Add Customer test started");

        driver.findElement(By.xpath(or.getProperty("addCustBtn"))).click();
        driver.findElement(By.xpath(or.getProperty("fnameInput"))).sendKeys(fname);
        driver.findElement(By.xpath(or.getProperty("lnameInput"))).sendKeys(lname);
        driver.findElement(By.xpath(or.getProperty("postCodeInput"))).sendKeys(postCode);
        driver.findElement(By.xpath(or.getProperty("submitCustBtn"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alertMsg), "Alert is not displayed correctly");
        alert.accept();

        log.debug("Add Customer test completed successfully");

    }

    @DataProvider
    public Object[][] getData() {

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][cols];

        for(int rowNum = 2; rowNum <= rows; rowNum++) {

            for(int colNum = 0; colNum < cols; colNum++) {

                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);

            }

        }

        return data;

    }

}
