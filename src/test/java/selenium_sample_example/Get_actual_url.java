package test.java.selenium_sample_example;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class Get_actual_url {
		WebDriver driver;

		@Parameters({ "Port" })
		@Test
		public void initiateDriver(String Port) throws MalformedURLException {
			if (Port.equalsIgnoreCase("9002")) {
				String host = System.getProperty("seleniumHubHost");
				driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), DesiredCapabilities.chrome());
				driver.manage().window().maximize();
			}         
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
