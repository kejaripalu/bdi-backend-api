## BDI Backend API Kejari Palu

Ini adalah Repository Bank Data Intelijen untuk mengelola data Intelijen Kejaksaan Negeri Palu.

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