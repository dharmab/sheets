#!/bin/sh
mvn verify
[ $? -eq 0 ] && vagrant ssh -c "sudo cp -f /vagrant/target/*.war /var/lib/tomcat/webapps"
