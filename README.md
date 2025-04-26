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
- PostgreSQL (or docker-compose)
- Docker(optional)

### 2. PostgreSQL Settings

Application needs these db information:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/repsydb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

You can modify these changes on application.properties.

## 🆙 API Usage

### Uploading packages

```POST /{packageName}/{version}
    Content-Type: multipart/form-data
```

#### Example

```curl -X POST http://localhost:8080/mypackage/1.0.0 \
    -F "package.rep=@test.rep" \
    -F "meta.json=@meta.json"
```

### Downloading packages

` GET /{packageName}/{version}/{fileName}`

#### Example

` CURL -X GET http://localhost:8080/mypackage/1.0.0/package.rep -o out.rep`

## Storage Strategy
