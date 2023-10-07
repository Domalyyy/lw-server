FROM openjdk:21

COPY target/lw-server*.jar /lw-server.jar

EXPOSE 8080

ENTRYPOINT java -Dspring.profiles.active=$ACTIVE_PROFILES -jar /lw-server.jar
