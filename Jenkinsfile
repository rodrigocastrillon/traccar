node ('traccar') {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      //git 'https://github.com/rodrigocastrillon/traccar.git'
      git 'https://github.com/traccar/traccar'
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
   stage('Stop Server') {
       try {
           def traccarPID = sh(script: "ps -ef|grep traccar|grep -v grep|awk '{ print \$2 }'", returnStdout: true)
           sh "kill -9 ${traccarPID}"
       } catch (e) {
           echo "Traccar server is not running"
       }
   }
   //stage('Results') {
   //   junit '**/target/surefire-reports/TEST-*.xml'
   //   archive 'target/*.jar'
   //}
   stage('Backup') {
       if (fileExists('/opt/traccar')) {
           sh 'mkdir -p /opt/traccar_backup/`date +"%d-%m-%Y"`'
           sh 'cp -Rn /opt/traccar /opt/traccar_backup/`date +"%d-%m-%Y"`'
       } else {
           echo "Traccar not found"
       }
   }
   stage('Deploy') {
       sh "rm -Rf /opt/traccar/*"
       sh "mkdir -p /opt/traccar/conf"
       sh "mkdir -p /opt/traccar/schema"
       sh "mkdir -p /opt/traccar/logs"
       sh "touch /opt/traccar/logs/tracker-server.log"
       sh "cp schema/* /opt/traccar/schema"
       sh "cp target/tracker-server.jar /opt/traccar"
       sh "cp setup/traccar.xml /opt/traccar"
       sh "cp setup/default.xml /opt/traccar/conf"
       sh "cp -Rf target/lib /opt/traccar"
       sh "echo 'java -jar tracker-server.jar traccar.xml &' > /opt/traccar/startserver.sh"
       sh "chmod +x /opt/traccar/startserver.sh"
   }
   stage('Sart Server') {
       dir('/opt/traccar') {
           sh "nohup java -jar tracker-server.jar traccar.xml &"
       }
   } 
}
