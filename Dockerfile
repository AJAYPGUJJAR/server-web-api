FROM eclipse-temurin:21-jdk-jammy

# 1. Install Docker CLI
RUN apt-get update && apt-get install -y docker.io && rm -rf /var/lib/apt/lists/*
WORKDIR /app

# 2. Copy your Spring Boot fat JAR
COPY build/server-web-api-0.0.1-SNAPSHOT.jar app.jar

# 3. Launch
ENTRYPOINT ["java","-jar","/app/app.jar"]