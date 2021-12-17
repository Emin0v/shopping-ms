FROM openjdk:8-jdk-alpine
COPY target/hystrix-dashboard-1.0.jar hystrix-service.jar
#ENTRYPOINT ["java","-jar","/hystrix-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]