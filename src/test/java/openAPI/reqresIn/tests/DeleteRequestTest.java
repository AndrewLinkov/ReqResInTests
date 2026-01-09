package openAPI.reqresIn.tests;

import openAPI.reqresIn.model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static openAPI.reqresIn.tests.BaseTest.BASE_URL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DeleteRequestTest {

    @Test
    @DisplayName("DELETE - Удаление пользователя")
    void deleteUserTest() {

        int userId = 3;

        given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .log().body()
                .when()
                .delete("users/" + userId)
                .then()
                .statusCode(204)
                .log().all();

        given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("users/" + userId)
                .then()
                .statusCode(404);
    }
}
