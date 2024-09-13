FROM openjdk:17
EXPOSE 8080
ADD target/chrifacile.jar chrifacile.jar
ENTRYPOINT [ "java", "-jar","/chrifacile.jar" ]