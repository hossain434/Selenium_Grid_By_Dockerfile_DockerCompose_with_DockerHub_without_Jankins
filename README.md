#### Selenium_Sample_Example

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
  
### Docker Basic

1.	Docker basic: https://testcollab.com/blog/using-docker-to-manage-and-replicate-test-environments/
2.	Dockerfile: A Dockerfile is a text document that contains the instructions to set up an environment for a Docker container. You can build a Docker image using a Dockerfile.
3.	Sample file:
FROM openjdk:8-jre-slim

#A Directory in the base image to copy our depedencies
WORKDIR /usr/share/tag

#### # Add the project jar & copy dependencies
ADD  target/selenium_grid_dockerfile.jar selenium_grid_dockerfile.jar
ADD  target/libs libs

#### # Add the suite xmls
ADD suite/Get_actual_title.xml Get_actual_title.xml
ADD suite/Get_actual_url.xml Get_actual_url.xml

#### # Command line to execute the test
#### # Expects below ennvironment variables
#### # BROWSER = chrome / firefox
#### # MODULE  = order-module / search-module
#### # SELENIUM_HUB = selenium hub hostname / ipaddress

ENTRYPOINT java -cp container-test.jar:libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE
4.	Dockerlogin: docker login
5.	Building Docker images: go to the file location and type: docker build --tag arif434/selenium_dockerfile_test:latest -f Dockerfile .
(In eclipse, run as -> Docker image build, install dockerfile editor on eclipse)
6.	To re-name or tagging exisitng docker image: docker tag selenium_dockerfile:latest arif434/selenium_dockerfile
7.	To push into docker hub: docker push arif434/selenium_dockerfile
(arif434-> docker hub repo name, selenium_dockerfile -> Image name, latest -> version (option), by default takes latest one if version not provided. This push command will push the image into dockerhub location with the same repo, image and tag name and this is public by default, it will overwrite if any same repo already exist )
8.	To run image: docker run -it --entrypoint bash arif434/selenium_dockerfile
(‘ENTRYPOINT’ command is used to execute the script, every custom image has their own entrypoint to login and run their image.)
9.	CMD command : The CMD command tells Docker how to run the application we packaged in the image. The CMD follows the format CMD [“command”, “argument1”, “argument2”]. e.g, CMD ["npm", "start"]
10.	To create and run any image, just type: docker run -t -i -p8080:5000 ubuntu:14.04
(p8080:5000 is optional, see step#1 for more details.)
11.	Docker logs: docker logs <container-id>
12.	To inspect: docker inspect <container-id>

### Docker additional Info

1.	https://github.com/hossain434/Docker_Sel_Grid_1 , In this script ‘OrderTest’ checks the homepage dropdown and ‘SearchTest’ checks the google search result.
2.	Windows support only Docker Version 17.09.0-ce-win32. After install, docker app visible at bottom right corner of notification screen. Make sure in the settings check ‘Expose daemon on TCP://localhost:2375..’
3.	Docker file: This is called source code for Image. This is useful if we have one image.
FROM openjdk:8-jre-slim

#### # Add the project jar & copy dependencies
ADD  target/container-test.jar /usr/share/tag/container-test.jar (Jar version like 0.0.1  we don’t need to consider )
ADD  target/libs /usr/share/tag/libs

#### # Add the suite xmls
ADD order-module.xml /usr/share/tag/order-module.xml
ADD search-module.xml /usr/share/tag/search-module.xml

#### # Command line to execute the test
#### # Expects below ennvironment variables
#### # BROWSER = chrome / firefox
#### # MODULE  = order-module / search-module
#### # GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT /usr/bin/java -cp /usr/share/tag/container-test.jar….

a.	FROM openjdk:8-jre-slim – openjdk is a Docker Image with version 8. This Image has facility to create Java environment and run Java app.
More: https://www.oreilly.com/learning/5-simple-tips-for-building-your-first-docker-image-with-java
b.	‘ADD’ command is used to copy file to Image. So we can copy Jar, xml there to execute script.
To copy multiple files in a folder use ‘VOLUME’ command (I need to know how to use this).
c.	‘ENTRYPOINT’ command is used to execute the script.
4.	In each Image we can create folder like arif/test (‘test’ image under ‘arif’ folder) or we can use their existing folder like ADD  target/libs /usr/share/tag/libs – here ‘libs’ folder has been copied from local to /usr/share/tag/libs (We need to configure this in POM.xml).
5.	Container creates automatically when Image is built and executed like when we hit ‘docker run’ in command prompt. Multiple images can be in one container but cannot be opposite. A docker container exits when its main process finishes.
Use: docker run -dit ubuntu to run the container in background in interactive mode. (I need to verify this)
6.	Docker-compose.yml: Used when there are multiple images like image for Selenium hub, Chrome, Firefox etc and they are depended on each other.
http://www.testautomationguru.com/selenium-docker-integration-through-jenkinsfile-part-2-building-docker-image-pushing-to-dockerhub/
Below yml file to setup Selenium Grid:

version: "3"   // compose file format
services:
  selenium-hub:
    image: selenium/hub   // image name ‘hub’ (built in) under selenium folder.
    container_name: selenium-hub  // image ‘hub’ will launch the running container named ‘selenium-hub’

    ports:
      - "4444:4444"      // Image ‘hub’ will use this port
  chrome:
    image: selenium/node-chrome   // built in image: node-chrome
    depends_on:
      - selenium-hub  // node-chrome depends on container: selenium-hub  
    environment:
      - HUB_PORT_4444_TCP_ADDR=selenium-hub
      - HUB_PORT_4444_TCP_PORT=4444
  firefox:
    image: selenium/node-firefox
    depends_on:
      - selenium-hub
    environment:
      - HUB_PORT_4444_TCP_ADDR=selenium-hub
      - HUB_PORT_4444_TCP_PORT=4444
  search-module:
    image: vinsdocker/containertest:demo // this is docker service and will create during docker build like “docker.build("vinsdocker/containertest")”. Please note that this file cannot be deleted by prune, rm command. This is required to use this command to remove: docker service rm vinsdocker/containertest

    container_name: search-module
    depends_on:
      - firefox
      - chrome
    environment:
      - MODULE=search-module.xml
      - BROWSER=firefox
      - SELENIUM_HUB=selenium-hub
  order-module:
    image: vinsdocker/containertest:demo  // this is docker service
    container_name: order-module
    depends_on:
      - firefox
      - chrome
    environment:
      - MODULE=order-module.xml
      - BROWSER=chrome
- SELENIUM_HUB=selenium-hub
6. maven doesn’t build docker-compose.yml (don’t know why) so I run this file from command prompt.
Through command prompt, I went to the file location and run
docker-compose up -d
Status: docker-compose ps
To bring down: docker-compose down
To increase chrome instance: docker-compose scale chrome=5
Please check the steps here for the installation of docker-compose.
http://localhost:4444/grid/console

 

To run script: docker run -e SELENIUM_HUB=10.195.79.159 -e MODULE=search-module.xml -e BROWSER=chrome vinsdocker/containertest:demo (here Ip address nothing but the local host of my machine. If I type localhost, it’s not working. I need to find better way to run script.)
7.	By default the plugin will try to connect to docker on localhost:2375. If the docker is not running on your machine and you would like to use remote docker, set the DOCKER_HOST environment variable.

DOCKER_HOST=tcp://<host>:2375
8.	Below config is used to create repository. I don’t know why we need to add since we added this image in docker-compose.yml.

<configuration>
					<repository>vinsdocker/containertest</repository>
					<tag>demo</tag>
				</configuration>

