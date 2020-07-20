# Selenium_Sample_Example

Selenium Grid on Docker within Jenkins:
1. Create Dockerfile.
2. Run Docker file as: docker build --tag arif434/selenium_dockerfile_test:latest -f Dockerfile .
3. docker login
4. docker push arif434/selenium_dockerfile
5. Create docker-compose.yml (this work similar like terraform)
6. docker-compose up -d
7. docker-compose ps  (Once the test execution is done, you would see Exit 0  (in case of any test failures, you would see Exit 1)
8. docker logs <container id>
9. As of 7/19/2020 Selenium Grid doesn't work V4.0.0, so i added 3.5.2 in the pom.xml 
Selenium grid without docker.
1. Download seleniun server
2. Run: java -jar selenium-server-standalone-3.141.59.jar -role hub
3. java -Dwebdriver.gecko.driver="C:\Users\ahoss1\Desktop\file\Workspace\seleniumgrid\chromekodriver.exe" -Dwebdriver.chrome.driver="C:\Users\ahoss1\Desktop\file\Workspace\seleniumgrid\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5566
4.Sample script:
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
  
