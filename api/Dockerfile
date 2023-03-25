FROM debian
RUN mkdir /app

WORKDIR /app

ADD ./web/target/web-1.2-SNAPSHOT.jar /app
COPY insert /app

EXPOSE 8080

CMD ["java", "-jar", "web-1.2-SNAPSHOT.jar"]