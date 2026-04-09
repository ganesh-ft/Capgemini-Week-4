package StepDefinition;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FakeRest {

    Response response;
    int bookId = 1; 

    @Given("Base URI is set for fakerest")
    public void setBaseURI() {
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
    }

    @When("I send POST request to create a book")
    public void createBook() {

        String body = "{\n" +
                "  \"id\": " + bookId + ",\n" +
                "  \"title\": \"Test Book\",\n" +
                "  \"description\": \"API Testing\",\n" +
                "  \"pageCount\": 100,\n" +
                "  \"excerpt\": \"Sample\",\n" +
                "  \"publishDate\": \"2026-03-24T00:00:00.000Z\"\n" +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/Books");

        System.out.println("Created Book ID: " + bookId);
    }

    @When("I send GET request to fetch book")
    public void getBook() {

        response = given()
                .when()
                .get("/Books/" + bookId);

        response.then().log().all();
    }

    @When("I send PUT request to update book")
    public void updateBook() {

        String body = "{\n" +
                "  \"id\": " + bookId + ",\n" +
                "  \"title\": \"Updated Book\",\n" +
                "  \"description\": \"Updated\",\n" +
                "  \"pageCount\": 200,\n" +
                "  \"excerpt\": \"Updated\",\n" +
                "  \"publishDate\": \"2026-03-24T00:00:00.000Z\"\n" +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/Books/" + bookId);
    }

    @When("I send DELETE request to remove book")
    public void deleteBook() {

        response = given()
                .when()
                .delete("/Books/" + bookId);
    }

    @Then("fakerest status should be {int}")
    public void validateStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }
}