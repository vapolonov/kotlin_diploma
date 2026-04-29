FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# Установка JDK 21 и SSL FIX
RUN apt-get update -o Acquire::Retries=5 && \
    apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    openssl \
    openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/*

# Настройка JAVA_HOME для JDK 21
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# SSL FIX для Java
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