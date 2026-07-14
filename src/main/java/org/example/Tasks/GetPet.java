package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetPet implements Task {

    public static Performable fromPage() {
        return instrumented(GetPet.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String petId = actor.recall("petId");

        actor.attemptsTo(
                Get.resource("/pet/" + petId)
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept", "application/json")
                                .log().all()
                        )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        System.out.println("Mascota consultada con ID: " + petId);
    }
}