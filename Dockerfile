# (빌드용) JDK로 소스 빌드 → JAR 생성
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar --no-daemon
# build/libs/*.jar 생성

# (실행용) JRE만 담긴 슬림 이미지로 실행
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV PORT=8080
# App Runner가 PORT를 주입해도 대응
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
# 기본 프로필은 prod, 필요 시 실행 시 -e SPRING_PROFILES_ACTIVE=local로 덮어쓰기
ENTRYPOINT ["java","-Dserver.port=${PORT}","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:prod}","-jar","/app/app.jar"]