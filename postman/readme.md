# Postman cURLs for CRUD operations.
**Note:** Change header "cookie" to your own cookie.

## Get a list of all available teams
```
curl --location 'http://localhost:8080/equipos' \
--header 'Content-Type: application/json' \
--header 'Cookie: <cookie>'
```

---

## Get information about a team
```
curl --location 'http://localhost:8080/equipos/3' \
--header 'Content-Type: application/json' \
--header 'Cookie: <cookie>'
```

---

## Update info team
```
curl --location --request PUT 'http://localhost:8080/equipos/1' \
--header 'Content-Type: application/json' \
--header 'Cookie: <cookie>' \
--data '{
  "nombre": "Equipo Argentino",
  "liga": "Liga Argentina",
  "pais": "Argentina"
}'
```

---

## Create a new team
```
curl --location 'http://localhost:8080/equipos/' \
--header 'Content-Type: application/json' \
--header 'Cookie: <cookie>' \
--data '{
    "nombre": "Nuevo Equipo FC",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
}'
```

---

## Delet team
```
curl --location --request DELETE 'http://localhost:8080/equipos/3' \
--header 'Content-Type: application/json' \
--header 'Cookie: <cookie>'
```