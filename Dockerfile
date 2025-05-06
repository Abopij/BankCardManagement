FROM bellsoft/liberica-openjdk-debian:22
WORKDIR /app
COPY ./target/bankcardmanagement-0.0.1-SNAPSHOT.jar /app
EXPOSE 8765:8765
ENTRYPOINT ["java","-jar","bankcardmanagement-0.0.1-SNAPSHOT.jar"]
