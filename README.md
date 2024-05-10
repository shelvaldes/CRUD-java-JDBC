# CRUD de tabla de empleados

- Cuenta con Arquitectura con los patrones DAO y Repository.
- Implementa transacciones
- implementa Connection pool
- Java 17
- Swing para la interfaz gráfica

## Instalación y Configuración
### Requisitos Previos
Java Development Kit (JDK): Asegúrate de tener el JDK instalado. Este proyecto requiere JDK 11 o superior. Puedes descargarlo desde Oracle JDK o cualquier otra distribución de Java que prefieras.

#### Servidor de Base de Datos MariaDB: 
Este proyecto utiliza MariaDB como sistema de gestión de base de datos. Necesitarás tener MariaDB instalado y en ejecución. Consulta la guía de instalación de MariaDB para más detalles.

#### Configuración de la Base de Datos
**Crear la Base de Datos:** Inicia sesión en tu servidor de MariaDB y crea una nueva base de datos para el proyecto:

    CREATE DATABASE javacursodb;
**Crear la Tabla de Empleados**: Ejecuta el siguiente comando SQL para crear la tabla `employees` dentro de tu base de datos:

    CREATE  TABLE employees ( id INT AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(255), pa_surname VARCHAR(255), ma_surname VARCHAR(255), email VARCHAR(255) UNIQUE, salary DECIMAL(10, 2), curp CHAR(18) UNIQUE );

**Configuración de Conexión**: Asegúrate de configurar las credenciales de conexión a la base de datos en el archivo de configuración `DatabaseConnection.java`. Establece la URL de conexión, el usuario y la contraseña correspondientes a tu configuración de MariaDB:

    private  static  String  url  =  "jdbc:mariadb://localhost:3306/javacursodb"; 
    private  static  String  user  =  "tu_usuario"; 
    private  static  String  pass  =  "tu_contraseña";


![image](https://github.com/shelvaldes/CRUD-java-JDBC/assets/35204421/2bc9baf4-c6b7-4bb5-90f2-ee1f26f6631b)

# Employee Table CRUD

-   Features architecture with DAO and Repository patterns.
-   Implements transactions.
-   Implements Connection pool.
-   Java 17.
-   Swing for the graphical interface.

## Installation and Configuration

### Prerequisites

**Java Development Key (JDK):** Make sure you have the JDK installed. This project requires JDK 11 or higher. You can download it from [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or any other Java distribution you prefer.

#### MariaDB Database Server:

This project uses MariaDB as the database management system. You will need to have MariaDB installed and running. See the MariaDB installation guide for more details.

#### Database Configuration

**Create the Database:** Log in to your MariaDB server and create a new database for the project:

`CREATE DATABASE javacursodb;` 

**Create the Employee Table**: Execute the following SQL command to create the `employees` table within your database:

`CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    pa_surname VARCHAR(255),
    ma_surname VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    salary DECIMAL(10, 2),
    curp CHAR(18) UNIQUE
);` 

**Connection Configuration**: Make sure to configure the database connection credentials in the `DatabaseConnection.java` configuration file. Set the connection URL, user, and password corresponding to your MariaDB configuration:

`private static String url = "jdbc:mariadb://localhost:3306/javacursodb";
private static String user = "your_username";
private static String pass = "your_password";`
