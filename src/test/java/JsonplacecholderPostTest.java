import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class JsonplacecholderPostTest {

    public final String url = "https://jsonplaceholder.typicode.com/";
    public final String posts = "posts";

    private static Faker faker;
    private static String userId;
    private static String title;
    private static String body;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
        userId = String.valueOf(faker.number().numberBetween(1,10));
        title = faker.book().title();
        body = faker.lorem().sentence();
    }

    @Test
    public void josonplacecholderCreatePost() {
        JSONObject postObject = new JSONObject();

        postObject.put("userId", userId);
        postObject.put("title", title);
        postObject.put("body", body);

        Response response = given()
                .contentType("application/json")
                .body(postObject.toString())
                .when()
                .post(url + posts)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(userId, jsonPath.get("userId"));
        Assertions.assertEquals(title, jsonPath.get("title"));
        Assertions.assertEquals(body, jsonPath.get("body"));
    }

}
