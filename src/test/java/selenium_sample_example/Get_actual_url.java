package test.java.selenium_sample_example;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Get_actual_url {

    @Test
	public void verifyHomepageUrl() throws MalformedURLException {
        WebDriver driver;

        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if (System.getProperty("browser").equals("firefox"))
//            dc = DesiredCapabilities.firefox();
        String host = System.getProperty("seleniumHubHost");
        
        driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		String baseUrl = "http://demo.guru99.com/test/newtours/";
		String expectedUrl = "http://demo.guru99.com/test/newtours/";
		String actualUrl = "";
		driver.get(baseUrl);
		actualUrl = driver.getCurrentUrl();
		if (actualUrl.contentEquals(expectedUrl)) {
			System.out.println("Test Passed: Get_actual_url - " +actualUrl);
		} else {
			System.out.println("Test Failed" );
		}
		driver.close();
	}
}
