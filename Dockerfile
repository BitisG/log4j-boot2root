FROM openjdk:8-jdk-alpine
RUN addgroup -S peter && adduser -S peter -G peter -
USER peter
WORKDIR /home/peter
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]