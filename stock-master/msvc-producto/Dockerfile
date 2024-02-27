FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-producto

COPY ./pom.xml /app
COPY ./msvc-producto/.mvn ./.mvn
COPY ./msvc-producto/mvnw .
COPY ./msvc-producto/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-producto/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder ./app/msvc-producto/target/msvc-producto-0.0.1-SNAPSHOT.jar .

EXPOSE 8003

CMD ["java","-jar","msvc-producto-0.0.1-SNAPSHOT.jar"]


