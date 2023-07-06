#FROM openjdk:11-jdk-buster
#FROM registry.redhat.io/fuse7/fuse-java-openshift-rhel8
FROM registry.redhat.io/fuse7/fuse7-java-openshift
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#CMD ls -lah
#ENTRYPOINT ["tail", "-f", "/dev/null"]
ENTRYPOINT ["java","-jar","/home/jboss/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar" , "--spring.config.location=file:///etc/config/application.properties"]
#CMD ["-start"]