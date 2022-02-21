FROM openjdk:11
COPY ./target /usr/test/HPEServiceApp
WORKDIR /usr/test/HPEServiceApp
EXPOSE 8080/tcp
CMD java -jar HPETEstService-0.0.1-SNAPSHOT.jar