FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY ./target/*.war librairie-boot.war

CMD [ "java", "-jar", "librairie-boot.jar" ]


EXPOSE 8080