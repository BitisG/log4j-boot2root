FROM openjdk:8u181-jre-alpine
# add bash, sudo, openssh, openssh-server and openrc, which is used to start services
RUN apk add bash \
  && apk add sudo  \
  && rm -rf /tmp/* /var/cache/apk/*


WORKDIR /home/

COPY MinecraftServer/ /home/
EXPOSE 25565

# start ssh server and the java webapp
CMD chmod -R a=rwx /home/ & java -Xmx1024M -Xms1024M -jar server.jar nogui