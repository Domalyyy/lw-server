# Вступ

Даний мікросервіс (сервер) є частиною аплікації LinkedWay, яка була розроблена для дипломної роботи як сервіс для
полегшення процесу рекрутингу

# Використані технології

1. MySql 8.0
2. Spring Boot 2.4.4 (Java 11)

# Запуск проекту

## Використовуючи Docker - рекомендую використовувати саме цей спосіб

### Вимоги для запуску на Docker

1. Необхідно мати встановлений docker та docker-compose

#### Linux:

```bash
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
```  

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
```   

```bash
sudo apt-get update
```

```bash
sudo apt-get install docker-ce docker-ce-cli containerd.io
```  

```bash
sudo groupadd docker
```

```bash
sudo usermod -aG docker $USER
```  

```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```  

```bash
sudo chmod +x /usr/local/bin/docker-compose
```  

```bash
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
```  

#### Windows:

Перейти за [посиланням](https://docs.docker.com/docker-for-windows/install/) та звантажити клієнт

#### Запуск:

Після цього можна запускати всю інфраструктуру за допомогою `docker`, виконавши наступну команду в корені проєкті:

```bash
docker-compose -f docker-compose.yml up -d
```

## Використовуючи локальні сервіси

### Linux

1. Встановлення MySQL

```bash
sudo apt install mysql-server
```

```bash
sudo mysql_secure_installation
```

```bash
sudo mysql
```

```mysql
CREATE USER 'lwuser'@'%' IDENTIFIED WITH authentication_plugin BY 'lwpassword';
```

```mysql
GRANT PRIVILEGE ON database.table TO 'lwuser'@'%';
```

```mysql
FLUSH PRIVILEGES;
```

```bash
exit
```

2. Встановлення Java 11

```bash
sudo apt-get install openjdk-11-jdk
```

3. Встановлення Maven

```bash
sudo apt-get install maven
```

### Windows

1. Необхідно встановити MySQL, завантаживши клієнт за [посиланням](https://dev.mysql.com/downloads/installer/) та
   створити користувача з юзернеймом: `lwuser` та паролем `lwpassword`
2. Необхідно встановити Java 11:
    - завантажити можна за [посиланням](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
    - або ж якщо маєте IDE, то воно підвантажить автоматично
3. Необхідно встановити Maven:
    - завантажити можна за [посиланням](https://maven.apache.org/install.html)
    - або ж якщо маєте IDE, то воно підвантажить автоматично

## Запуск

### IDE

Просто клікаємо на `старт` у середовищі розробки

### Термінал

```bash
mvn clean install -DskipTests
```

```bash
cd target
```

```bash
java -Dspring.profiles.active=dev -jar /lw-server.jar
```
