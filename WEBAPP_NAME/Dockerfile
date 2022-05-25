FROM openjdk:8u181-jre-alpine
# add bash, sudo, openssh, openssh-server and openrc, which is used to start services
RUN apk add bash \
  && apk add sudo  \
  && apk add python3 && ln -sf python3 /usr/bin/python \ 
  && apk add --no-cache openssh openssh-server openrc \
  && rm -rf /tmp/* /var/cache/apk/*

# Remove old ssh keys
RUN rm -rf /etc/ssh/ssh_host_rsa_key /etc/ssh/ssh_host_dsa_key

# Add ssh host keys
RUN /usr/bin/ssh-keygen -A

# add the user peter
RUN addgroup -S devs && adduser -S peter -G devs -s /bin/bash
RUN echo 'peter:strongpassword' | chpasswd
COPY special_log_reader.py home/peter/special_log_reader.py

RUN echo '%devs ALL=(ALL) NOPASSWD: /usr/bin/python3 /home/peter/special_log_reader.py' > /etc/sudoers.d/devs
RUN rc-update add sshd
RUN mkdir -p /var/run/sshd

WORKDIR /home/peter

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080 22

# start ssh server and the java webapp
CMD exec /usr/sbin/sshd -D & su peter -c 'java -jar /home/peter/app.jar'
ENV PASSWD=strongpassword