package StepDefinition.ninzaaa_emp;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClasses.EmployeePojo;

public class NinzaCRUDSteps {

    Response response;
    String empId;
    String username;

    @Given("Base URL is set")
    public void baseURI() {
        RestAssured.baseURI = "http://49.249.28.218:8091";
    }

    @And("Content type is JSON")
    public void contentType() {
    }

    @When("I send POST request to create employee with {string} {string} {string} {string}")
    public void createEmployee(String name, String email, String phone, String designation) {

        EmployeePojo pojo = new EmployeePojo();
        pojo.setEmpName(name);
        pojo.setEmail(email);
        pojo.setMobileNo(phone);
        pojo.setDesignation(designation);
        pojo.setDob("12/12/1998");
        pojo.setExperience(2);
        pojo.setUsername(name);
        pojo.setRole("user");
        pojo.setProject("TYSS");

        response = given()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post("/employees");

        response.prettyPrint();
    }

    
    @Then("response status should be {int}")
    public void validateStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("store employee details")
    public void storeEmployeeDetails() {
        empId = response.jsonPath().getString("employeeId");
        username = response.jsonPath().getString("username");

        System.out.println("Stored Employee ID: " + empId);
        System.out.println("Stored Username: " + username);
    }

    
    @When("I send GET request for stored employee")
    public void getEmployee() {

        response = given()
                .pathParam("username", username)
                .when()
                .get("/employee/{username}");

        response.prettyPrint();
    }

   
    @When("I send PUT request to update employee with {string}")
    public void updateEmployee(String newName) {

       
        String mobile = response.jsonPath().getString("mobileNo");
        String email = response.jsonPath().getString("email");
        String designation = response.jsonPath().getString("designation");

        EmployeePojo pojo = new EmployeePojo();
        pojo.setEmpName(newName);
        pojo.setEmail(email);
        pojo.setMobileNo(mobile);
        pojo.setDesignation(designation);
        pojo.setDob("12/12/1998");
        pojo.setExperience(3);
        pojo.setUsername(username); 
        pojo.setRole("user");
        pojo.setProject("TYSS");

        response = given()
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(pojo)
                .when()
                .put("/employee/{username}");

        response.prettyPrint();
    }

    
    @When("I send DELETE request for stored employee")
    public void deleteEmployee() {

        response = given()
                .pathParam("username", username)
                .when()
                .delete("/employee/{username}");

        response.prettyPrint();
    }

   
    @And("response field {string} should be {string}")
    public void validateField(String field, String expected) {

        String actual = response.jsonPath().getString(field);

        Assert.assertEquals(actual, expected,
                "Mismatch in field: " + field);
    }
}