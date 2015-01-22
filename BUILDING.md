# Requirements

Building:
* JDK7 or higher
* [Maven](https://maven.apache.org/) 3 or higher

To use the included Vagrant environment:
* OSX or Linux
* [Vagrant](https://www.vagrantup.com)
* Either [VirtualBox](https://www.virtualbox.org/) or [VMware](http://www.vmware.com/products/workstation/)
* If using VirtualBox, [vagrant-vbguest](https://github.com/dotless-de/vagrant-vbguest) is recommended to resolve issues with mismatching VirtualBox Guest Additions
* If using VMware, the [VMware Integration plugin for Vagrant](https://www.vagrantup.com/vmware) is requited
* [Ansible](http://docs.ansible.com/index.html)

# Instructions

Spin up the development VM with `vagrant up`. This will download and configure a VM running CentOS 7, Apache Tomcat 7 and PostgreSQL 9.2.

Compile the project with `mvn verify`. This will perform the following steps:

* Download Google Web Toolkit
* Download third-party libraries and dependencies
* Generate GWT-RPC async service interfaces
* Run unit tests
* Compile the `com.dharmab.sheets.server` and `com.dharmab.sheets.shared` packages to server-side Java
* Compile the `com.dharmab.sheets.client` and `com.dharmab.sheets.shared` packages to client-side JavaScript
* Package the application as a Web Application Archive (WAR), which can be deployed to a Tomcat 7 server

The `vagrant-deploy.sh` script is a convenience script that will run the above build and (re)deploy the WAR to the Vagrant VM. The application will be accessible at [https://localhost:8443](https://localhost:8443). You should receive a warning about an insecure SSL key- this is expected, as the development environment uses an insecure, pregenerated key.

# Troubleshooting

If you can't access the webapp after first building the VM, try restarting the firewall with `vagrant ssh -c 'sudo systemctl restart firewalld'`. I'm not sure why the firewall fails.

For other problems, check the Tomcat logs:

`vagrant ssh`
`sudo -i`
`cd /var/log/tomcat`

Errors related to starting and stopping the application will appear in `catalina.out`, while errors internal to the application will appear in `sheets.<date>.log`.
