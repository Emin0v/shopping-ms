FROM openjdk:8-jdk-alpine
COPY target/order-serice-1.0.jar order-service.jar
#ENTRYPOINT ["java","-jar","/order-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]