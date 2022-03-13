FROM openjdk:8u181-jre-alpine
# add bash, sudo, openssh, openssh-server and openrc, which is used to start services
RUN apk add bash \
  && apk add sudo \
  && apk add --no-cache openssh-server openssh openrc \
  && rm -rf /tmp/* /var/cache/apk/*

# Remove old ssh keys
RUN rm -rf /etc/ssh/ssh_host_rsa_key /etc/ssh/ssh_host_dsa_key

# Add ssh host keys
RUN /usr/bin/ssh-keygen -A

# Add the user peter
RUN addgroup -S devs && adduser -S peter -G devs -s /bin/bash
RUN echo 'peter:reallystrongpassword' | chpasswd
RUN echo '%devs ALL=(ALL) ALL' > /etc/sudoers.d/devs
RUN rc-update add sshd
RUN mkdir -p /var/run/sshd

WORKDIR /home/peter
# for log4j env stealing
RUN su peter -c "export PASSWD=reallystrongpassword"

# get the application and run it
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080 22
CMD exec /usr/sbin/sshd -D & su peter -c 'java -jar /home/peter/app.jar'