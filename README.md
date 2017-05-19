# taskmanager
A spring4 + Angular exercise bulding a task management website


## Architecture

![architecture](/doc/taskmanager.jpg?raw=true)

you can have a look at a live drawing [here](https://docs.google.com/drawings/d/1Kst-gEPnU7SV6RhGqVKwuxKECHpmFvoV097tDaNgXAg).

## Requirements

* git
* JDK8
* Maven3
* Postgresql
* Docker

## Build

### Database configuration

The database connection params are stored in 

```
<root_repo>\endpoints\src\main\resources\application.properties

<root_repo>\tasks-manager\scheduler\src\main\resources\application.properties
```

and by default are configured for postgres, the web component is set to CREATE-DROP and the scheduler to VALIDATE. So the former must be executed before the latter.

If you don't use the dockerized environment you have to take care on configure a postgres instance (or drop all the postgres properties and use H2 in memory)

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

* **OPTION 1** Run the classes ``EndpointsMain`` and ``SchedulerMain``. It can be done eclipse after having imported the project or directly from the command line.

* **OPTION 2** in the repo root run ``#$ sudo docker-compose up``

Open the browser at http://localhost:8080 and have fun!

![preview](/doc/preview.jpg?raw=true)


