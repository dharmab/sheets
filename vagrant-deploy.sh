#!/bin/sh
mvn verify && vagrant ssh -c "sudo cp -f /vagrant/target/*.war /var/lib/tomcat/webapps"
