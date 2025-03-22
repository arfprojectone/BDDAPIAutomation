package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;


import java.util.HashMap;
import java.util.Map;

public class UserSteps {
    private Response response;
    private static final String BASE_URL = "https://petstore.swagger.io/v2/user";
    private Map<String, Object> userPayload;
    private Faker faker = new Faker();
    private String username;
    private String password;

    @Given("I have a valid user payload")
    public void i_have_a_valid_user_payload() {
        userPayload = new HashMap<>();
        userPayload.put("id", faker.number().randomNumber());
        userPayload.put("username", faker.name().username());
        userPayload.put("firstName", faker.name().firstName());
        userPayload.put("lastName", faker.name().lastName());
        userPayload.put("email", faker.internet().emailAddress());
        userPayload.put("password", faker.internet().password());
        userPayload.put("phone", faker.phoneNumber().cellPhone());
        userPayload.put("userStatus", faker.number().numberBetween(0, 1));
    }

    @When("I send a request to create a new user")
    public void i_send_a_request_to_create_a_new_user() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .log().body()  // Log body sebelum dikirim
                .when()
                .post(BASE_URL)
                .then()
                .log().all()  // Log response setelah diterima
                .extract()
                .response();
    }

    @Given("I have a valid username {string} and password {string}")
    public void i_have_a_valid_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;

    }

    @When("I send a request to login")
    public void i_send_a_request_to_login() {
        response = RestAssured.given()
                .accept("application/json")
                .when()
                .get(BASE_URL + "/login" + "?username=" + username + "&password=" + password)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a request to login with PUT method")
    public void i_send_a_request_to_login_with_put_method() {
        response = RestAssured.given()
                .accept("application/json")
                .when()
                .put(BASE_URL + "/login" + "?username=" + username + "&password=" + password)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Given("I have a valid username {string}")
    public void i_have_a_valid_username(String string) {
        this.username = string;
    }

    @When("I send a request to get the user details")
    public void i_send_a_request_to_get_the_user_details() {
        response = RestAssured.given()
                .log().all()  // Log request
                .when()
                .get(BASE_URL + "/" + username)
                .then()
                .log().all()  // Log response
                .extract()
                .response();
    }

    @Given("I have an existing username {string}")
    public void i_have_an_existing_username(String string) {
        this.username = string;
    }

    @Given("I have an not found username {string}")
    public void i_have_an_not_found_username(String string) {
        this.username = string;
    }

    @When("I send a request to delete the user")
    public void i_send_a_request_to_delete_the_user() {
        response = RestAssured.given()
                .accept("application/json")
                .when()
                .delete(BASE_URL + "/" + username)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int expectedStatus) {
        Assert.assertEquals(expectedStatus, response.getStatusCode());
    }

    @And("the response should contain username {string}")
    public void the_response_should_contain_username(String expectedUsername) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue("Username not found in response", responseBody.contains("\"username\":\"" + expectedUsername + "\""));
    }

    @And("the response should contain message {string}")
    public void the_response_should_contain_message(String expectedMessage) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue("Response message not found", responseBody.contains("\"message\":\"" + expectedMessage + "\""));
    }

    @And("I have a valid update payload")
    public void i_have_a_valid_update_payload() {
        userPayload = new HashMap<>();
        userPayload.put("id", faker.number().randomNumber());
        userPayload.put("username", faker.name().username());
        userPayload.put("firstName", faker.name().firstName());
        userPayload.put("lastName", faker.name().lastName());
        userPayload.put("email", faker.internet().emailAddress());
        userPayload.put("password", faker.internet().password());
        userPayload.put("phone", faker.phoneNumber().cellPhone());
        userPayload.put("userStatus", faker.number().numberBetween(0, 1));
    }

    @And("I have a invalid update payload, id not integer")
    public void i_have_a_invalid_update_payload_id_not_integer() {
        userPayload = new HashMap<>();
        userPayload.put("id", 1);
        userPayload.put("username", faker.name().username());
        userPayload.put("firstName", faker.name().firstName());
        userPayload.put("lastName", faker.name().lastName());
        userPayload.put("email", faker.internet().emailAddress());
        userPayload.put("password", faker.internet().password());
        userPayload.put("phone", faker.phoneNumber().cellPhone());
        userPayload.put("userStatus", faker.number().numberBetween(0, 1));
    }

    @And("have a invalid update payload, id maximum digit")
    public void i_have_a_invalid_update_payload_id_maximum_digit() {
        userPayload = new HashMap<>();
        userPayload.put("id", "123456789123456789123456789123456789");
        userPayload.put("username", faker.name().username());
        userPayload.put("firstName", faker.name().firstName());
        userPayload.put("lastName", faker.name().lastName());
        userPayload.put("email", faker.internet().emailAddress());
        userPayload.put("password", faker.internet().password());
        userPayload.put("phone", faker.phoneNumber().cellPhone());
        userPayload.put("userStatus", faker.number().numberBetween(0, 1));
    }

    @When("I send a request to update the user with POST method")
    public void i_send_a_request_to_update_the_user_with_post_method() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .log().body()  // Log body sebelum dikirim
                .when()
                .post(BASE_URL + "/" + username)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a request to update the user")
    public void i_send_a_request_to_update_the_user() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .log().body()  // Log body sebelum dikirim
                .when()
                .put(BASE_URL + "/" + username)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
