FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# SSL FIX (ВАЖНО)
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk ca-certificates curl openssl && \
    update-ca-certificates

ENV JAVA_TOOL_OPTIONS="-Dhttps.protocols=TLSv1.2,TLSv1.3"
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN chmod +x gradlew

# НЕ падаем на кешировании сразу — сначала только bootstrap
RUN ./gradlew --version

# теперь зависимости
RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew test --no-daemon