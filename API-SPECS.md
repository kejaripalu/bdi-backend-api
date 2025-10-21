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

Mendapatkan info username:

Request:

- Method : GET
- Endpoint : `/api/v1/user`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "username": "string"
}
```

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
    "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
    "jamPenerimaanSurat": "time (ex. 11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-01-10)",
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
    "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
    "jamPenerimaanSurat": "time (ex. 11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-01-10)",
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
  "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
  "jamPenerimaanSurat": "time (ex. 11:49)",
  "asal": "string",
  "nomorSurat": "string",
  "tanggalSurat": "date (ex. 2025-01-10)",
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
    "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
    "jamPenerimaanSurat": "time (ex. 11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-01-10)",
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

### Get By Search Register Surat Masuk

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
              "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
              "jamPenerimaanSurat": "time (ex. 11:49)",
              "asal": "string",
              "nomorSurat": "string",
              "tanggalSurat": "date (ex. 2025-01-10)",
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

### Get By Id Register Surat Masuk

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
    "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
    "jamPenerimaanSurat": "time (ex. 11:49)",
    "asal": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-01-10)",
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
      "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
      "jamPenerimaanSurat": "time (ex. 11:49)",
      "asal": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (ex. 2025-01-10)",
      "perihal": "string",
      "jenisSurat": "enum",
      "isiDisposisi": "string",
      "tindakLanjutDisposisi": "string",
      "keterangan": "string",
      "urlFile": "string"
    },
    {
      "ids": "string",
      "tanggalPenerimaanSurat": "date (ex. 2025-01-12)",
      "jamPenerimaanSurat": "time (ex. 11:49)",
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
---

## Register Surat Keluar

### Create Register Surat Keluar

Request:

- Method : POST
- Endpoint : `/api/v1/surat-keluar`
- Header :
  - Content-Type : application/json
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "tanggalSurat": "date (ex. 2022-10-12)",
  "nomorSurat": "string",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "keterangan": "string",
  "jenisSurat": "enum"
}
```

Response:

```json
{
  "ids": "string",
  "tanggalSurat": "date (ex. 2022-10-12)",
  "nomorSurat": "string",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "keterangan": "string",
  "jenisSurat": "enum"
}
```

### Update Register Surat Keluar

Request:

- Method : PUT
- Endpoint : `/api/v1/surat-keluar/{id}`
- Header :
  - Content-Type : application/json
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "tanggalSurat": "date (ex. 2022-10-12)",
  "nomorSurat": "string",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "keterangan": "string",
  "jenisSurat": "enum"
}
```

Response:

```json
{
  "ids": "string",
  "tanggalSurat": "date (ex. 2022-10-12)",
  "nomorSurat": "string",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "keterangan": "string",
  "jenisSurat": "enum"
}
```

### Delete Register Surat Keluar

Request:

- Method : DELETE
- Endpoint : `/api/v1/surat-keluar/{ids}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Get By Search Register Surat Keluar

Request:

- Method : GET
- Endpoint : `/api/v1/surat-keluar/search?pages={pages}&sizes={sizes}&jenisSurat={jenisSurat}&startDate={startDate}&endDate={endDate}&value={value}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
      {
        "ids": "string",
        "tanggalSurat": "date (ex. 2022-10-12)",
        "nomorSurat": "string",
        "kepada": "string",
        "perihal": "string",
        "jenisSurat": "enum",
        "lampiran": "string",
        "keterangan": "string"
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

### Get By Id Register Surat Keluar

Request:

- Method : GET
- Endpoint : `/api/v1/surat-keluar/{id}/detail`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "ids": "string",
  "tanggalSurat": "date (ex. 2022-10-12)",
  "nomorSurat": "string",
  "kepada": "string",
  "perihal": "string",
  "jenisSurat": "enum",
  "lampiran": "string",
  "keterangan": "string"
}
```

### Get All Register Surat Keluar

