To run on a local system 

DOCKER_COMPOSE
1. Dependency is only docker-compose (No java or maven needed)
2. Clone repo 
3. docker-compose up --build 
(takes some time because of multi-stage docker build to save image size)

8. Disadvantage is too much time to start the app -- maven build takes too long 
9. 

LOCALHOST 
1. Dependency is maven, java and docker-compose 
2. clone repo 
3. Run "mvn clean install -Pweb"
4. Run "mvn clean install -Pscheduler"
5. comment proper section of docker-compose file 
6. docker-compose up --build



In Both the above methods 

Only port 8080 is exposed on localhost
talk to each other using service names only 
Even DB is started by docker compose 
DB and scheduler ports are not exposed at all and only accessible on service names
Size of final docker images is around 200 MB !! -> this is advantage of multistage build 


I have worked extensively on Jenkins but this is the first time I am using CircleCI 

