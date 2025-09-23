# API Spesification

## Login

Request:
- Method : POST
- Endpoint : `/api/v1/login`
- Header :
  - Content-Type : application/json 
  - Accept : application/json
- Body :

```json
{
  "username" : "ucup", 
  "password" : "test123"
}
```

Example (using cURL):

```
curl -X POST "http://localhost:8888/api/v1/login" -d '{"username" : "ucup", "password" : "test123"}'
```

Response:

```json
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1Y3VwIiwic2NvcGVzIjpbXSwiaXNzIjoiaHR0cDovL3d3dy50aW1wb3N1bGFicy5jb20iLCJpYXQiOjE3NTIzODExNTUsImV4cCI6MTc1MjM4NDc1NX0.1Z51LwzbuDEnMC9Ylp7lxjyPz_qvk5plQ53Wue1wc_g"}
```

> Token nantinya akan digunakan untuk autentikasi di setiap request.

---

## Register Surat Masuk

### Create Register Surat Masuk

Request:

- Method : POST
- Endpoint : `/api/v1/surat-masuk`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
    "tanggalPenerimaanSurat": "date (2025-01-12)",
    "jamPenerimaanSurat": "time (11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (2025-01-10)",
    "perihal": "string",
    "jenisSurat": "enum",
    "isiDisposisi": "string",
    "tindakLanjutDisposisi": "string",
    "keterangan": "string",
    "urlFile": "string"
}
```

Response: 

```json
{
    "ids": "string",
    "tanggalPenerimaanSurat": "date (2025-01-12)",
    "jamPenerimaanSurat": "time (11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (2025-01-10)",
    "perihal": "string",
    "jenisSurat": "enum",
    "isiDisposisi": "string",
    "tindakLanjutDisposisi": "string",
    "keterangan": "string",
    "urlFile": "string"
}
```

### Update Register Surat Masuk

Request:

- Method : PUT
- Endpoint : `/api/v1/surat-masuk/{ids}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "tanggalPenerimaanSurat": "date (2025-01-12)",
  "jamPenerimaanSurat": "time (11:49)",
  "asal": "string",
  "nomorSurat": "string",
  "tanggalSurat": "date (2025-01-10)",
  "perihal": "string",
  "jenisSurat": "enum",
  "isiDisposisi": "string",
  "tindakLanjutDisposisi": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
    "ids": "string",
    "tanggalPenerimaanSurat": "date (2025-01-12)",
    "jamPenerimaanSurat": "time (11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (2025-01-10)",
    "perihal": "string",
    "jenisSurat": "enum",
    "isiDisposisi": "string",
    "tindakLanjutDisposisi": "string",
    "keterangan": "string",
    "urlFile": "string"
}
```

### Delete Register Surat Masuk

Request:

- Method : DELETE
- Endpoint : `/api/v1/surat-masuk/{ids}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Search Register Surat Masuk

Request:

- Method : GET
- Endpoint : `/api/v1/surat-masuk/search?pages={pages}&sizes={sizes}&startDate={startDate}&endDate={endDate}&value={value}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
        {
              "ids": "string",
              "tanggalPenerimaanSurat": "date (2025-01-12)",
              "jamPenerimaanSurat": "time (11:49)",
              "asal": "string",
              "nomorSurat": "string",
              "tanggalSurat": "date (2025-01-10)",
              "perihal": "string",
              "jenisSurat": "enum",
              "isiDisposisi": "string",
              "tindakLanjutDisposisi": "string",
              "keterangan": "string",
              "urlFile": "string"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

### Get One Register Surat Masuk

Request:

- Method : GET
- Endpoint : `/api/v1/surat-masuk/{ids}/detail`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "ids": "string",
    "tanggalPenerimaanSurat": "date (2025-01-12)",
    "jamPenerimaanSurat": "time (11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (2025-01-10)",
    "perihal": "string",
    "jenisSurat": "enum",
    "isiDisposisi": "string",
    "tindakLanjutDisposisi": "string",
    "keterangan": "string",
    "urlFile": "string"
}
```

### Get All Register Surat Masuk

Request:

- Method : GET
- Endpoint : `/api/v1/surat-masuk?pages={pages}&sizes={sizes}&jenisSurat={jenisSurat}&startDate={startDate}&endDate={endDate}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "tanggalPenerimaanSurat": "date (2025-01-12)",
      "jamPenerimaanSurat": "time (11:49)",
      "asal": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (2025-01-10)",
      "perihal": "string",
      "jenisSurat": "enum",
      "isiDisposisi": "string",
      "tindakLanjutDisposisi": "string",
      "keterangan": "string",
      "urlFile": "string"
    },
    {
      "ids": "string",
      "tanggalPenerimaanSurat": "date (2025-01-12)",
      "jamPenerimaanSurat": "time (11:49)",
      "asal": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (2025-01-10)",
      "perihal": "string",
      "jenisSurat": "enum",
      "isiDisposisi": "string",
      "tindakLanjutDisposisi": "string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```
