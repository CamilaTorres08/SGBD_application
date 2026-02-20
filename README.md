# Sistema de Gestión Académica

API REST para la gestión de un sistema académico desarrollada con Spring Boot y SQL Server.

## 📋 Descripción

Esta aplicación permite gestionar estudiantes, materias, salones e inscripciones de un sistema académico. Implementa operaciones CRUD completas para todas las entidades y utiliza **JdbcTemplate** en lugar de JPA para el manejo de la base de datos.

## Tecnologías

- **Java 21**
- **Spring Boot 4.0.2**
- **SQL Server** (Base de datos AcademicoBD)
- **JdbcTemplate** para acceso a datos
- **Maven** para gestión de dependencias
- **Postman** para pruebas de API


## Modelo de Datos

### Entidades Principales

1. **Estudiantes** (`Estudiantes`)
   - id, nombre, email, fechaNacimiento, ciudad

2. **Materias** (`Materias`)
   - id, nombre, creditos, departamento

3. **Salones** (`Salones`)
   - id, numeroSalon, edificio, capacidad, tieneProyector

4. **Inscripciones** (`Inscripciones`)
   - id, estudianteID, materiaID, salonID, semestre, calificacion

### Relaciones

- Cada inscripción relaciona un estudiante, una materia y un salón (relación 1:N)


### Variables de Entorno

Configurar las siguientes variables de entorno:

```properties
DB_URL=jdbc:sqlserver://[servidor]:[puerto];databaseName=AcademicoBD;encrypt=true;trustServerCertificate=true
DB_USER=tu_usuario
DB_PASS=tu_contraseña
```

## 🧪 Pruebas

Se incluye una colección de Postman (`Aplicación SGBD Parte 1.postman_collection.json`) con todas las operaciones disponibles. Importar en Postman para realizar pruebas.
