FROM openjdk:11

COPY target/server-0.0.*.0.SERVER.jar /server.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --retries=5 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

CMD java $JAVA_OPTS $ARGS -Dspring.profiles.active=$ACTIVE_PROFILES \
                                  -jar /app.jar
