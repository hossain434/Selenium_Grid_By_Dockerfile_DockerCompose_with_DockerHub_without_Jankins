package test.java.selenium_sample_example;
import org.testng.annotations.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Get_actual_title {
    
    @Test
	public void verifyHomepageTitle() throws MalformedURLException {
        WebDriver driver;

        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if (System.getProperty("browser").equals("firefox"))
//            dc = DesiredCapabilities.firefox();
        String host = System.getProperty("seleniumHubHost");
        
        driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);	
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);	
		String baseUrl = "http://demo.guru99.com/test/newtours/";
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test Passed: Get_actual_title - " + actualTitle);
		} else {
			System.out.println("Test Failed");
		}
		driver.close();
    }
}
  