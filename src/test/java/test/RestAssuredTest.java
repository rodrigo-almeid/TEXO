package test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {
    String url = "https://jsonplaceholder.typicode.com";
    @Test
    public void getUserPerName() {
        given().
                baseUri(url).
                param("name", "alias odio sit").
                when().
                get("/comments").
                then().
                statusCode(200).
                body("[0].email", equalTo("Lew@alysha.tv"));
    }

    @Test
    public void post() {
        String requestBody = "{\"name\":\"TESTE\",\"email\": \"TESTE@alysha.tv\",\"body\": \"teste body\"}";
        given().
                baseUri(url).header("Content-type", "application/json").
                and().
                body(requestBody).
                when().
                post("/users").
                then().
                statusCode(201).
                body("id", equalTo(11));
    }
    @Test
    public void put() {
        String requestBody = "{\"id\":11,\"name\":\"teste alteracao\",\"email\": \"teste@alteracao.tv\",\"body\": \"teste de alteração no body\"}";
        String id = "1";
        given().
                baseUri(url).header("Content-type", "application/json").
                and().
                body(requestBody).
                when().
                put("/posts/" + id).
                then().
                statusCode(200).
                body("name", equalTo("teste alteracao")).
                body("email", equalTo("teste@alteracao.tv")).
                body("body", equalTo("teste de alteração no body"));
    }
}