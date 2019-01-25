# No Redirection
#JAVA_HOME=/root/DcBlock/install/java-se-8u40-ri
#nohup $JAVA_HOME/bin/java -jar /root/DcBlock/install/CaffeAPIServer-0.0.1.jar --spring.config.location=file:/root/DcBlock/application.yml 1>/dev/null 2>&1 &

# stdout, stderr
JAVA_HOME=/usr/bin
APP_PATH=/opt/dcadmin/DCAdminAPI-0.0.1.jar
CONFIG_PATH=/opt/dcadmin/application.properties

nohup $JAVA_HOME/java -jar $APP_PATH --spring.config.location=$CONFIG_PATH 1>/var/log/dcadmin/stdout.log 2>/var/log/dcadmin/stderr.log &
