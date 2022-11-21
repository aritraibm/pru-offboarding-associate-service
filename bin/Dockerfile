#cmd : docker build -t associate-service .
FROM openjdk:8
EXPOSE 9092
ADD target/associate-service.jar associate-service.jar
ENTRYPOINT ["java","-jar","/associate-service.jar"]