package org.example.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import org.example.Tasks.DeletePet;
import org.example.Tasks.GetPet;
import org.example.Tasks.PostPet;
import org.example.Tasks.PutPet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class PetStepDefinition {

    public static Logger LOGGER =
            LoggerFactory.getLogger(PetStepDefinition.class);

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint de pet")
    public void elActorEstableceElEndpointDePet(Actor actor) {
        actor.whoCan(
                CallAnApi.at("https://petstore.swagger.io/v2")
        );
    }

    @When("el {actor} crea una mascota con el nombre {string} y estado {string}")
    public void elActorCreaUnaMascotaConElNombreYEstado(
            Actor actor,
            String nombre,
            String estado
    ) {

        theActorInTheSpotlight().attemptsTo(
                PostPet.fromPage(nombre, estado)
        );
    }

    @When("el {actor} consulta la mascota creada anteriormente")
    public void elActorConsultaLaMascotaCreadaAnteriormente(Actor actor) {

        theActorInTheSpotlight().attemptsTo(
                GetPet.fromPage()
        );
    }

    @When("el {actor} actualiza la mascota con el nombre {string} y estado {string}")
    public void elActorActualizaLaMascotaConElNombreYEstado(
            Actor actor,
            String nombre,
            String estado
    ) {

        theActorInTheSpotlight().attemptsTo(
                PutPet.fromPage(nombre, estado)
        );
    }

    @When("el {actor} elimina la mascota creada anteriormente")
    public void elActorEliminaLaMascotaCreadaAnteriormente(Actor actor) {

        theActorInTheSpotlight().attemptsTo(
                DeletePet.fromPage()
        );
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {

        theActorInTheSpotlight().should(
                seeThat(
                        "El codigo de respuesta",
                        ResponseCode.getStatus(),
                        equalTo(responseCode)
                )
        );
    }
}