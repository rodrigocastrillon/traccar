node ('traccar') {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/rodrigocastrillon/traccar.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' package"
      } else {
         bat(/"${mvnHome}\bin\mvn" package/)
      }
   }
   //stage('Results') {
   //   junit '**/target/surefire-reports/TEST-*.xml'
   //   archive 'target/*.jar'
   //}
   stage('Deploy') {
      sh "mkdir -p /opt/traccar/conf"
      sh "mkdir -p /opt/traccar/schema"
      sh "cp schema/* /opt/traccar/schema"
      sh "cp target/tracker-server-jar-with-dependencies.jar /opt/traccar"
      sh "cp setup/traccar.xml /opt/traccar"
      sh "cp setup/default.xml /opt/traccar/conf"
      sh "echo 'java -jar tracker-server-jar-with-dependencies.jar traccar.xml' > /opt/traccar/startserver.sh"
      sh "chmod +x /opt/traccar/startserver.sh"
   }
}
