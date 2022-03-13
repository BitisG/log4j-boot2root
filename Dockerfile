FROM openjdk:8u181-jre-alpine

#TODO: Fix db


RUN apk add bash \
  && apk add sudo 
RUN addgroup -S peter && adduser -S peter -G peter -s /bin/bash
USER peter
WORKDIR /home/peter
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]