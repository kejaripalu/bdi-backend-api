## BDI Backend API Kejari Palu

Ini adalah Repository Bank Data Intelijen untuk mengelola data Intelijen Kejaksaan Negeri Palu.

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