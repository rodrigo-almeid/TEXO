package test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {
    String url = "https://gorest.co.in/public-api";
    String token = "89ce14201c7a904324e4085ad66430b9d1135f196f2563368c8f1cebcebd96b1";
    @Test
    public void getUserPerName(){
        given().
            baseUri(url).
            param("name", "Ronald Kessler").
       when().
            get("/users").
       then().
            statusCode(200).
            body("data[0].email", equalTo("ronald.kessler@teste.com"));
    }
    @Test
    public void getUserPerId()
    {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String id = Integer.toString(jsonPathEvaluator.get("data[4].id"));

        given().
                baseUri(url).
        when().
                get(url + "/" + id + "/posts").
        then().
                statusCode(200).
                body("data[0].body", equalTo("n"));
    }
    @Test
    public void createNewUser(){
        url = "https://gorest.co.in/public/v1";
        String requestBody = "{\"name\":\"Teste QA\", \"gender\":\"male\", \"email\":\"create.new@user.com\", \"status\":\"active\"}";
        //criando um usuário
        Response response =
            given().
                baseUri(url).
                auth().
                oauth2(token).
                header("Content-type", "application/json").
                and().
                body(requestBody).
            when().
                post("/users").
            then().
                statusCode(201).
                extract().response();
        String id = response.jsonPath().getString("data.id");
        given().
                baseUri(url).
        when().
            get(url + "/" + id + "/posts").
        then().
            statusCode(200).body("data.nome", equalTo("Teste QA"));
    }
}
