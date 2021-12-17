FROM openjdk:8-jdk-alpine
COPY target/cart-service-10.0.1.jar cart-service.jar
#ENTRYPOINT ["java","-jar","/cart-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]