FROM maven:3.8.4-openjdk-17
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/backend.jar ./backend.jar
EXPOSE 8080
CMD ["java", "-jar", "backend.jar"]

