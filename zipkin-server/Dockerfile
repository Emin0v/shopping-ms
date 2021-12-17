FROM openjdk:8-jdk-alpine
COPY target/zipkin-server-2.0.7.RELEASE.jar zipkin-service.jar
#ENTRYPOINT ["java","-jar","/zipkin-service.jar"]
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]