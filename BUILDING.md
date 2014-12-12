Install the following tools:

* JDK7 or higher
* [Maven](https://maven.apache.org/) 3 or higher
* [Vagrant](https://vagrantup.com)
* Either [VirtualBox](https://www.virtualbox.org/) or [VMware](http://www.vmware.com/products/workstation/)
* If using VirtualBox, [vagrant-vbguest](https://github.com/dotless-de/vagrant-vbguest) is recommended to resolve issues with mismatching VirtualBox Guest Additions
* [Ansible](http://docs.ansible.com/index.html)

Note that Ansible doesn't support Windows control machines, so you need to develop on OSX or Linux.

Spin up the development VM with `vagrant up`. This will download and configure a VM running CentOS 7, Apache Tomcat 7 and PostgreSQL 9.2.

Compile the project with `mvn verify`. This will perform the following steps:

* Download Google Web Toolkit
* Generate GWT-RPC async service interfaces
* Run unit tests
* Compile the `com.dharmab.sheets.server` and `com.dharmab.sheets.shared` packages to server-side Java
* Compile the `come.dharmab.sheets.client` and `com.dharmab.sheets.shared` packages to client-side JavaScript
* Package the application as a Web Application Archive (WAR), which can be deployed to a Tomcat 7 server

The `vagrant-deploy.sh` script is a convenience script that will run the above build and (re)deploy the WAR to the Vagrant VM. The application will be accessible at `https://localhost:8443`. You should receive a warning about an insecure SSL key- this is expected, as the development environment uses an insecure, pregenerated key.
