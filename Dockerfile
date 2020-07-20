FROM openjdk:8-jre-slim 
#A Directory in the base image to copy our depedencies
WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/selenium_grid_dockerfile.jar selenium_grid_dockerfile.jar
ADD  target/libs libs

# Add the suite xmls
ADD suite/Get_actual_title.xml Get_actual_title.xml
ADD suite/Get_actual_url.xml Get_actual_url.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# SELENIUM_HUB = selenium hub hostname / ipaddress

ENTRYPOINT java -cp selenium_grid_dockerfile.jar:libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE
