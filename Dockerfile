FROM openjdk:17
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/chrifacile?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=445661tva++6
ADD target/chrifacile-0.0.1-SNAPSHOT.jar chrifacile.jar
ENTRYPOINT ["java", "-jar", "/chrifacile.jar"]