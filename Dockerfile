
#FROM openjdk:17-jdk-alpine as builder
#RUN mkdir -p /app/source
#COPY . /app/source
#
#WORKDIR /app/source
#RUN chmod +x mvnw
#RUN ./mvnw clean package
#
#FROM openjdk:17-jdk-alpine as runtime
#COPY --from=builder /app/source/target/*.jar /app/app.jar
#EXPOSE 8080
##ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app/app.jar"]
#ENTRYPOINT ["java","-jar","/app/app.jar"]


FROM openjdk:17-jdk-alpine AS build
COPY . .
#RUN mvn clean package -DskipTests

RUN chmod +x ./mvnw
RUN ./mvnw clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /target/TransportApp-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

