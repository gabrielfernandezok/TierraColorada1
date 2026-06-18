## Proyecto Tierra Colorada - Escenario A para POO

Este proyecto utiliza **Java con JPA/Hibernate** y una base de datos **MySQL**.  
El objetivo es que cada colaborador pueda ejecutar el proyecto en su entorno local sin necesidad de compartir credenciales.

---

### 1. Requisitos previos
- Tener instalado **MySQL Server** y **MySQL Workbench**.
- NetBeans con soporte para Java y JDBC.
- GitHub Desktop para clonar y sincronizar el repositorio.

---

### 2. Clonar el repositorio
1. Abrir **GitHub Desktop**.
2. Clonar el repositorio desde la URL que se comparte.
3. Abrir el proyecto en NetBeans desde la carpeta clonada.

---

### 3. Configuración de la base de datos
Cada colaborador debe modificar el archivo persistence.xml borrando el user y password que se encuentra y agregando los que use para ingresar a su mysql workbench. 

### 4. Crear la base de datos
En MySQL Workbench, creen la base de datos como hicimos en clase y ponganle el nombre de tierracolorada_bd

### 5. Flujo de trabajo
Clonar el repositorio con GitHub Desktop.

Crear la base de datos en MySQL.

Modificar el archivo persistence.xml con sus usuario y contraseña.

Abrir el proyecto en NetBeans.

Ejecutar y verificar que la tabla se crea automáticamente.

