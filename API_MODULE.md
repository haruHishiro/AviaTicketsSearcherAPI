
# API Module — AviaTicketsSearcher

**Модуль-ядро.** Этот сервис является центральным звеном системы. Он принимает запросы, управляет парсерами, хранит данные и отдает результаты.

---

## Назначение модуля

API-модуль решает следующие задачи:
1. **Прием запросов** от пользователей чере Telegram-бота (и любых других клиентов) подключенных через REST API (планируется сайт).
2. **Валидация** входных данных (города, даты, пассажиры).
3. **Координация** работы парсеров: отправка задач, сбор результатов, обработка ошибок.
4. **Хранение** истории запросов и результатов в PostgreSQL.

---

## Архитектура модуля (внутреннее устройство)

API построен по **многослойной архитектуре (Layered Architecture)**:

```

┌─────────────────────────────────────────────────────────────────┐
│                      CONTROLLER LAYER                          │
│  - Принимает HTTP-запросы (REST endpoints)                    │
│  - Валидирует входные DTO (@Valid)                            │
│  - Возвращает HTTP-ответы (статусы + DTO)                     │
│  - Обрабатывает исключения (@ControllerAdvice)                │
└───────────────────────────┬─────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────────┐
│                       SERVICE LAYER                            │
│  - Содержит бизнес-логику                                      │
│  - Координирует работу репозиториев и внешних клиентов        │
│  - Транзакционная обертка (@Transactional)                    │
│  - Преобразует Entity ↔ DTO (через MapStruct)                │
└───────────────────────────┬─────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────────┐
│                      REPOSITORY LAYER                          │
│  - Работа с БД через Spring Data JPA                         │
│  - Кастомные запросы (@Query)                                 │
│  - Пагинация и сортировка                                     │
└───────────────────────────┬─────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────────┐
│                    DATABASE (PostgreSQL)                       │
│  - Таблицы: users, search_requests, tickets, etc.            │
└─────────────────────────────────────────────────────────────────┘

```

---

## Структура пакетов (src/main/java)

```

com.aviasearcher/
├── controller/          # REST-контроллеры
│   ├── SearchController.java      # Эндпоинты поиска билетов
│   ├── CityController.java        # Справочник городов
│   └── HealthController.java      # Проверка статуса
│
├── service/             # Бизнес-логика
│   ├── SearchService.java         # Поиск и координация с парсерами
│   ├── CityService.java           # Работа со справочником городов
│   └── ParserClient.java          # HTTP-клиент для общения с Parser'ом
│
├── repository/          # Доступ к БД (Spring Data JPA)
│   ├── SearchRequestRepository.java
│   ├── CityRepository.java
│   └── TicketRepository.java
│
├── model/               # JPA-сущности (таблицы БД)
│   ├── SearchRequest.java
│   ├── City.java
│   └── Ticket.java
│
├── dto/                 # Объекты для передачи данных (запросы/ответы)
│   ├── SearchRequestDto.java
│   ├── SearchResponseDto.java
│   └── CityDto.java
│
├── mapper/              # MapStruct-мапперы (Entity ↔ DTO)
│   ├── SearchMapper.java
│   └── CityMapper.java
│
├── config/              # Конфигурации
│   ├── SwaggerConfig.java         # OpenAPI 3 (SpringDoc)
│   └── WebConfig.java             # CORS и настройки веба
│
└── exception/           # Кастомные исключения и обработчики
├── GlobalExceptionHandler.java
├── CityNotFoundException.java
└── SearchTimeoutException.java

```

---

## REST API (ключевые эндпоинты)

Все эндпоинты доступны после запуска по адресу: `http://localhost:8080/api/`

| Метод | URL | Описание | Пример запроса |
| :--- | :--- | :--- | :--- |
| **POST** | `/search` | Запуск поиска билетов | `{"from": "Moscow", "to": "London", "date": "2025-07-01"}` |
| **GET** | `/search/{id}` | Получение статуса поиска по ID | `GET /search/123` |
| **GET** | `/cities` | Поиск городов по стране | `GET /cities?country=Russia` |
| **GET** | `/health` | Проверка работоспособности | `GET /health` → `{"status": "UP"}` |

---

## Взаимодействие с другими модулями

### С Telegram Bot
- Бот присылает **POST** запрос на `/api/search` с DTO.
- API возвращает `searchId`, по которому бот может позже получить результат.

### С Parser'ом
- API отправляет задачу в Parser Core через **HTTP** (или через очередь, если реализовано).
- Parser после завершения присылает результат обратно на эндпоинт `/api/parser/callback`.

---

## Стек технологий (API модуль)

| Компонент | Технология | Версия |
| :--- | :--- | :--- |
| **Язык** | Java | 17 |
| **Фреймворк** | Spring Boot | 3.2.x |
| **ORM** | Spring Data JPA (Hibernate) | — |
| **БД** | PostgreSQL | 15+ |
| **Миграции** | Flyway (или Liquibase) | — |
| **Маппинг** | MapStruct | 1.5.x |
| **Валидация** | Hibernate Validator | — |
| **Документация** | SpringDoc OpenAPI | 2.3.x |
| **Сборка** | Maven | 3.9.x |
| **Тесты** | JUnit 5, Mockito, Testcontainers | — |

---

## Запуск и отладка

### Локальный запуск (без Docker)
```bash
# 1. Собери проект
./mvnw clean install

# 2. Запусти приложение
./mvnw spring-boot:run
```

Запуск с PostgreSQL в Docker (только БД)

```bash
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=pass postgres:15
```
---

## Переменные окружения (application.properties)

Ключевые настройки, которые нужно указать перед запуском:

```properties
# БД
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/aviasearcher}
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:pass}

# Parser URL
parser.base-url=${PARSER_URL:http://localhost:8081}

# Таймауты
parser.timeout=${PARSER_TIMEOUT:30000}
```

---

##  Статус модуля

· Состояние: Завершен (production-ready).
· Покрытие тестами: Проведено полное юнит тестирование.
· Релиз: v1.0.0