Request:

- Method : GET
- Endpoint : `/api/v1/surat-keluar?pages={pages}&sizes={sizes}&jenisSurat={jenisSurat}&startDate={startDate}&endDate={endDate}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
        {
          "ids": "string",
          "tanggalSurat": "date (ex. 2022-10-12)",
          "nomorSurat": "string",
          "kepada": "string",
          "perihal": "string",
          "jenisSurat": "enum",
          "lampiran": "string",
          "keterangan": "string"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 1,
    "totalPages": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

---

## Register Arsip

### Create Register Arsip

Request:

- Method : POST
- Endpoint : `/api/v1/arsip`
- Header :
  - Content-Type : application/json
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
    "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
    "jamPenerimaanArsip": "time (ex. 11:49)",
    "diterimaDari": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-10-10)",
    "perihal": "string",
    "lampiran": "string",
    "kodePenyimpanan": "enum, string",
    "keterangan": "string",
    "urlFile": "string"
}
```

Response:

```json
{
    "ids": "string",
    "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
    "jamPenerimaanArsip": "time (ex. 11:49)",
    "diterimaDari": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-10-10)",
    "perihal": "string",
    "lampiran": "string",
    "kodePenyimpanan": "enum, string",
    "keterangan": "string",
    "urlFile": "string"
}
```

### Update Register Arsip

Request:

- Method : PUT
- Endpoint : `/api/v1/arsip/{id}`
- Header :
  - Content-Type : application/json
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
  "jamPenerimaanArsip": "time (ex. 11:49)",
  "diterimaDari": "string",
  "nomorSurat": "string",
  "tanggalSurat": "date (ex. 2025-10-10)",
  "perihal": "string",
  "lampiran": "string",
  "kodePenyimpanan": "enum, string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
    "ids": "string",
    "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
    "jamPenerimaanArsip": "time (ex. 11:49)",
    "diterimaDari": "string",
    "nomorSurat": "string",
    "tanggalSurat": "date (ex. 2025-10-10)",
    "perihal": "string",
    "lampiran": "string",
    "kodePenyimpanan": "enum, string",
    "keterangan": "string",
    "urlFile": "string"
}
```

### Delete Register Arsip

Request:

- Method : DELETE
- Endpoint : `/api/v1/arsip/{ids}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Get By Search Register Arsip

Request:

- Method : GET
- Endpoint : `/api/v1/arsip/search?pages={pages}&sizes={sizes}&startDate={starDate}&endDate={endDate}&value={value}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
        {
            "ids": "string",
            "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
            "jamPenerimaanArsip": "time (ex. 11:49)",
            "diterimaDari": "string",
            "nomorSurat": "string",
            "tanggalSurat": "date (ex. 2025-10-10)",
            "perihal": "string",
            "lampiran": "string",
            "kodePenyimpanan": "enum, string",
            "keterangan": "string",
            "urlFile": "string"
        }
    ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
      },
      "last": true,
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "first": true,
      "numberOfElements": 1,
      "empty": false
}
```

### Get By Id Register Arsip

Request:

- Method : GET
- Endpoint : `/api/v1/arsip/{id}/detail`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "ids": "string",
  "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
  "jamPenerimaanArsip": "time (ex. 11:49)",
  "diterimaDari": "string",
  "nomorSurat": "string",
  "tanggalSurat": "date (ex. 2025-10-10)",
  "perihal": "string",
  "lampiran": "string",
  "kodePenyimpanan": "enum, string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Get All Register Arsip

Request:

