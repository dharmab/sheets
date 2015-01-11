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
* Generate GWT-RPC async service interfaces
* Run unit tests
* Compile the `com.dharmab.sheets.server` and `com.dharmab.sheets.shared` packages to server-side Java
* Compile the `come.dharmab.sheets.client` and `com.dharmab.sheets.shared` packages to client-side JavaScript
* Package the application as a Web Application Archive (WAR), which can be deployed to a Tomcat 7 server

The `vagrant-deploy.sh` script is a convenience script that will run the above build and (re)deploy the WAR to the Vagrant VM. The application will be accessible at `https://localhost:8443`. You should receive a warning about an insecure SSL key- this is expected, as the development environment uses an insecure, pregenerated key.

# Troubleshooting

If the app doesn't work after deploying to the Vagrant VM, try restarting Tomcat with  `vagrant ssh -c 'sudo systemctl restart tomcat'`. This occurs because Tomcat tries to deploy the WAR during the provisioning phase. Since the WAR hasn't been built and deployed at this stage, Tomcat can't find the file and errors. Restarting Tomcat is a simple way of forcing it to find the newly compiled WAR. This should only be required for the first deployment- subsequent redeployments should be detected and Tomcat should reload the WAR automatically.

For other problems, check the Tomcat logs:

`vagrant ssh`
`sudo -i`
`cd /var/log/tomcat`

Errors related to starting and stopping the application will appear in `catalina.out`, while errors internal to the application will appear in `sheets.<date>.log`.
