FROM openjdk:8-jdk-alpine
COPY target/eureka-service-1.0.jar eureka-service.jar
#ENTRYPOINT ["java","-jar","/eureka-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]