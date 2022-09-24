FROM openjdk:18.0.2.1-jdk-slim-buster
EXPOSE 8080
ADD target/account-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=k8s","/app.jar"]