- Method : GET
- Endpoint : `/api/v1/arsip?pages={pages}&sizes={sizes}&startDate={startDate}&endDate={endDate}`
- Header :
  - Accept : application/json
  - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
        {
            "ids": "string",
            "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
            "jamPenerimaanArsip": "time (ex. 11:49)",
            "diterimaDari": "string",
            "nomorSurat": "string",
            "tanggalSurat": "date (ex. 2025-10-10)",
            "perihal": "string",
            "lampiran": "string",
            "kodePenyimpanan": "enum, string",
            "keterangan": "string",
            "urlFile": "string"
        },
        {
            "ids": "string",
            "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
            "jamPenerimaanArsip": "time (ex. 11:49)",
            "diterimaDari": "string",
            "nomorSurat": "string",
            "tanggalSurat": "date (ex. 2025-10-10)",
            "perihal": "string",
            "lampiran": "string",
            "kodePenyimpanan": "enum, string",
            "keterangan": "string",
            "urlFile": "string"
        },
        {
            "ids": "string",
            "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
            "jamPenerimaanArsip": "time (ex. 11:49)",
            "diterimaDari": "string",
            "nomorSurat": "string",
            "tanggalSurat": "date (ex. 2025-10-10)",
            "perihal": "string",
            "lampiran": "string",
            "kodePenyimpanan": "enum, string",
            "keterangan": "string",
            "urlFile": "string"
        },
        {
            "ids": "string",
            "tanggalPenerimaanArsip": "date (ex. 2025-10-12)",
            "jamPenerimaanArsip": "time (ex. 11:49)",
            "diterimaDari": "string",
            "nomorSurat": "string",
            "tanggalSurat": "date (ex. 2025-10-10)",
            "perihal": "string",
            "lampiran": "string",
            "kodePenyimpanan": "enum, string",
            "keterangan": "string",
            "urlFile": "string"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 4,
    "empty": false
}
```

---

## Register Ekspedisi

### Create Register Ekspedisi

Request:

- Method : POST
- Endpoint : `/api/v1/ekspedisi`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
      "nomorSurat": "string",
      "tanggalSurat": "date (ex. 2022-10-12)",
      "kepada": "string",
      "perihal": "string",
      "lampiran": "string",
      "tanggalTandaTerima": "date (ex. 2022-10-10)",
      "jamTandaTerima": "time (ex. 11:49)",
      "jenisSurat": "enum, string",
      "keterangan": "string",
      "urlFile": "string"
}
```

Response:

```json
{
      "ids": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (ex. 2022-10-12)",
      "kepada": "string",
      "perihal": "string",
      "lampiran": "string",
      "tanggalTandaTerima": "date (ex. 2022-10-10)",
      "jamTandaTerima": "time (ex. 11:49)",
      "jenisSurat": "enum, string",
      "keterangan": "string",
      "urlFile": "string"
}
```

### Update Register Ekspedisi

Request:

- Method : PUT
- Endpoint : `/api/v1/ekspedisi/{id}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "nomorSurat": "string",
  "tanggalSurat": "date (ex. 2022-10-12)",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "tanggalTandaTerima": "date (ex. 2022-10-10)",
  "jamTandaTerima": "time (ex. 11:49)",
  "jenisSurat": "enum, string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
      "ids": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (ex. 2022-10-12)",
      "kepada": "string",
      "perihal": "string",
      "lampiran": "string",
      "tanggalTandaTerima": "date (ex. 2022-10-10)",
      "jamTandaTerima": "time (ex. 11:49)",
      "jenisSurat": "enum, string",
      "keterangan": "string",
      "urlFile": "string"
}
```

### Delete Register Ekspedisi

Request:

