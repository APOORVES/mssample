FROM openjdk:8u151-jre-alpine
RUN cd /
RUN mkdir repos
RUN mkdir /repos/logs
EXPOSE 5002
COPY cartms-0.0.1-SNAPSHOT.jar .
CMD java -jar /cartms-0.0.1-SNAPSHOT.jar
