import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Feature("Connect User")
public class ConnectUserTest extends BaseTestSpooner{

    @Test(description = "Get Username and Hash")
    public void testConnectUser(){
        String apiKey = "1b03a112b9ca4dcdb3162e950dc1b540";
        String requestBody = "{\n" +
                "    \"username\": \"reginaag\",\n" +
                "    \"firstName\": \"Regina\",\n" +
                "    \"lastName\": \"Ayu\",\n" +
                "    \"email\": \"rgnaayu@gmail.com\"\n" +
                "}";

        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .body(requestBody)
                .post("users/connect")
                .then()
                .statusCode(200)
                .extract().response();
    }

}
