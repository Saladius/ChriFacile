FROM openjdk:17
EXPOSE 8080
ADD target/chrifacile-0.0.1-SNAPSHOT.jar chrifacile.jar
ENTRYPOINT [ "java", "-jar", "/chrifacile.jar" ]