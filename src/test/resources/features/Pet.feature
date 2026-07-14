@pet
Feature: APIs de Swagger PetStore

  Como usuario de PetStore
  Quiero realizar operaciones sobre las mascotas
  Para verificar el correcto funcionamiento de la API


  @CP01_Pet
  Scenario Outline: Crear una mascota exitosamente
    Given el actor establece el endpoint de pet
    When el actor crea una mascota con el nombre "<nombre>" y estado "<estado>"
    Then el codigo de respuesta debe ser 200

    Examples:
      | nombre | estado    |
      | Toby   | available |


  @CP02_Pet
  Scenario: Consultar una mascota exitosamente
    Given el actor establece el endpoint de pet
    And el actor crea una mascota con el nombre "Firulais" y estado "available"
    When el actor consulta la mascota creada anteriormente
    Then el codigo de respuesta debe ser 200


  @CP03_Pet
  Scenario Outline: Actualizar una mascota exitosamente
    Given el actor establece el endpoint de pet
    And el actor crea una mascota con el nombre "Max" y estado "available"
    When el actor actualiza la mascota con el nombre "<nombre>" y estado "<estado>"
    Then el codigo de respuesta debe ser 200

    Examples:
      | nombre                 | estado |
      | Mascotita de NNT       | sold   |


  @CP04_Pet
  Scenario: Eliminar una mascota exitosamente
    Given el actor establece el endpoint de pet
    And el actor crea una mascota con el nombre "Rocky" y estado "available"
    When el actor elimina la mascota creada anteriormente
    Then el codigo de respuesta debe ser 200


  @CP05_Pet
  Scenario: Verificar que una mascota eliminada ya no existe
    Given el actor establece el endpoint de pet
    And el actor crea una mascota con el nombre "Bobby" y estado "available"
    And el actor elimina la mascota creada anteriormente
    When el actor consulta la mascota creada anteriormente
    Then el codigo de respuesta debe ser 404