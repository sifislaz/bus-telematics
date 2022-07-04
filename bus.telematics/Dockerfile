FROM adoptopenjdk/openjdk13-openj9:latest
MAINTAINER ece.upatras.gr
COPY deploy/bus.telematics-0.0.1-SNAPSHOT-exec.jar /opt/bus.telematics/
WORKDIR /opt/bus.telematics/
CMD ["java", "-jar", "/opt/bus.telematics/bus.telematics-0.0.1-SNAPSHOT-exec.jar"]
EXPOSE 8888
