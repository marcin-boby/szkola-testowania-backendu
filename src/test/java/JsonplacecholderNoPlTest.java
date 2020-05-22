import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplacecholderNoPlTest {

    public final String url = "https://jsonplaceholder.typicode.com/";
    public final String users = "users";

    @Test
    public void josonplacecholderCheckPlDomain() {
        Response response = given()
                .when()
                .get(url + users)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        List<String> email = jsonPath.getList("email");
        email.stream().forEach(n -> Assertions.assertFalse(n.contains(".pl")));
    }
}
