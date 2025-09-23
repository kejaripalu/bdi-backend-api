## BDI Backend API Kejari Palu

Ini adalah Repository Bank Data Intelijen untuk mengelola data Intelijen Kejaksaan Negeri Palu.

### System Recruitment

* JDK 21 or newest
* PostgreSQL 17++
* Maven 3.9++

### Baca Juga

- [API Spesification](API-SPECS.md)
- [Sample Data SQL untuk data user](sample-data-user.md)

---

### Environment

```
DB_URL=jdbc:postgresql://localhost:5432/databasename
DB_USERNAME=username
DB_PASSWORD=password
ISSUER=www.timposulabs.com
ORIGIN_URL=http://localhost:4200
RANDOM_CODE=XRND8dD8M3KxQcH8vRfrICD3QyNUAwDARaNxDP0Na0jrQzDC7F
```

Keterangan:

* `DB_URL`: Url database
* `DB_USERNAME`: username dari database
* `DB_PASSWORD`: password dari database
* `ISSUER`: Issuer token JWT
* `ORIGIN_URL`: URL Client dari Frontend
* `RANDOM_CODE`: Random code for JWT

Ganti value dari masing-masing environment sesuai kebutuhan.

---

### Build to production

Build Springboot app to production using maven

* Build skip test

```
mvn clean install -DskipTests
```

or run use profile prod

```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

* Running jar file in `target` directory

```
java -jar -Dspring.profiles.active=prod XXX.jar
```
---

### Menggunakan Docker

Untuk menggunakan database Postgres mengunakan Docker berikut petunjuknya: 

#### 1. Membuat Volume

Membuat volume contoh dengan nama `bdi-volume`, agar data yang dibuat dalam database tidak hilang ketika container dihentikan/dihapus:

```
docker volume create bdi-volume
```

#### 2. Membuat Container

Membuat Container dengan nama `bdi-postgres` dan password `cLVc086Ey4` sekaligus menjalankan containernya:

```
docker container run -d --rm --name bdi-postgres -e POSTGRES_PASSWORD=cLVc086Ey4 -p 5432:5432 -v bdi-volume:/var/lib/postgresql/data postgres:17.4
```

#### 3. Buat database

Login CLI Postgres dan buat databasenya (sekali saja karena datanya akan tersimpan di docker volume):

```
docker exec -it -u postgres bdi-postgres psql
```
#### 4. Docker Compose

Jika ingin menggunakan docker compose, maka berikut konfigurasinya:

```yaml
services:
  bdi-postgres:
    container_name: bdi-postgres
    image: postgres:17.4
    environment:
      - POSTGRES_PASSWORD=cLVc086Ey4
    ports:
      - 5432:5432
    volumes:
      - bdi-volume:/var/lib/postgresql/data
volumes:
  bdi-volume:
    driver: local
```

> Baca Selengkapnya [https://hub.docker.com/_/postgres](https://hub.docker.com/_/postgres)