#!/bin/bash

##########################################################################
# Default Variables
##########################################################################

JAVA_HOME=/usr/local/java11
HOME_DIR=/data/webapp
LOG_ROOT=${HOME_DIR}/logs

JAR_NAME=com.globalpsa.webapp-0.0.1.jar
APPLICATION_PROCESS_ID=com.globalpsa.webapp
MEM_ARGS="-Xms128m -Xmx128m"


##########################################################################
# Environment Variables
##########################################################################


##########################################################################
# FUNCTIONS
##########################################################################

start_application()
{
	${JAVA_HOME}/bin/java -D${APPLICATION_PROCESS_ID} ${MEM_ARGS} -jar ${JAR_NAME} $@ >> ${LOG_ROOT}/$0.log 2>&1 &
}

stop_application()
{
	kill -9 `ps -ef|grep -i ${APPLICATION_PROCESS_ID}|grep -v grep|awk {'print $2'}`
}


##########################################################################
# BODY
##########################################################################

if [ "$#" -ne 1 ]; then
	echo "Usage : $0 <start|stop>"
	exit 1
fi

if [ "$1" == "start" ]; then
	start_application
elif [ "$1" == "stop" ]; then
	stop_application
fi
