FROM bellsoft/liberica-openjdk-debian:23
WORKDIR /app
COPY ./target/BankCardManagement-0.0.1-SNAPSHOT.jar /app
EXPOSE 8765:8765
ENTRYPOINT ["java","-jar","BankCardManagement-0.0.1-SNAPSHOT.jar"]

