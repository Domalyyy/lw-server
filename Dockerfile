FROM openjdk:11

COPY target/lw-server*.jar /lw-server.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --retries=5 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

CMD java $JAVA_OPTS $ARGS -Dspring.profiles.active=$ACTIVE_PROFILES \
                                  -jar /lw-server.jar
