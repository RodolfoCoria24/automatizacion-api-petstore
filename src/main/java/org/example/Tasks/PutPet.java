package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutPet implements Task {

    private final String nombre;
    private final String estado;

    public PutPet(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public static Performable fromPage(String nombre, String estado) {
        return instrumented(PutPet.class, nombre, estado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String petId = actor.recall("petId");

        actor.attemptsTo(
                Put.to("/pet")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept", "application/json")
                                .body("""
                                        {
                                            "id": %s,
                                            "name": "%s",
                                            "photoUrls": ["foto.jpg"],
                                            "status": "%s"
                                        }
                                        """.formatted(petId, nombre, estado)
                                )
                                .log().all()
                        )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        System.out.println("Mascota actualizada con ID: " + petId);
    }
}