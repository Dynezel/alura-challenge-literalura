📚 Proyecto LiterAlura
Este es un proyecto Java desarrollado con Spring Boot que consume la API de Gutendex para gestionar libros y autores del Proyecto Gutenberg. Ofrece diversas funcionalidades de consulta y organización de los datos obtenidos.

🚀 Funcionalidades
El programa se ejecuta en consola y permite al usuario realizar las siguientes acciones:

1 - Buscar Libros Por Título  
2 - Listar Libros Registrados  
3 - Listar Autores Registrados  
4 - Listar Autores vivos en un determinado año  
5 - Listar Libros por Idioma  
6 - Mostrar Cantidad de Libros por un Idioma  
0 - Salir
📌 Detalles de cada función
Buscar Libros Por Título: Permite al usuario ingresar el título de un libro y consulta la API para traer los resultados correspondientes.

Listar Libros Registrados: Muestra los libros previamente guardados en la base de datos local.

Listar Autores Registrados: Presenta una lista de todos los autores obtenidos de los libros registrados.

Listar Autores vivos en un determinado año: Solicita al usuario un año y muestra aquellos autores cuya vida abarcó dicho período (usando derived queries de Spring).

Listar Libros por Idioma: Filtra los libros según un idioma proporcionado.

Mostrar Cantidad de Libros por un Idioma: Devuelve la cantidad total de libros por cada idioma registrado.

🛠 Tecnologías Utilizadas
Java 17

Spring Boot

Spring Data JPA

API RestTemplate

H2 Database (en memoria)

Maven

💻 Cómo ejecutar el proyecto
Clona este repositorio:

git clone https://github.com/Dynezel/alura-challenge-literalura.git
Abre el proyecto en tu IDE (IntelliJ, Eclipse, VSCode, etc.).

Ejecuta la clase principal LiteraluraApplication.

<<<<<<< HEAD
Se abrirá el menú en la consola donde podrás elegir las distintas opciones.
=======
Se abrirá el menú en la consola donde podrás elegir las distintas opciones.
>>>>>>> dd008645020331bc4735051e11a5f833aa3d1d4b
