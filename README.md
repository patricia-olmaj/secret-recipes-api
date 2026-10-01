# Autenticación

Registro (/api/v1/auth/register)
Un nuevo usuario se da de alta con un nombre de usuario y contraseña (cifrar).

Login (/api/v1/auth/login): El usuario se identifica y el sistema devuelve un token.

Filtro de seguridad: Cada vez que el usuario quiera hacer algo con sus recetas, tendrá que presentar ese token en la cabecera de la petición.

# CRUD
Cada receta pertenece a cada usuario y puede gestionar sus recetas: 

Crear: Guardar una receta con su título, categoría (ej. Cenas, Postres), ingredientes y pasos de preparación.

Listar: Ver únicamente la lista de recetas que le pertenecen a ese usuario .

Actualizar: Modificar los ingredientes de una receta.

Borrar: Eliminar una receta.


# Arrancar Dockerfile
```
docker compose up --build -d
```
# Revisar logs de springboot 
```
docker compose logs -f app
```

Register:
```
curl -X POST http://localhost:8080/api/v1/auth/register   -H "Content-Type: application/json"   -d '{
    "username": "patricia23",
    "password": "prueba123",
    "firstname": "Pati",
    "lastname": "Olmaj",
    "country": "Spain"
  }'
```
Login:
```
curl -i -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "patricia23",
    "password": "prueba123"
  }'
```
CREATE RECIPE:
```
curl -i -X POST http://localhost:8080/api/v1/recipes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer AQUI_TOKEN" \
  -d '{
    "name": "Tortitas de avena",
    "category": "BREAKFAST",
    "ingredients": "Avena, platano, huevo, canela",
    "instructions": "Mezclar todos los ingredientes y cocinar en una sarten antiadherente."
  }'
```

GET RECIPE:
```
curl -i -X GET http://localhost:8080/api/v1/me/recipes/1 \
  -H "Authorization: Bearer AQUI_TOKEN"
```
