#/bin/bash
mvn package
sudo cp /home/filatov/projects/javatar-cms/target/ROOT.war /opt/tomcat11/webapps/
