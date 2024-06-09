package nz.co.tmsandbox.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.APIManager;
import nz.co.tmsandbox.util.ScenarioContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class StepDefinitions {

    private Response response;

    private ScenarioContext scenarioContext;

    public StepDefinitions(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I set the base URL")
    public void setBaseURL() {
        APIManager.setBaseURI();
    }

    @When("I send a GET request to {string}")
    public void sendGETRequest(String endpoint) {
        response = given()
                .when()
                .get(endpoint);
        //response.then().log().all();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        log.info(response.getStatusCode());
        response.then().statusCode(statusCode);
    }

    @Then("the response should have the following fields")
    public void verifyResponseFields(DataTable dataTable) {
        dataTable.asList().forEach(field -> {
            response.then().assertThat().body(field, notNullValue());
        });
    }

    @Then("the response should contain below values in root")
    public void the_response_should_contain_below_values_in_root(DataTable dataTable) {
        Map<String, String> properties = dataTable.asMap(String.class, String.class);
        properties.forEach((propertyName, expectedValue) -> {
            assertTrue(response.jsonPath().getMap("").containsKey(propertyName), "Response body does not contain property: " + propertyName);

            Object propertyValue = response.jsonPath().get(propertyName);
            log.info("propertyName=" + propertyName);
            switch (propertyName) {
                case "Name":
                    assertEquals(expectedValue, propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                case "Number":
                case "Path":
                    assertTrue(propertyValue instanceof String, "Property '" + propertyName + "' is not a String");
                    break;
                case "Subcategories":
                    assertTrue(propertyValue instanceof Iterable, "Property '" + propertyName + "' is not an array");
                    break;
                case "AreaOfBusiness":
                    assertEquals(Integer.parseInt(expectedValue), propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                case "IsLeaf":
                    assertEquals(Boolean.parseBoolean(expectedValue), propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported property: " + propertyName);
            }
        });
    }

    @Then("the response should contains below values")
    public void verifyResponseProperties(DataTable dataTable) {
        Map<String, String> properties = dataTable.asMap(String.class, String.class);
        properties.forEach((propertyName, expectedValue) -> {
            assertTrue(response.jsonPath().getMap("").containsKey(propertyName), "Response body does not contain property: " + propertyName);

            Object propertyValue = response.jsonPath().get(propertyName);
            log.info("propertyName=" + propertyName);
            switch (propertyName) {
                case "Name":
                case "Number":
                case "Path":
                    assertEquals(expectedValue, propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                case "Subcategories":
                    assertTrue(propertyValue instanceof Iterable, "Property '" + propertyName + "' is not an array");
                    break;
                case "AreaOfBusiness":
                    assertEquals(Integer.parseInt(expectedValue), propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                case "HasClassifieds":
                case "CanHaveSecondCategory":
                case "CanBeSecondCategory":
                case "IsLeaf":
                    assertEquals(Boolean.parseBoolean(expectedValue), propertyValue, "Property '" + propertyName + "' does not match expected value");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported property: " + propertyName);
            }
        });
    }

    @Then("the response should not contain a field {string}")
    public void the_response_should_not_contain_a_field(String fieldName) {
        response.then().body("$", not(hasKey(fieldName)));
    }

    @Then("the response should contain a field {string}")
    public void the_response_should_contain_a_field(String fieldName) {
        response.then().body("$", hasKey(fieldName));
    }

    @Then("the response time should be less than {int} milliseconds")
    public void the_response_time_should_be_less_than_milliseconds(long milliseconds) {
        response.then().time(lessThan(milliseconds), TimeUnit.MILLISECONDS);
    }

    @Then("the response format should be {string}")
    public void the_response_format_should_be(String format) {
        if (format.equalsIgnoreCase("JSON")) {
            response.then().header("Content-Type", containsString("application/json"));
        } else if (format.equalsIgnoreCase("XML")) {
            response.then().header("Content-Type", containsString("text/xml"));
        }
    }

    @And("the response should contain a valid data types")
    public void theResponseShouldContainAValidDataTypes() {
        response.then()
                .body("Name", anyOf(isA(String.class), nullValue()))
                .body("Number", anyOf(isA(String.class), nullValue()))
                .body("Path", anyOf(isA(String.class), nullValue()))
                .body("Subcategories", anyOf(isA(List.class), nullValue()))
                .body("HasClassifieds", isA(Boolean.class))
                .body("AreaOfBusiness", isIn(Arrays.asList(0, 1, 2, 3, 4, 5)))
                .body("CanHaveSecondCategory", isA(Boolean.class))
                .body("CanBeSecondCategory", isA(Boolean.class))
                .body("IsLeaf", isA(Boolean.class));
    }

    @When("I send a {string} request to {string}")
    public void i_send_a_request_to(String method, String endpoint) {
        switch (method.toUpperCase()) {
            case "GET":
                response = given().when().get(endpoint);
                break;
            case "POST":
                response = given().when().post(endpoint);
                break;
            case "PUT":
                response = given().when().put(endpoint);
                break;
            case "PATCH":
                response = given().when().patch(endpoint);
                break;
            case "DELETE":
                response = given().when().delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    @Then("the response should not contain sensitive information")
    public void the_response_should_not_contain_sensitive_information() {
        response.then().body(not(containsString("password")))
                .body(not(containsString("secret")));
    }
}