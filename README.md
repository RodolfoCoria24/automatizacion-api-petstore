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

## 📸 Evidencia de ejecución

A continuación se muestra la evidencia del reporte generado por **Serenity BDD**, donde se observa que la prueba fue ejecutada correctamente.

![Reporte Serenity](https://github.com/RodolfoCoria24/automatizacion-api-petstore/blob/main/reporte-serenity-1.png)
![Reporte Serenity](https://github.com/RodolfoCoria24/automatizacion-api-petstore/blob/main/reporte-serenity-2.png)
![Reporte Serenity](https://github.com/RodolfoCoria24/automatizacion-api-petstore/blob/main/reporte-serenity-2.png))
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

Proyecto de automatización de pruebas API .

</div>
