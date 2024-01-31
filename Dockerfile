FROM maven:3.8.7-openjdk-18-slim
COPY target/delivery-fee-calculator-1.0.0-SNAPSHOT.jar delivery-fee-app.jar
ENTRYPOINT ["java","-jar","/delivery-fee-app.jar"]