#!/bin/sh
mvn verify && vagrant ssh -c "sudo cp -f /vagrant/target/sheets*.war /opt/sheets/sheets.war"
