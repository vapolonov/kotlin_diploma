FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# ❌ НИКАКИХ ../../

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN chmod +x gradlew

# кеш зависимостей
RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew playwrightInstall --no-daemon