FROM openjdk:11
ADD build/libs/transfer-service-0.0.1-SNAPSHOT-all.jar transfer-service-0.0.1-SNAPSHOT-all.jar
CMD java -jar transfer-service-0.0.1-SNAPSHOT-all.jar
EXPOSE 4567
