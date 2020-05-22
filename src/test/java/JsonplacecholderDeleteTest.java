import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplacecholderDeleteTest {

    public final String url = "https://jsonplaceholder.typicode.com/";
    public final String posts = "posts";

    @Test
    public void josonplacecholderDeletePost() {

        given()
                .when()
                .delete(url + posts + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
