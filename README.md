# taskmanager
A spring4 + Angular exercise bulding a task management website


## Architecture

![architecture](/doc/taskmanager.png?raw=true)

you can have a look at a live drawing [here](https://docs.google.com/drawings/d/1Kst-gEPnU7SV6RhGqVKwuxKECHpmFvoV097tDaNgXAg).

## Requirements

* git
* JDK8
* Maven3
* Postgresql
* Docker

## Build

### Building with maven

Clone this repo and build first the **web** modules: *endpoints*, *service*, *persistance*.
Note that the latter 2 are shared also with the scheduler.

```
#$ mvn clean install -Pweb
#$ mvn eclipse:clean eclipse:eclipse -Pweb
```

Then build the **scheduler**:
```
#$ mvn clean install -Pscheduler
#$ mvn eclipse:clean eclipse:eclipse -Pscheduler
```

If you want also to produce the Docker images for both components add the profile **docker** e.g.

```
#$ mvn clean install -Pscheduler,docker
```

## Run

### Database configuration

Long story short: create the Postgres database described below and then skip to **Option1** or **Option2**
* name **taskmanager**
* on **localhost**
* port **5432**
* user **postgres**
* password **postgres**

Here hare some more details... 

the database connection params are stored in 

```
<root_repo>\endpoints\src\main\resources\application.properties

<root_repo>\tasks-manager\scheduler\src\main\resources\application.properties
```

and by default are configured for postgres, the web component is set to CREATE-DROP and the scheduler to VALIDATE. So the former must be executed before the latter.

If you don't use the dockerized environment (Options 1 and 2) you have to take care on configure a postgres instance (or drop all the postgres properties and use H2 in memory)

### Option 1 - download and run the jar files
* Download the [web](http://80.241.213.140/taskmanager/endpoints-0.1-SNAPSHOT.jar) and the [scheduler](http://80.241.213.140/taskmanager/scheduler-0.1-SNAPSHOT.jar) jar files
* Create the database as described in *Database configuration*
* place them in a convinient location and the run ``java -jar`` first web then scheduler module.

### Option 2 - run from eclipse
* Create the database as described in *Database configuration*
* Run the classes ``EndpointsMain`` and ``SchedulerMain``. It can be done eclipse after having imported the project or directly from the command line.

### [WIP3]OPTION 3 - run via dockercompose

*The coolest option but it still doesn't work :(*

In the repo root run ``#$ sudo docker-compose up``

Open the browser at http://localhost:8080 and have fun!

![preview](/doc/preview.jpg?raw=true)


