FROM openjdk:11-jdk-buster
#FROM registry.redhat.io/fuse7/fuse-java-openshift-rhel8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ls -lah
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar" , "--spring.config.location=file:///etc/config/application.properties"]
CMD ["-start"]