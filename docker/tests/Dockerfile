FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# Установка JDK 21 и SSL FIX
RUN apt-get update -o Acquire::Retries=5 && \
    apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    openssl \
    openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/* && \
    # Обновление сертификатов
    update-ca-certificates -f

# Настройка JAVA_HOME для JDK 21
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# SSL FIX для Java - добавим больше протоколов
ENV JAVA_TOOL_OPTIONS="-Dhttps.protocols=TLSv1.1,TLSv1.2,TLSv1.3 -Djava.security.egd=file:/dev/./urandom"

# Дополнительные опции Gradle
ENV GRADLE_OPTS="-Dorg.gradle.internal.http.socketTimeout=120000 -Dorg.gradle.internal.http.connectionTimeout=120000"

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN chmod +x gradlew

# проверка Gradle + Java
RUN ./gradlew --version

# Запуск с отключенным SSL проверкой (только для сборки, не для продакшена!)
RUN ./gradlew dependencies --no-daemon -Dhttp.socketTimeout=60000 -Dhttp.connectionTimeout=60000

COPY src ./src

# Запуск тестов
RUN ./gradlew test --no-daemon --no-build-cache --stacktrace