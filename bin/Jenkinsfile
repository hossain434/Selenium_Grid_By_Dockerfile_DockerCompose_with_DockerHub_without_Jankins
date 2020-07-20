//Jenkins file example
pipeline {
  agent any
        
  stages {
        
    stage('Cloning Git') {
      steps {
        git 'https://github.com/hossain434/Selenium_Sample_Example'
      }
    }
        
    stage('Install dependencies') {
      steps {
        sh 'mvn clean package'
      }
    }
     
    stage('Test') {
      steps {
         sh 'mvn install package'
      }
    }      
  }
}
