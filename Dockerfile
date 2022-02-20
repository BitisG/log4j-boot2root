FROM openjdk:8u181-jre-alpine
RUN addgroup -S peter && adduser -S peter -G peter -
USER peter
WORKDIR /home/peter
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]