- Method : DELETE
- Endpoint : `/api/v1/ekspedisi/{ids}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Get By Search Register Ekspedisi

Request:

- Method : GET
- Endpoint : `/api/v1/ekspedisi/search?pages={pages}&sizes={sizes}&jenisSurat={jenisSurat}&startDate={startDate}&endDate={endDate}&value={value}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
    "content": [
      {
        "ids": "string",
        "nomorSurat": "string",
        "tanggalSurat": "date (ex. 2022-10-12)",
        "kepada": "string",
        "perihal": "string",
        "lampiran": "string",
        "tanggalTandaTerima": "date (ex. 2022-10-10)",
        "jamTandaTerima": "time (ex. 11:49)",
        "jenisSurat": "enum, string",
        "keterangan": "string",
        "urlFile": "string"
      }
    ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
      },
      "last": true,
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "first": true,
      "numberOfElements": 1,
      "empty": false
}
```

### Get By Id Register Ekspedisi

Request:

- Method : GET
- Endpoint : `/api/v1/ekspedisi/{id}/detail`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "ids": "string",
  "nomorSurat": "string",
  "tanggalSurat": "date (ex. 2022-10-12)",
  "kepada": "string",
  "perihal": "string",
  "lampiran": "string",
  "tanggalTandaTerima": "date (ex. 2022-10-10)",
  "jamTandaTerima": "time (ex. 11:49)",
  "jenisSurat": "enum, string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Get All Register Ekspedisi

Request:

- Method : GET
- Endpoint : `/api/v1/ekspedisi?pages={pages}&sizes={sizes}&jenisSurat={jenisSurat}&startDate={startDate}&endDate={endDate}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "nomorSurat": "string",
      "tanggalSurat": "date (ex. 2022-10-12)",
      "kepada": "string",
      "perihal": "string",
      "lampiran": "string",
      "tanggalTandaTerima": "date (ex. 2022-10-10)",
      "jamTandaTerima": "time (ex. 11:49)",
      "jenisSurat": "enum, string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "empty": false
}
```

---

## Register Kegiatan Intelijen

### Create Register Kegiatan Intelijen

Request:

- Method : POST
- Endpoint : `/api/v1/kegiatan`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "bidangDirektorat": "string",
  "sektor": "string",
  "nomor": "string",
  "tanggal": "date (yyyy-mm-dd)",
  "perihal": "string",
  "namaPetugasPelaksana": "string",
  "hasilPelaksanaanKegiatan": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
      "ids": "string",
      "bidangDirektorat": "string",
      "sektor": "string",
      "nomor": "string",
      "tanggal": "date (yyyy-mm-dd)",
      "perihal": "string",
      "namaPetugasPelaksana": "string",
      "hasilPelaksanaanKegiatan": "string",
      "keterangan": "string",
      "urlFile": "string"
}
```

### Update Register Kegiatan Intelijen

Request:

