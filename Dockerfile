
FROM openjdk:17-jdk-alpine as builder
RUN mkdir -p /app/source
COPY . /app/source

WORKDIR /app/source
RUN chmod +x mvnw
RUN ./mvnw clean package

FROM openjdk:17-jdk-alpine as runtime
COPY --from=builder /app/source/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app/app.jar"]