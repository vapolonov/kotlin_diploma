FROM mcr.microsoft.com/playwright/java:v1.59.0-jammy

WORKDIR /app

# --- Кэшируем зависимости ---
COPY ../../gradlew ./
COPY ../../gradle ./gradle
COPY ../../build.gradle settings.gradle ./

RUN chmod +x gradlew

# Скачиваем зависимости (без исходников)
RUN ./gradlew dependencies --no-daemon

# --- Копируем исходники ---
COPY ../../src ./src

# --- Устанавливаем браузеры Playwright ---
RUN ./gradlew playwrightInstall --no-daemon

# --- По умолчанию запускаем UI тесты ---
CMD ["./gradlew", "ui_test", "--no-daemon"]