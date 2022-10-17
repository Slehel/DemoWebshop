FROM maven:3.8.4-openjdk-17
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/webshop-0.0.1-SNAPSHOT.jar ./webshop-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "webshop-0.0.1-SNAPSHOT.jar"]

