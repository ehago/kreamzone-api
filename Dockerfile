FROM openjdk:11.0.12-jre

EXPOSE 8080

VOLUME /tmp/log

COPY build/libs/*.jar /home/kreamzone.jar

ENTRYPOINT java -jar /home/kreamzone.jar
