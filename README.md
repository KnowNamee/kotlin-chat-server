# Чат-сервер
## Cборка и тестирование
- Требуется установка OpenJDK 11. Сделать это можно, например, введя в консоли :
```
  $ sudo apt install openjdk-11-jdk
```
- Cобираем проект, используя систему сборки `gradle`. Для этого просто достаточно ввести :
```
  $ ./gradlew build
```

Теперь в корневой папке проекта появился каталог `jar` с двумя файлами, серверной частью и клиентской соответственно.
Для запуска каждой из программ можно использовать следующие команды :
```
  $ java -jar jar/server-1.0.jar --port=8081
  $ java -jar jar/client-1.0.jar --ip=127.0.0.1 --port=8081 --username=Alice
  $ java -jar jar/client-1.0.jar --ip=127.0.0.1 --port=8081 --username=Bob
```
---
Система команд построена следующим образом. Любая команда начинается на `:`. Таким образом, если ввести в консоли
```
  $ java -jar jar/client-1.0.jar --ip=127.0.0.1 --port=8081 --username=Bob
  :quit
  $
```
то клиент отключиться от сервера. Также для смены имени следует использовать `:update <new name>`
