#!/bin/bash
#
#SPRING_APPLICATION_JSON='{"spring.jpa.hibernate.ddl-auto":"create"}'

/usr/lib/postgresql/9.3/bin/postgres -D /var/lib/postgresql/9.3/main -c config_file=/etc/postgresql/9.3/main/postgresql.conf &
echo "waiting 20 seconds to allow postgres start..."
sleep 10
echo "...other 10 seconds..."
sleep 10
psql -c "ALTER USER postgres WITH PASSWORD 'postgres';"
java -jar /tmp/endpoints-0.1-SNAPSHOT.jar &
echo "waiting 30 seconds to allow the scheduler start..."
sleep 30
java -jar /tmp/scheduler-0.1-SNAPSHOT.jar
