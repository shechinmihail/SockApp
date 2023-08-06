FROM openjdk:17-alpine
ADD /target/TestTask-0.0.1-SNAPSHOT.jar backendTask.jar
ENTRYPOINT ["java", "-jar", "backendTask.jar"]