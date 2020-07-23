ARG OPENJDK_VERSION=8
FROM openjdk:${OPENJDK_VERSION}

COPY ./target/application-0.0.1-SNAPSHOT.jar /usr/libexec/homework/application.jar
COPY docker /etc/homework

RUN chmod +x /etc/homework/startup.sh && mkdir --parent /tmp/homework

ENV DEBUG false
ENV CONSOLE_LOG_LEVEL INFO

EXPOSE 8080 5005

ENTRYPOINT ["/etc/homework/startup.sh"]
