package com.lao.utilities;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FacebookLoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        driver.quit();
    }

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        // Path to your Excel file
        String filePath = "";
        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        fis.close();

        return data;
    }

    @Test(dataProvider = "testData")
    public void executeTest(String keyword, String username, String password) {
        switch (keyword) {
            case "OpentheBrowser":
                // Code to open the browser
                driver.get("https://www.facebook.com/");
                break;

            case "Enter_username":
                // Code to enter username
                driver.findElement(By.id("email")).sendKeys(username);
                break;

            case "Enter_password":
                // Code to enter password
                driver.findElement(By.id("pass")).sendKeys(password);
                break;

            // Add more cases for other keywords as needed

            default:
                System.out.println("Invalid keyword: " + keyword);
                break;
        }
    }
}
