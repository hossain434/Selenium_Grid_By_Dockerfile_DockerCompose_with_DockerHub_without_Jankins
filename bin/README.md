# Selenium_Sample_Example

1.	Create a maven project. Create a sample selenium script. My intension here to get scratch knowledge on testng, selenium and maven project and hook up with Jenkins for continuous integration.
2.	Add testng in pom.xml.
3.	In Eclipse, install testng from help->install new software-> Add new URL to install: https://dl.bintray.com/testng-team/testng-eclipse-release/6.14.3/
4.	Right click on project->Properties->Java Build path->Add library->select testng.
5.	Right click on project->testng-> convert into testng->testng.xml will create.
6.	Build the project by clean and install unless testng library will not be added and fail afterwards.
7.	Open testng.xml file. You may need to remove https://testng.org/testng-1.0.dtd unless you will get weird error that suite is not defined. I don’t know why but this URL may need on other platform.
8.	In testng.xml, add script you want to run. This could be single, multiple script or entire test suite. Useful doc: https://testng.org/doc/documentation-main.html
9.	In pom.xml, update the scope from ‘test’ to ‘compile’ unless maven build will ignore testng test suite/script and build will success without running any test.
10.	Run chrome on headless mode before configure in the Jenkins.
