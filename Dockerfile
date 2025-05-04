FROM bellsoft/liberica-openjdk-debian:24
WORKDIR /app
COPY ./target/BankCardManagement-0.0.1-SNAPSHOT.jar /app
EXPOSE 8765:8765
ENTRYPOINT ["java","-jar","BankCardManagement-0.0.1-SNAPSHOT.jar"]

