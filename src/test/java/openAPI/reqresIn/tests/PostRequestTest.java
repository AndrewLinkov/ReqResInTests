package openAPI.reqresIn.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static openAPI.reqresIn.tests.BaseTest.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class PostRequestTest {

    @Test
    @DisplayName("Создание пользователя. Пользователь прописан в json файле. POST.")
    void postAddUserTest() {

        File jsonBodyNewUser = new File("src/test/java/openAPI/reqresIn/jsons/addNewUser.json");

        // Предусловие
        String token = given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(jsonBodyNewUser)
                .log().headers()
                .log().body()

                // Действие
                .when()
                .post("login")

                // Проверка
                .then()
                .log().all()
                .statusCode(200)

                // Извлекаем токен
                .extract().asString();

        // Проверяем, что в ответе есть токен
        assertThat(token).contains("token");
    }
}
