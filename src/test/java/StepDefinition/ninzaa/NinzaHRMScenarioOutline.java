package StepDefinition.ninzaa;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClasses.ProjectPojo; 

public class NinzaHRMScenarioOutline {

    Response response;

    @Given("Base URL is set")
    public void baseURI() {
        baseURI = "http://49.249.28.218:8091";
    }

    @When("I send a POST request to create a project with {string} {string} {string} {int}")
    public void createProject(String createdBy, String projectName, String status, int teamSize) {

   
        ProjectPojo pojo = new ProjectPojo(createdBy, projectName, status, teamSize);

     
        response = given()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post("/addProject");

        response.then().log().all();
    }

    @Then("response status should be {int}")
    public void validateStatus(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("response should contain {string}")
    public void validateResponse(String value) {
        response.then().assertThat().body(containsString(value));
    }

    @Then("response project name should be {string}")
    public void validateProjectName(String expectedProjectName) {
        response.then().assertThat().body("projectName", equalTo(expectedProjectName));
    }

    @Then("response status field should be {string}")
    public void validateStatusField(String expectedStatus) {
        response.then().assertThat().body("status", equalTo(expectedStatus));
    }
}