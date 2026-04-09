package StepDefinition.data_tab;

import static io.restassured.RestAssured.*;

import java.util.*;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClasses.data_table;

public class ProjectSteps {

    Response response;
    List<String> projectIds = new ArrayList<>();

    @Given("Base URI is set")
    public void setBaseURI() {
        RestAssured.baseURI = "http://49.249.28.218:8091";
    }

   
    @When("I create projects with following data")
    public void createProjects(DataTable table) {

        List<Map<String, String>> dataList = table.asMaps();

        for (int i = 0; i < dataList.size(); i++) {

            data_table pojo = new data_table();

            pojo.setCreatedBy(dataList.get(i).get("createdBy"));
            pojo.setProjectName(dataList.get(i).get("projectName") + "_" + i);
            pojo.setStatus(dataList.get(i).get("status"));
            pojo.setTeamSize(0); 

            response = given()
                    .contentType(ContentType.JSON)
                    .body(pojo)
            .when()
                    .post("/addProject");

            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(), 201);

            String projectId = response.jsonPath().getString("projectId");
            projectIds.add(projectId);
        }
    }

    @When("I get all created projects")
    public void getProjects() {

        response = given()
        .when()
                .get("/projects");

        response.then().log().all();
    }

 
    @When("I update all projects status to {string}")
    public void updateProjects(String newStatus, DataTable table) {

        List<Map<String, String>> dataList = table.asMaps();

        for (int i = 0; i < projectIds.size(); i++) {

            data_table pojo = new data_table();

            pojo.setCreatedBy(dataList.get(i).get("createdBy"));
            pojo.setProjectName(dataList.get(i).get("projectName") + "_updated");
            pojo.setStatus(newStatus);
            pojo.setTeamSize(0); 

            response = given()
                    .pathParam("pid", projectIds.get(i))
                    .contentType(ContentType.JSON)
                    .body(pojo)
            .when()
                    .put("/project/{pid}");

            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(), 200);
        }
    }

   
    @When("I delete all created projects")
    public void deleteProjects() {

        for (int i = 0; i < projectIds.size(); i++) {

            response = given()
                    .pathParam("pid", projectIds.get(i))
            .when()
                    .delete("/project/{pid}");

            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(), 204);
        }
    }

 
    @Then("response status should be {int}")
    public void validateStatus(int expectedStatus) {
        System.out.println("Validated in steps");
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
    }
}