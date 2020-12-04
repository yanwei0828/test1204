FROM 172.23.0.14/public/java:openjdk-8u111

COPY target/service-workorder.jar /usr/src/myapp/service-workorder.jar

RUN wget "http://172.25.160.115:8080/test/bonree.zip" -O bonree.zip

RUN unzip bonree.zip -d /opt/agent/

WORKDIR /usr/src/myapp/

EXPOSE 9026

ENTRYPOINT java -javaagent:/opt/agent/bonree-v5.3.104147/bonree.jar -Dbonree.appName=app-service -Dbonree.componentName=service-workorder  -jar service-workorder.jar

