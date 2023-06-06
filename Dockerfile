FROM openjdk:17
EXPOSE 8090
COPY target/*.jar srna-api.jar
ENTRYPOINT ["java","-jar","/srna-api.jar"]