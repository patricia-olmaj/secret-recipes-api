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