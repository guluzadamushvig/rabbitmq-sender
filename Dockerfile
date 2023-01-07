FROM openjdk:17-jdk-slim
RUN apk add --no-cache openjdk17
COPY build/libs/rabbitsender-0.0.1-SNAPSHOT.jar /app/
CMD java -jar /app/rabbitsender-0.0.1-SNAPSHOT.jar