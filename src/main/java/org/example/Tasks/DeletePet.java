package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePet implements Task {

    private final String petId;

    public DeletePet() {
        this.petId = OnStage.theActorInTheSpotlight().recall("petId");
    }

    public static Performable fromPage() {
        return instrumented(DeletePet.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Delete.from("/pet/" + petId)
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept", "application/json")
                                .log().all()
                        )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        System.out.println("Mascota eliminada con ID: " + petId);
    }
}