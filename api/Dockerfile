FROM openjdk:15
RUN mkdir /app

WORKDIR /app

ADD ./web/target/web-1.2-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "web-1.2-SNAPSHOT.jar"]