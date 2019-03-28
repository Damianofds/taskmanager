#!/bin/bash

#install kubectl and virtualbox
apt-get update && apt-get install -y apt-transport-https curl gnupg2
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | tee -a /etc/apt/sources.list.d/kubernetes.list
wget -q https://www.virtualbox.org/download/oracle_vbox_2016.asc -O- | apt-key add -
wget -q https://www.virtualbox.org/download/oracle_vbox.asc -O- | apt-key add -
add-apt-repository "deb http://download.virtualbox.org/virtualbox/debian xenial contrib"
apt-get update
apt-get install -y kubectl virtualbox-6.0

# donwload minikube and add it to bin
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64   && chmod +x minikube
cp minikube /usr/local/bin && rm minikube

#start minikube
minikube start --cpus 4 --memory 8192

#enable ingress and registry
minikube addons enable ingress
minikube addons enable registry