FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# SSL FIX
RUN apt-get update -o Acquire::Retries=5 && \
    apt-get install -y --no-install-recommends \
    ca-certificates curl openssl && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_TOOL_OPTIONS="-Dhttps.protocols=TLSv1.2,TLSv1.3"

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN chmod +x gradlew

# проверка Gradle + Java
RUN ./gradlew --version

# зависимости (лучше через cache-friendly слой)
RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew test --no-daemon
