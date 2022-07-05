FROM openjdk:11
RUN mkdir /target
COPY ./target/* /target/
ENTRYPOINT ["java","-jar","/target/api_bitso-0.0.1-SNAPSHOT.jar"]
