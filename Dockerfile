FROM openjdk:8-jdk-alpine
COPY target/kubernetes-training-0.0.1-SNAPSHOT.jar kubernetes-training-0.0.1-RELEASE.jar
ENTRYPOINT ["java","-jar","/kubernetes-training-0.0.1-RELEASE.jar"]