
# DOCKER CHALLENGE

## Assumptions
1. Run the application on non Kubernetes environment - (localhost or ubuntu server)
2. Java 7+, Maven 3.5+ and docker-compose (latest) are already installed on the machine
3. Only web app must be exposed. DB and scheduler must not be accessible on localhost.
4. Containers must talk to each other using only service names
5. Size of final docker images must be as small as possible (around 200 MB in this case)

## Option 1 - Java, Maven and docker-compose 

## Method and Solution
1. clone this repo and do a maven build
    ``` git clone git@github.com:gauti038/taskmanager.git 
        cd taskmanager 
        mvn clean install -Pweb 
        mvn clean install -Pscheduler
    ```
2. These commands will download and build all the required files
3. Start docker compose 
    ```
        docker-compose up --build
    ```
4. The docker compose uses docker files - Dockerfile-web-localhost & Dockerfile-scheduler-localhost
5. The dockerfiles just copies the required jar files into a alpine base image (reduce image size)
6. Open http://localhost:8080 for the web application 
7. Open http://localhost:4040/#!/state/{%22pinnedMetricType%22:%22Memory%22,%22topologyId%22:%22containers%22} for weavescope (an advanced realtime container interations UI)
8. dependencies are set to each of the containers

## Option 2 - Only docker-compose

## Method and Solution
1. clone this repo and do a maven build
    ``` git clone git@github.com:gauti038/taskmanager.git 
        cd taskmanager 
        # comment right sections of docker-compose.yml file
        docker-compose up --build
    ```
2. These commands will start multi-stage docker builds to reduce image sizes
3. The docker compose uses docker files - Dockerfile-web-compose & Dockerfile-scheduler-compose
4. The dockerfiles first use maven image (large in size) and does a maven build. The jar is then copied onto a light-weight java alpine docker image 
5. The disadvantage of this method is - it takes too much time to build (15+ mins) 
6. Once the containers start, open http://localhost:8080 for the web application 
7. Open http://localhost:4040/#!/state/{%22pinnedMetricType%22:%22Memory%22,%22topologyId%22:%22containers%22} for weavescope (an advanced realtime container interations UI)
8. dependencies are set to each of the containers

Weave scope is a beautiful UI which shows live interactions between pods. 
more at https://www.weave.works/oss/scope/ 

# Minikube Solution

## Install minikube  on Ubuntu :
    sudo sh minikube.sh
    This will install minikube and start it with 4 CPUs and 8GB RAM
    Also enables ingress and registry of minikube addons

## Solution

1. Install weavescope for better visual representation

``` kubectl apply -f "https://cloud.weave.works/k8s/scope.yaml?k8s-version=$(kubectl version | base64 | tr -d '\n')" ```
2. Use minikube registry 
    ``` eval $(minikube docker-env) ```
3. Maven build and install
    ```
    mvn clean install -Pweb
    mvn clean install -Pscheduler
    ```
4. Build docker images required
    ```
    docker build -t web:minikube -f ./Dockerfile-web-localhost .
    docker build -t scheduler:minikube -f ./Dockerfile-scheduler-localhost . 
    ```
    This builds the image and stores them in the docker registry of minikube
5. Pull postgres image 
    ``` docker pull postgres ```
6. Apply the Kubernetes yaml files
    ```
    kubectl apply -f k8s/postgres.yaml
    kubectl apply -f k8s/web.yaml
    kubectl apply -f k8s/scheduler.yaml
    kubectl apply -f k8s/ingress.yaml
    ```
7. Add domain name needed for the ingress IP
    ``` echo "$(minikube ip) omnius-challenge.demo" | sudo tee -a /etc/hosts ```
8. Open http://omnius-challenge.demo/ on browser and you can access webapp 






