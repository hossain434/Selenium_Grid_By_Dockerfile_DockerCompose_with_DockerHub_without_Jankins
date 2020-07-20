package test.java.selenium_sample_example;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Get_actual_title_NoGrid {
    
    @Test
	public void verifyHomepageTitle() {
    	
		System.setProperty("webdriver.chrome.driver","chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
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