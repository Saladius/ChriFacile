FROM openjdk:17
EXPOSE 8080
ADD target/chrifacile-1.0-SNAPSHOT.jar chrifacile.jar
ENTRYPOINT [ "java", "-jar", "/chrifacile.jar" ]