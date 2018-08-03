FROM ubuntu:16.04

RUN apt-get update && apt-get install -y wget 
RUN wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add -
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list
RUN apt-get update && apt-get install -y python-software-properties software-properties-common postgresql-9.3 postgresql-client-9.3 postgresql-contrib-9.3 default-jre-headless
USER postgres
RUN    /etc/init.d/postgresql start &&\
    createdb -O postgres s_ink
USER root
ADD endpoints-0.1-SNAPSHOT.jar /tmp/endpoints-0.1-SNAPSHOT.jar
ADD scheduler-0.1-SNAPSHOT.jar /tmp/scheduler-0.1-SNAPSHOT.jar
ADD launcher.sh /tmp/launcher.sh
RUN chmod +x /tmp/launcher.sh
USER postgres
EXPOSE 8080
ENTRYPOINT ["/tmp/launcher.sh"]

