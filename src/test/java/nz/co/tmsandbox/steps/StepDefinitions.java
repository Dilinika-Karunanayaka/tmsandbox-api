package nz.co.tmsandbox.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.APIManager;
import nz.co.tmsandbox.util.ScenarioContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
public class StepDefinitions {

    private Response response;

    private ScenarioContext scenarioContext;

    public StepDefinitions(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I set the base URI")
    public void setBaseURI() {
        APIManager.setBaseURI();
    }

    @When("I send a GET request to {string}")
    public void sendGETRequest(String endpoint) {
        response = given()
                .when()
                .get(endpoint);
        response.then().log().all();
        scenarioContext.put("status", String.valueOf(response.getStatusCode()));
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        log.info(scenarioContext.get("status"));
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should have the following fields")
    public void verifyResponseFields(DataTable dataTable) {
        dataTable.asList().forEach(field -> assertNotNull(response.jsonPath().get(field)));
    }
}