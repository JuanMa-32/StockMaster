FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-usuario

COPY ./pom.xml /app
COPY ./msvc-usuario/.mvn ./.mvn
COPY ./msvc-usuario/mvnw .
COPY ./msvc-usuario/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-usuario/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-usuario/target/msvc-usuario-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD ["java","-jar","msvc-usuario-0.0.1-SNAPSHOT.jar"]
