# TodoManager

Простой REST-сервис для управления задачами.

Возможности:
- Просмотр списка задач на сегодня/неделю/месяц с фильтрацией по выполнению
- Создание задачи
- Изменение задачи
- Установление/снятие метки выполнения
- Удаление задачи

## Фреймворки

- Spring Web MVC
- Spring Security
- Spring Data JPA (Jakarta persistence + Hibernate + H2)

## Сборка

Используя ```mvnw.cmd``` или ```mvnw```:

Windows:

```ps1
./mvnw.cmd compile
```

Другие операционные системы:

```sh
$ ./mvnw compile
```

## Запуск

Windows:

```ps1
./mvnw.cmd spring-boot:run
```

Другие операционные системы:

```sh
$ ./mvnw spring-boot:run
```
