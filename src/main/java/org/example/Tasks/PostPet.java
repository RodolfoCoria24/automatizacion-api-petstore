package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostPet implements Task {

    private final String nombre;
    private final String estado;

    public PostPet(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public static Performable fromPage(String nombre, String estado) {
        return instrumented(PostPet.class, nombre, estado);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        long petId = System.currentTimeMillis();

        actor.attemptsTo(
                Post.to("/pet")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept", "application/json")
                                .body("""
                                    {
                                        "id": %d,
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

        if (SerenityRest.lastResponse().statusCode() == 200) {

            OnStage.theActorInTheSpotlight().remember(
                    "petId",
                    SerenityRest.lastResponse().path("id").toString()
            );

            String valorDelPetId = actor.recall("petId");

            System.out.println("Pet ID: " + valorDelPetId);
        }
    }
}