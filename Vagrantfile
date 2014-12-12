# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = '2'

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.define 'development' do |development|
    development.vm.box = 'chef/centos-7.0'
    development.vm.network "forwarded_port", guest: 80, host: 8080
    development.vm.network "forwarded_port", guest: 443, host: 8443
    development.vm.network "forwarded_port", guest: 5432, host: 5432
  end

  config.vm.provision 'ansible', run: 'always' do | ansible |
    ansible.groups = {
        'sheets' => ['development']
    }
    ansible.playbook = 'provisioning/ansible/site.yml'
  end
end
