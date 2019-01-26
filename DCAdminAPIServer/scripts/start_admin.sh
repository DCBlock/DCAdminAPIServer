JAVA_HOME=/usr/bin
APP_PATH=/opt/dcadmin/DCAdminAPI-0.0.1.jar
CONFIG_PATH=/opt/dcadmin/application.properties

# pinpoint
PINPOINT=/opt/pinpoint-agent/pinpoint-bootstrap-1.8.1.jar
AGENT_ID=2
APPLICATION_NAME=DCAdminAPI

nohup $JAVA_HOME/java -javaagent:$PINPOINT -Dpinpoint.agentId=$AGENT_ID -Dpinpoint.applicationName=$APPLICATION_NAME -jar $APP_PATH --spring.config.location=$CONFIG_PATH 1>/var/log/dcadmin/stdout.log 2>/var/log/dcadmin/stderr.log &