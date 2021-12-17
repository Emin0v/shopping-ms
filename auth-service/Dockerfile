FROM openjdk:8-jdk-alpine
COPY target/auth-service-10.0.1.jar auth-service.jar
#ENTRYPOINT ["java","-jar","/auth-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]