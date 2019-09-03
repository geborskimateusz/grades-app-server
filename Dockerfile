FROM openjdk:8
ADD target/grades.jar grades.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "grades.jar"]