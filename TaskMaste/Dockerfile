FROM amazoncorretto:17-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/TaskMaste-0.0.1-SNAPSHOT.jar todo.jar
ENTRYPOINT ["java","-jar","/todo.jar"]