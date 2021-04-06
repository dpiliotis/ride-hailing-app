Ride Hailing API.

It's a basic CRUD rest API.

It persists and retrieves data from the Database regarding user surveys.

By default it requires MySQL.

In order to test it without MySQL, follow the steps bellow:

RUN "mvn clean verify"

Then go to the target folder

RUN "java -jar -Dspring.profiles.active=test,dev ride-hailing-app-1.0-SNAPSHOT.jar"

RUN "curl localhost:8080/survey"