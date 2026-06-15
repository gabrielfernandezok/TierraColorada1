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
Cada colaborador debe crear un archivo llamado `config.properties` en la carpeta `src/main/resources/`.

El archivo debe contener lo siguiente:

db.url=jdbc:mysql://localhost:3306/tierracolorada_bd?zeroDateTimeBehavior=CONVERT_TO_NULL
db.user=tu_usuario_mysql // Acá borren tu_usuario_mysql y pongan root si es ese el usuario que supongo tienen en su workbench
db.password=tu_contraseña_mysql // Acá borren tu_contraseña_mysql y pongan su contraseña para entrar en el workbench que tambien supongo que será root o ROOT o la contraseña que hayan puesto
db.driver=com.mysql.cj.jdbc.Driver


⚠️ **Importante**:  
- Este archivo está ignorado en GitHub (`.gitignore`), por lo que cada uno debe crearlo manualmente en su PC.  
- No subir credenciales personales al repositorio.

---

### 4. Crear la base de datos
En MySQL Workbench, creen la base de datos como hicimos en clase y ponganle el nombre de tierracolorada_bd

### 5. Flujo de trabajo
Clonar el repositorio con GitHub Desktop.

Crear la base de datos en MySQL.

Configurar el archivo config.properties con sus usuario y contraseña.

Abrir el proyecto en NetBeans.

Ejecutar y verificar que la tabla se crea automáticamente.

