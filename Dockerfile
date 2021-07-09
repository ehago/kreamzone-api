FROM openjdk:11

EXPOSE 8080

COPY build/libs/*.jar /home/kreamzone.jar

ENTRYPOINT java -jar /home/kreamzone.jar
