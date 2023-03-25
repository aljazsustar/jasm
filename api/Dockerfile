FROM debian
RUN mkdir /app

WORKDIR /app

ADD ./web/target/web-1.2-SNAPSHOT.jar /app
RUN mkdir insert
COPY insert /app/insert

EXPOSE 8080

CMD ["java", "-jar", "web-1.2-SNAPSHOT.jar"]