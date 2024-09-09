Тестовое задание на CaseLab Java

Технологический стек:
Микросервис реализован на Java 17 + Spring Boot.
Для хранения данных (и самого файла, и его атрибутов) используется СУБД PostgreSQL.

Т.к. я искал возможность научиться, а не писать код с ходу, мой
код основан на цикле статей [Spring Boot Restful Service](https://devmark.ru/article/spring-boot-resful-service).
Переработан из исходников представленных в статье.

Дополнительные задания решить не успел.

Разработка велась в VSCode. Запуск средствами VSCode.

Тестирование в curl.

Получение записей из БД.

Пример запроса на получение данных по id: 

curl http://localhost:8080/files/1

Пример ответа, если id найден: 

{"id":1,"title":"application.yml","description":"Application configuration file","creation_date":"09.09.2024 10:37:21","file":"YzNCeWFXNW5PZ29nSUdSaGRHRnpiM1Z5WTJVNkNpQWdJQ0JrY21sMlpYSXRZMnhoYzNNdGJtRnRaVG9nYjNKbkxuQnZjM1JuY21WemNXd3VSSEpwZG1WeUNpQWdJQ0IxY213NklHcGtZbU02Y0c5emRHZHlaWE54YkRvdkwyeHZZMkZzYUc5emREbzFORE15TDJacGJHVnpkRzl5WVdkbENpQWdJQ0IxYzJWeWJtRnRaVG9nWVdSdGFXNEtJQ0FnSUhCaGMzTjNiM0prT2lCaFpHMXBiZ289"}

Пример ответа, если id не найден: 

{"message":"File with id = 10 not found"}

Пример POST запроса:

curl -X POST -H "Content-Type: application/json" -d   @json/file.json http://localhost:8080/files

Содержимое файла file.json:

{"title":"application.yml","description":"Application configuration file","creation_date":"09.09.2024 10:37:21","file":"YzNCeWFXNW5PZ29nSUdSaGRHRnpiM1Z5WTJVNkNpQWdJQ0JrY21sMlpYSXRZMnhoYzNNdGJtRnRaVG9nYjNKbkxuQnZjM1JuY21WemNXd3VSSEpwZG1WeUNpQWdJQ0IxY213NklHcGtZbU02Y0c5emRHZHlaWE54YkRvdkwyeHZZMkZzYUc5emREbzFORE15TDJacGJHVnpkRzl5WVdkbENpQWdJQ0IxYzJWeWJtRnRaVG9nWVdSdGFXNEtJQ0FnSUhCaGMzTjNiM0prT2lCaFpHMXBiZ289"}

Пример ответа: {"id":8}
