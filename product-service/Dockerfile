FROM openjdk:8-jdk-alpine
COPY target/product-service-10.0.1.jar product-service.jar
#ENTRYPOINT ["java","-jar","/product-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]