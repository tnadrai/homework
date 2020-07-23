#!/bin/bash

DEBUGSTRING=""

if [ $DEBUG = true ]
then
    DEBUGSTRING="-agentlib:jdwp=transport=dt_socket,server=y,address=5005,suspend=n"
fi

java $DEBUGSTRING \
-Djava.security.egd=file:/dev/./urandom \
-Djava.io.tmpdir=/tmp/homework \
-Dserver.port=1980 \
-jar /usr/libexec/homework/application.jar \