- Method : PUT
- Endpoint : `/api/v1/kegiatan/{id}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "bidangDirektorat": "string",
  "sektor": "string",
  "nomor": "string",
  "tanggal": "date (yyyy-mm-dd)",
  "perihal": "string",
  "namaPetugasPelaksana": "string",
  "hasilPelaksanaanKegiatan": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
  "ids": "string",
  "bidangDirektorat": "string",
  "sektor": "string",
  "nomor": "string",
  "tanggal": "date (yyyy-mm-dd)",
  "perihal": "string",
  "namaPetugasPelaksana": "string",
  "hasilPelaksanaanKegiatan": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Delete Register Kegiatan Intelijen

Request:

- Method : DELETE
- Endpoint : `/api/v1/kegiatan/{ids}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Get By Search Register Kegiatan Intelijen

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan/search?pages={pages}&sizes={sizes}&bidangDirektorat={bidangDirektorat}&startDate={startDate}&endDate={endDate}&value={value}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "bidangDirektorat": "string",
      "sektor": "string",
      "nomor": "string",
      "tanggal": "date (yyyy-mm-dd)",
      "perihal": "string",
      "namaPetugasPelaksana": "string",
      "hasilPelaksanaanKegiatan": "string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": true
}
```

### Get By Id Register Kegiatan Intelijen

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan/{id}/detail`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "ids": "string",
  "bidangDirektorat": "string",
  "sektor": "string",
  "nomor": "string",
  "tanggal": "date (yyyy-mm-dd)",
  "perihal": "string",
  "namaPetugasPelaksana": "string",
  "hasilPelaksanaanKegiatan": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Get All Register Kegiatan Intelijen

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan?pages={pages}&sizes={sizes}&startDate={starDate}&endDate={endDate}&bidangDirektorat={bidangDirektorat}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "bidangDirektorat": "string",
      "sektor": "string",
      "nomor": "string",
      "tanggal": "date (yyyy-mm-dd)",
      "perihal": "string",
      "namaPetugasPelaksana": "string",
      "hasilPelaksanaanKegiatan": "string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": true
}
```

---

## Register Kegiatan Intelijen Pamstra

### Create Register Kegiatan Intelijen Pamstra

Request:

- Method : POST
- Endpoint : `/api/v1/kegiatan-pamstra`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "sektor": "string",
  "namaKegiatan": "string",
  "sumberDana": "string",
  "instansi": "string",
  "paguAnggaran": "number",
  "nomorSuratPermohonan": "string",
  "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
  "tempatPemaparan": "string",
  "tanggalPemaparan": "date (yyyy-mm-dd)",
  "telaahanIntelijen": "string",
  "tindakLanjut": "string",
  "tindakLanjutKeterangan": "string",
  "nomorSprintWalpam": "string",
  "tanggalSprintWalpam": "date (yyyy-mm-dd)",
  "namaPetugasPelaksana": "string",
  "nilaiKontrak": "number",
  "hasilPelaksanaan": "string",
  "hasilPelaksanaanKeterangan": "string",
  "nomorKertasKerja": "string",
  "tanggalKertasKerja": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
  "ids": "string",
  "sektor": "string",
  "namaKegiatan": "string",
  "sumberDana": "string",
  "instansi": "string",
  "paguAnggaran": "number",
  "nomorSuratPermohonan": "string",
  "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
  "tempatPemaparan": "string",
  "tanggalPemaparan": "date (yyyy-mm-dd)",
  "telaahanIntelijen": "string",
  "tindakLanjut": "string",
  "tindakLanjutKeterangan": "string",
  "nomorSprintWalpam": "string",
  "tanggalSprintWalpam": "date (yyyy-mm-dd)",
  "namaPetugasPelaksana": "string",
  "nilaiKontrak": "number",
  "hasilPelaksanaan": "string",
  "hasilPelaksanaanKeterangan": "string",
  "nomorKertasKerja": "string",
  "tanggalKertasKerja": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Update Register Kegiatan Intelijen Pamstra

Request:

- Method : PUT
- Endpoint : `/api/v1/kegiatan-pamstra/{id}`
- Header :
    - Content-Type : application/json
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`
- Body :

