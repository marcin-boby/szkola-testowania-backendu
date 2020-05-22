import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplacecholderPutTest {

    public final String url = "https://jsonplaceholder.typicode.com/";
    public final String posts = "posts";

    private static Faker faker;
    private static String userId;
    private static String title;
    private static String body;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        userId = String.valueOf(faker.number().numberBetween(1,10));
        title = faker.book().title();
        body = faker.lorem().sentence();
    }

    @Test
    public void josonplacecholderChangePutPost() {
        JSONObject postObject = new JSONObject();

        postObject.put("userId", userId);
        postObject.put("title", title);
        postObject.put("body", body);

        Response response = given()
                .contentType("application/json")
                .body(postObject.toString())
                .when()
                .put(url + posts + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(userId, jsonPath.get("userId"));
        Assertions.assertEquals(title, jsonPath.get("title"));
        Assertions.assertEquals(body, jsonPath.get("body"));
    }

    @Test
    public void josonplacecholderChangePatchPost() {
        JSONObject postObject = new JSONObject();

        postObject.put("title", title);

        Response response = given()
                .contentType("application/json")
                .body(postObject.toString())
                .when()
                .put(url + posts + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(title, jsonPath.get("title"));
    }

}
