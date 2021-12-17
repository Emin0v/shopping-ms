FROM openjdk:8-jdk-alpine
COPY target/gateway-service-1.0.jar gateway-service.jar
#ENTRYPOINT ["java","-jar","/cart-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]