```json
{
  "sektor": "string",
  "namaKegiatan": "string",
  "sumberDana": "string",
  "instansi": "string",
  "paguAnggaran": "number",
  "nomorSuratPermohonan": "string",
  "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
  "tempatPemaparan": "string",
  "tanggalPemaparan": "date (yyyy-mm-dd)",
  "telaahanIntelijen": "string",
  "tindakLanjut": "string",
  "tindakLanjutKeterangan": "string",
  "nomorSprintWalpam": "string",
  "tanggalSprintWalpam": "date (yyyy-mm-dd)",
  "namaPetugasPelaksana": "string",
  "nilaiKontrak": "number",
  "hasilPelaksanaan": "string",
  "hasilPelaksanaanKeterangan": "string",
  "nomorKertasKerja": "string",
  "tanggalKertasKerja": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

Response:

```json
{
  "ids": "string",
  "sektor": "string",
  "namaKegiatan": "string",
  "sumberDana": "string",
  "instansi": "string",
  "paguAnggaran": "number",
  "nomorSuratPermohonan": "string",
  "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
  "tempatPemaparan": "string",
  "tanggalPemaparan": "date (yyyy-mm-dd)",
  "telaahanIntelijen": "string",
  "tindakLanjut": "string",
  "tindakLanjutKeterangan": "string",
  "nomorSprintWalpam": "string",
  "tanggalSprintWalpam": "date (yyyy-mm-dd)",
  "namaPetugasPelaksana": "string",
  "nilaiKontrak": "number",
  "hasilPelaksanaan": "string",
  "hasilPelaksanaanKeterangan": "string",
  "nomorKertasKerja": "string",
  "tanggalKertasKerja": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Delete Register Kegiatan Intelijen Pamstra

Request:

- Method : DELETE
- Endpoint : `/api/v1/kegiatan-pamstra/{ids}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

### Get By Search Register Kegiatan Intelijen Pamstra

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan-pamstra/search?pages={pages}&sizes={sizes}&startDate={startDate}&endDate={endDate}&value={value}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "sektor": "string",
      "namaKegiatan": "string",
      "sumberDana": "string",
      "instansi": "string",
      "paguAnggaran": "number",
      "nomorSuratPermohonan": "string",
      "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
      "tempatPemaparan": "string",
      "tanggalPemaparan": "date (yyyy-mm-dd)",
      "telaahanIntelijen": "string",
      "tindakLanjut": "string",
      "tindakLanjutKeterangan": "string",
      "nomorSprintWalpam": "string",
      "tanggalSprintWalpam": "date (yyyy-mm-dd)",
      "namaPetugasPelaksana": "string",
      "nilaiKontrak": "number",
      "hasilPelaksanaan": "string",
      "hasilPelaksanaanKeterangan": "string",
      "nomorKertasKerja": "string",
      "tanggalKertasKerja": "string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```

### Get By Id Register Kegiatan Intelijen

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan-pamstra/{id}/detail`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "ids": "string",
  "sektor": "string",
  "namaKegiatan": "string",
  "sumberDana": "string",
  "instansi": "string",
  "paguAnggaran": "number",
  "nomorSuratPermohonan": "string",
  "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
  "tempatPemaparan": "string",
  "tanggalPemaparan": "date (yyyy-mm-dd)",
  "telaahanIntelijen": "string",
  "tindakLanjut": "string",
  "tindakLanjutKeterangan": "string",
  "nomorSprintWalpam": "string",
  "tanggalSprintWalpam": "date (yyyy-mm-dd)",
  "namaPetugasPelaksana": "string",
  "nilaiKontrak": "number",
  "hasilPelaksanaan": "string",
  "hasilPelaksanaanKeterangan": "string",
  "nomorKertasKerja": "string",
  "tanggalKertasKerja": "string",
  "keterangan": "string",
  "urlFile": "string"
}
```

### Get All Register Kegiatan Intelijen Pamstra

Request:

- Method : GET
- Endpoint : `/api/v1/kegiatan-pamstra?pages={pages}&sizes={sizes}&startDate={startDate}&endDate={endDate}`
- Header :
    - Accept : application/json
    - Authorization: Bearer `YOUR_ACCESS_TOKEN`

Response:

```json
{
  "content": [
    {
      "ids": "string",
      "sektor": "string",
      "namaKegiatan": "string",
      "sumberDana": "string",
      "instansi": "string",
      "paguAnggaran": "number",
      "nomorSuratPermohonan": "string",
      "tanggalSuratPermohonan": "date (yyyy-mm-dd)",
      "tempatPemaparan": "string",
      "tanggalPemaparan": "date (yyyy-mm-dd)",
      "telaahanIntelijen": "string",
      "tindakLanjut": "string",
      "tindakLanjutKeterangan": "string",
      "nomorSprintWalpam": "string",
      "tanggalSprintWalpam": "date (yyyy-mm-dd)",
      "namaPetugasPelaksana": "string",
      "nilaiKontrak": "number",
      "hasilPelaksanaan": "string",
      "hasilPelaksanaanKeterangan": "string",
      "nomorKertasKerja": "string",
      "tanggalKertasKerja": "string",
      "keterangan": "string",
      "urlFile": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": true
}
```