//package StepDefinition;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//import io.cucumber.java.en.*;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//public class ninza {
//
//    Response response;
//    static String projectId;
//
//    @Given("Base URI is set")
//    public void setBaseURI() {
//        baseURI = "http://49.249.28.218:8091";
//    }
//
//
//    @When("I send a POST request to create a project")
//    public void createProject() {
//
//       // String uniqueName = "proj_" + System.currentTimeMillis();
//
//        String body = "{\n" +
//                "  \"createdBy\": \"mrlove\",\n" +
//                "  \"projectName\": \"proj1234567\",\n" +
//                "  \"status\": \"Created\",\n" +
//                "  \"teamSize\": 0\n" +
//                "}";
//
//        response = given()
//                .contentType(ContentType.JSON)
//                .body(body)
//                .when()
//                .post("/addProject");
//
//        projectId = response.jsonPath().get("projectId");
//
//        System.out.println("Created Project ID: " + projectId);
//    }
//
// 
//    @When("I send a GET request to fetch projects")
//    public void getProjects() {
//        response = given()
//                .when()
//                .get("/projects");
//    }
//
//    @When("I send PUT request to update project")
//    public void sendPutRequest() {
//
//        String body = "{\n" +
//                "  \"createdBy\": \"mrlove\",\n" +
//                "  \"projectName\": \"proj1234567\",\n" +
//                "  \"status\": \"Ongoing\",\n" +
//                "  \"teamSize\": 0\n" +
//                "}";
//
//        response = given()
//                .contentType("application/json")
//                .body(body)
//                .when()
//                .put("/project/" + projectId); 
//    }
//
//    @When("I send DELETE request to delete project")
//    public void deleteProject() {
//
//        response = given()
//                .when()
//                .delete("/project/" + projectId);
//    }
//
//  
//    @Then("response status should be {int}")
//    public void validateStatus(int statusCode) {
//        response.then().statusCode(statusCode);
//    }
//
// 
//    @Then("response should contain {string}")
//    public void validateResponse(String value) {
//        response.then().body(containsString(value));
//    }
//}

package StepDefinition;

import static io.restassured.RestAssured.*;

import java.util.Random;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ninza {

    Response response;

    @Given("Base URI is set for ninza simple")
    public void setBaseURI() {
        baseURI = "http://49.249.28.218:8091";
    }

    @When("I send a POST request to create a project simple")
    public void createProject() {

        String projectName = "Proj_" + new Random().nextInt(1000);

        String body = "{\n" +
                "  \"createdBy\": \"sabari\",\n" +
                "  \"projectName\": \"" + projectName + "\",\n" +
                "  \"status\": \"Created\",\n" +
                "  \"teamSize\": 5\n" +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/addProject");
    }

    @Then("response status should be {int}")
    public void validateStatus(int code) {
        response.then().statusCode(code);
    }
}