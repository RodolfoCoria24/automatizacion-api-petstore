<div align="center">

# 🐾 Automatización API Swagger PetStore

### Pruebas automatizadas del flujo CRUD de mascotas con Serenity BDD y Screenplay

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Project-C71A36?logo=apachemaven)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-3.1.20-16A085)
![Cucumber](https://img.shields.io/badge/Cucumber-7.1.0-23D96C?logo=cucumber)
![Rest Assured](https://img.shields.io/badge/Rest%20Assured-4.4.0-6DB33F)
![JUnit](https://img.shields.io/badge/JUnit-4.13.2-25A162?logo=junit5)

</div>

---

## 📌 Descripción

Este proyecto implementa pruebas automatizadas para la API REST **Swagger PetStore 2.0**.  
El objetivo es comprobar las principales operaciones CRUD del recurso `pet`, aplicando una estructura ordenada basada en **Serenity BDD**, **Cucumber**, **Rest Assured** y el patrón de diseño **Screenplay**.

La automatización permite crear una mascota, recuperar su identificador, consultarla, actualizarla, eliminarla y verificar que el registro ya no se encuentre disponible.

---

## 🎯 Objetivo del proyecto

Validar el correcto funcionamiento de los endpoints relacionados con la gestión de mascotas mediante solicitudes HTTP automatizadas y generar un reporte de resultados con Serenity.

---

## 🌐 API utilizada

| Elemento | Valor |
|---|---|
| Documentación | [Swagger PetStore](https://petstore.swagger.io/) |
| URL base | `https://petstore.swagger.io/v2` |
| Recurso automatizado | `/pet` |
| Formato de intercambio | JSON |

---

## 🧪 Casos de prueba automatizados

| ID | Caso de prueba | Método | Endpoint | Resultado esperado |
|---|---|---:|---|---:|
| CP01 | Crear una mascota | `POST` | `/pet` | `200` |
| CP02 | Consultar la mascota creada | `GET` | `/pet/{petId}` | `200` |
| CP03 | Actualizar los datos de la mascota | `PUT` | `/pet` | `200` |
| CP04 | Eliminar la mascota creada | `DELETE` | `/pet/{petId}` | `200` |
| CP05 | Verificar que la mascota eliminada no exista | `GET` | `/pet/{petId}` | `404` |

---

## 🔄 Flujo automatizado

```text
Crear mascota
      ↓
Guardar petId
      ↓
Consultar mascota
      ↓
Actualizar mascota
      ↓
Eliminar mascota
      ↓
Consultar nuevamente
      ↓
Validar código 404
```

El identificador `petId` se genera durante la ejecución y se almacena en la memoria del actor de Serenity. De esta forma, las siguientes operaciones trabajan con el mismo registro creado por la prueba.

---

## 🛠️ Tecnologías utilizadas

- **Java 17:** lenguaje de programación.
- **Maven:** gestión de dependencias y ejecución del proyecto.
- **Serenity BDD:** generación de reportes y administración de pruebas.
- **Cucumber:** definición de escenarios en lenguaje Gherkin.
- **Screenplay:** organización de actores, tareas, preguntas y habilidades.
- **Rest Assured:** envío y validación de solicitudes HTTP.
- **JUnit 4:** ejecución de los escenarios automatizados.
- **IntelliJ IDEA:** entorno de desarrollo utilizado.

---

## 🧱 Arquitectura del proyecto

El proyecto está organizado con el patrón **Screenplay**:

- **Actor:** representa al usuario que interactúa con la API.
- **Ability:** permite que el actor consuma servicios REST mediante `CallAnApi`.
- **Tasks:** contienen las acciones POST, GET, PUT y DELETE.
- **Questions:** consultan el código de respuesta de la API.
- **Step Definitions:** conectan los pasos Gherkin con las clases Java.
- **Runner:** configura y ejecuta los escenarios de Cucumber.

---

## 📂 Estructura

```text
automatizacion-api-petstore
├── src
│   ├── main
│   │   └── java
│   │       └── org.example
│   │           ├── Questions
│   │           │   └── ResponseCode.java
│   │           └── Tasks
│   │               ├── PostPet.java
│   │               ├── GetPet.java
│   │               ├── PutPet.java
│   │               └── DeletePet.java
│   └── test
│       ├── java
│       │   └── org.example
│       │       ├── RunnerTest.java
│       │       └── StepDefinitions
│       │           ├── ParametersDefinitions.java
│       │           └── PetStepDefinition.java
│       └── resources
│           └── features
│               └── Pet.feature
├── .gitignore
├── pom.xml
└── README.md
```

---

## ✅ Requisitos previos

Antes de ejecutar el proyecto, se debe contar con:

- JDK 17 o superior.
- Apache Maven.
- Git.
- IntelliJ IDEA o cualquier IDE compatible con Maven.
- Conexión a Internet para consumir la API.

Puedes verificar las versiones instaladas con:

```bash
java -version
mvn -version
git --version
```

---

## 📥 Instalación

Clona el repositorio:

```bash
git clone https://github.com/RodolfoCoria24/automatizacion-api-petstore.git
```

Ingresa a la carpeta del proyecto:

```bash
cd automatizacion-api-petstore
```

Descarga las dependencias y compila:

```bash
mvn clean compile
```

---

## ▶️ Ejecución de las pruebas

Para ejecutar todos los escenarios:

```bash
mvn clean verify
```

También puedes ejecutar directamente la clase:

```text
src/test/java/org/example/RunnerTest.java
```

El Runner utiliza la etiqueta general:

```java
tags = "@pet"
```

Para ejecutar un caso específico, se puede cambiar temporalmente el tag. Por ejemplo:

```java
tags = "@CP01_Pet"
```

---

## 📊 Reporte Serenity

Después de ejecutar las pruebas, Serenity genera el reporte HTML en:

```text
target/site/serenity/index.html
```

El reporte permite visualizar:

- Escenarios ejecutados.
- Pasos realizados.
- Solicitudes y respuestas de la API.
- Código HTTP obtenido.
- Casos exitosos y fallidos.
- Tiempo de ejecución.

En Windows puedes abrirlo desde el explorador de archivos o desde IntelliJ haciendo clic derecho sobre `index.html` y seleccionando **Open in Browser**.

---

## 📝 Ejemplo de escenario Gherkin

```gherkin
@CP01_Pet
Scenario Outline: Crear una mascota exitosamente
  Given el actor establece el endpoint de pet
  When el actor crea una mascota con el nombre "<nombre>" y estado "<estado>"
  Then el codigo de respuesta debe ser 200

  Examples:
    | nombre | estado    |
    | Toby   | available |
```

---

## 🔍 Validaciones realizadas

La automatización valida principalmente:

- El código de estado HTTP de cada operación.
- La creación correcta de una mascota.
- La recuperación y reutilización dinámica del `petId`.
- La actualización de los datos enviados.
- La eliminación del registro.
- La respuesta `404` después de consultar una mascota eliminada.

---

## 🚀 Posibles mejoras

- Validar los campos del cuerpo de respuesta.
- Incorporar escenarios negativos adicionales.
- Utilizar modelos Java para construir los cuerpos JSON.
- Configurar integración continua con GitHub Actions.
- Agregar datos de prueba desde archivos externos.
- Publicar automáticamente el reporte Serenity.

---

## 👨‍💻 Autor

**Rodolfo Coria**

- GitHub: [@RodolfoCoria24](https://github.com/RodolfoCoria24)
- Repositorio: [automatizacion-api-petstore](https://github.com/RodolfoCoria24/automatizacion-api-petstore)

---

<div align="center">

Proyecto académico de automatización de pruebas API con Serenity BDD.

</div>
