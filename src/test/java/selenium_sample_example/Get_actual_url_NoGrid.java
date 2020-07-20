package test.java.selenium_sample_example;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Get_actual_url_NoGrid {

	@Test
	  public void verifyHomepageUrl() {
		System.setProperty("webdriver.chrome.driver","chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
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
