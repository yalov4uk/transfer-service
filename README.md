# transfer-service

### Quick start
1. Build:
    * Unix 
    ```shell script
    ./gradlew -Dorg.gradle.java.home=/path/to/java/11 build
    ```
    * Windows
    ```shell script
    gradlew.bat -Dorg.gradle.java.home=/path/to/java/11 build
    ```
2. Run:
    * Java (4567 port):
    ```shell script
    path/to/java/11/bin/java -jar build/libs/transfer-service-0.0.1-SNAPSHOT-all.jar
    ```
    * Docker:
    ```shell script
    docker build -t transfer-service
    ```
    ```shell script
    docker run -p 8080:4567 transfer-service
    ```

### Implementation notes:
* The same data model is used in all layers for simplicity;
* JSR 303: Bean Validation should be considered instead of custom solution;
* In-memory data store is used for simplicity;
* Event consumer and producer applications are in the same process for simplicity;
* Balance reservation is omitted for simplicity
