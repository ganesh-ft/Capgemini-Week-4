package StepDefinition;

import static io.restassured.RestAssured.*;

import java.util.Random;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class Pet_store {

    Response response;
    String baseUrl = "https://petstore.swagger.io/v2";
    long petId;

    @Given("the petstore API is available")
    public void the_petstore_api_is_available() {
        baseURI = baseUrl;
    }

    @When("I send a POST request to create a pet")
    public void create_pet() {

        petId = new Random().nextInt(10000);

        String body = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/pet");

        System.out.println("Created Pet ID: " + petId);
    }

    @When("I send a GET request to fetch pet details")
    public void get_pet() {
        response = given()
                .when()
                .get("/pet/" + petId);
    }

    @When("I send a PUT request to update the pet")
    public void update_pet() {

        String updatedBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"updatedDog\",\n" +
                "  \"status\": \"sold\"\n" +
                "}";

        response = given()
                .header("Content-Type", "application/json")
                .body(updatedBody)
                .when()
                .put("/pet");
    }

    @When("I send a DELETE request to remove the pet")
    public void delete_pet() {
        response = given()
                .when()
                .delete("/pet/" + petId);
    }

    @Then("the pet should be created successfully with status code {int}")
    public void validate_create(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the pet details should be returned with status code {int}")
    public void validate_get(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the pet details should be updated successfully with status code {int}")
    public void validate_update(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the pet should be deleted successfully with status code {int}")
    public void validate_delete(int statusCode) {
        response.then().statusCode(statusCode);
    }
}