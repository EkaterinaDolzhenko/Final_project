package praktikum;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    public static Response registerUser(User user) {
        String json = String.format(
                "{\"email\": \"%s\", \"password\": \"%s\",\"submitPassword\": \"%s\"}",
                user.getEmail(), user.getPassword(), user.getPassword()
        );

        return given()
                .header("Content-type", "application/json")
                .body(json)
                .post(Constants.BASE_URL + "/api/signup");
    }
}