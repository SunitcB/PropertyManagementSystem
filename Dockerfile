FROM openjdk:17
EXPOSE 8090
ADD target/srna-api.jar srna-api.jar
ENTRYPOINT ["java","-jar","/srna-api.jar"]