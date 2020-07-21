package test.java.selenium_sample_example;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Get_actual_title {
	WebDriver driver;

	@Parameters({ "Port" })
	@Test
	public void initiateDriver(String Port) throws MalformedURLException {
		if (Port.equalsIgnoreCase("9001")) {
			String host = System.getProperty("seleniumHubHost");
			driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), DesiredCapabilities.chrome());
			driver.manage().window().maximize();
		}
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
