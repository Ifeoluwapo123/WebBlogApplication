FROM openjdk:11
COPY target/WebBlog-0.0.1-SNAPSHOT.jar app.jar
RUN mvn clean install

ENTRYPOINT ["java","-jar", "app.jar"]

