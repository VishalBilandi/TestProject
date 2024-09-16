package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Launch_browser01 {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {

        // Initialize the ChromeDriver
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://uat.insurecorrect.com/");
 
    }

//    @AfterClass
//    public void tearDown() throws InterruptedException {
//    	Thread.sleep(5000);
//        // Close the browser and clean up
//        if (driver != null) {
//            driver.quit();
//        }
//    }



}