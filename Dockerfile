FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# Установка JDK 21 (скачивание готового архива)
RUN apt-get update && apt-get install -y curl tar && \
    curl -L -o /tmp/jdk21.tar.gz https://api.adoptium.net/v3/binary/latest/21/ga/linux/x64/jdk/hotspot/normal/eclipse && \
    mkdir -p /opt/java && \
    tar -xzf /tmp/jdk21.tar.gz -C /opt/java && \
    rm /tmp/jdk21.tar.gz

# Найдём реальную папку (она с версией внутри)
RUN ln -s /opt/java/jdk-21* /opt/java/jdk-21

# Явно указываем Java 21 как основную
ENV JAVA_HOME=/opt/java/jdk-21
ENV PATH=$JAVA_HOME/bin:$PATH

# Копирование всего проекта
COPY . .

# Даём права на запуск Gradle Wrapper
RUN chmod +x gradlew

# Указываем путь к уже установленным браузерам
ENV PLAYWRIGHT_BROWSERS_PATH=/ms-playwright

# Запуск тестов через Wrapper
CMD ["sh", "-c", "xvfb-run -a ./gradlew test --no-daemon"]