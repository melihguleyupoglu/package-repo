# Repsy Package Repository API

Spring Boot based REST API implementation. Developed for management of ".rep" formatted files. Achieves upload and download functionality.

## 🚀 Features

- ✅ RESTful API (Spring Boot)
- ✅ Upload packages: ".rep" + "meta.json" files
- ✅ Download packages
- ✅ Using PostgreSQL for storage
- ✅ Utilized strategy pattern for 2 different storage mechanism.
  - File System
  - Object Storage (MinIO)
- ✅ Dockerized structure
- ✅ Global error handling

## ⚙️ Setup

### 1. Requirements

- Java 17
- Maven
- Docker and Docker Compose

### 2. Docker Usage

You can start the whole system with a single command.

```bash
./mvnw clean package -DskipTests
docker compose up --build
```

Docker Compose will start these services:

- PostgreSQL (5432)
- MinIO (9000-API, 9001-Console UI)
- Spring Boot App (8080)

#### MinIO UI User Settings

- Address: http://localhost:9001
- User: minioadmin
- Password: minioadmin

## 🆙 API Usage

### Uploading packages

```
POST /{packageName}/{version}
Content-Type: multipart/form-data
```

#### Example

```
curl -X POST http://localhost:8080/mypackage/1.0.0 \
  -F "package.rep=@test.rep" \
  -F "meta.json=@meta.json"
```

### Downloading packages

` GET /{packageName}/{version}/{fileName}`

#### Example

` CURL -X GET http://localhost:8080/mypackage/1.0.0/package.rep -o out.rep`

## Storage Strategy Selection

There are two main stoage management strategy used in this project:

- file-system: Files will be stored in a local storage.
- object-storage: Files will be stored in MinIO server.

Storage stategy selection can be updated via command line like below:

```bash
STORAGE_STRATEGY=file-system ./mvnw clean package -DskipTests
```

You can change the strategy while building the app. Or you can manually change it via docker-compose.yml but it is not recommended way to do it.

```docker-compose.yml
environment:
  STORAGE_STRATEGY: object-storage
```
