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

### Get All Surat Masuk



