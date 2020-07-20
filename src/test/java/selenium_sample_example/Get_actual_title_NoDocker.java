package test.java.selenium_sample_example;
import org.testng.annotations.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Get_actual_title_NoDocker {
    
    @Test
	public void verifyHomepageTitle() throws MalformedURLException {
        WebDriver driver;

        DesiredCapabilities dc = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://192.168.1.152:4444/wd/hub"), dc);	
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
  