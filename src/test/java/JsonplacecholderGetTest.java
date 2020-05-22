import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplacecholderGetTest {

    public final String url = "https://jsonplaceholder.typicode.com/";
    public final String posts = "posts";

    @Test
    public void josonplacecholderReadAllPosts() {
        Response response = given()
                .when()
                .get(url + posts)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void josonplacecholderReadFirstPosts() {
        Response response = given()
                .when()
                .get(url + posts + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());
    }
}
