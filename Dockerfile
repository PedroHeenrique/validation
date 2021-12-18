FROM openjdk:11
LABEL version="1.0.1" description="Validacao de model e tratamento de excessao"
MAINTAINER pedroheenri_q@hotmail.com
WORKDIR /app
EXPOSE 8082
COPY target/spring-validation-0.0.1-SNAPSHOT.jar /app/spring-validation.jar
ENTRYPOINT ["java","-jar","spring-validation.jar"]