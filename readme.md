##  Como correr


La aplicación esta containerizada usando docker y contiene todas sus dependencias. La bd es precargada con el archivo data.sql.

Para correr la aplicación se requiere ejecutar el siguiente comando de docker:

```shell
docker-compose up
```

## Endpoints:

### Search books (buscar libros por titulo y/o autor y/o categoria)

```
curl --location 'http://localhost:8080/books?title=title&author=author&category=category'
```


### Available books (listado de libros disponibles)

```
curl --location 'http://localhost:8080/available-books'
```

### Book Loan (cargar un prestamo para un determinado usuario y libro)

```
curl --location 'http://localhost:8080/loan' \
--header 'Content-Type: application/json' \
--data '{
    "customer_id": 1,
    "book_id": 1
}'
```


## Postman collection con los endpoints

Importar `galicia-challenge.postman_collection` en postman.
