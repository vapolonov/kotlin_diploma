# Kotlin Diploma Test Framework 

Фреймворк для UI и API тестирования, построенный на современном технологическом стеке. Проект выполнен в рамках дипломной работы.
Репозиторий: https://github.com/vapolonov/kotlin_diploma

## Стек технологий

- **Язык:** Kotlin
- **Тестирование UI:** Playwright
- **Тест раннер:** JUnit 5
- **Сборка:** Gradle
- **API тестирование:** Retrofit
- **База данных:** Postgres
- **Отчетность:** Allure Reports

## Основные возможности

- 🎭 **Автоматизация браузера** через Playwright (поддержка Chromium, Firefox, WebKit)
- 🌐 **API тестирование** с помощью Retrofit + OkHttp
- 📊 **Генерация отчётов** с помощью Allure Reports
- 🧹 **Чистый код** с применением best practices Kotlin

## 📂 Структура проекта

```text
.
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   ├── resources/
│   └── test/
│       ├── kotlin/
│       ├── resources/
├── build.gradle
├── .gitignore
├── gradlew.bat
└── README.md
```

---

## 🚀 Запуск проекта

### 1. Клонирование репозитория

```bash
git clone https://github.com/vapolonov/kotlin_diploma.git
cd kotlin_diploma
```

### 2. Сборка проекта

```bash
./gradlew build
```

Для Windows:

```powershell
gradlew.bat build
```

---

## ⚙️ Конфигурация

Основные настройки находятся в файле:

```text
src/main/resources/env.properties
```

---

## 🧪 Тестирование

Запуск всех тестов:

```bash
./gradlew clean test
```

Запуск UI тестов:

```bash
./gradlew clean ui_test
```

Запуск API тестов:

```bash
./gradlew clean api_test
```

---

## 👨‍💻 Автор

Vasily Apolonov  
GitHub: https://github.com/vapolonov
