import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Test Get Meal Plan")
public class PostMealPlanTest extends BaseTest {
    @Test(description = "Add items to the meal planner")
    public void addItemToMealPlanner() {
        String username = "frfusch21";
        String apiKey = "1065eecace0f45d9abfa481db565817b";
        String hash = "e43e22e2600cbd495d80d260a765068af209b256";

        String requestBody = "[\n" +
                "    {\n" +
                "        \"date\": 1589500800,\n" +
                "        \"slot\": 1,\n" +
                "        \"position\": 0,\n" +
                "        \"type\": \"INGREDIENTS\",\n" +
                "        \"value\": {\n" +
                "            \"ingredients\": [\n" +
                "                {\n" +
                "                    \"name\": \"1 banana\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"coffee\",\n" +
                "                    \"unit\": \"cup\",\n" +
                "                    \"amount\": \"1\",\n" +
                "                    \"image\": \"https://img.spoonacular.com/ingredients_100x100/brewed-coffee.jpg\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"date\": 1589500800,\n" +
                "        \"slot\": 2,\n" +
                "        \"position\": 0,\n" +
                "        \"type\": \"CUSTOM_FOOD\",\n" +
                "        \"value\": {\n" +
                "            \"id\": 348,\n" +
                "            \"servings\": 1,\n" +
                "            \"title\": \"Aldi Spicy Cashews - 30g\",\n" +
                "            \"image\": \"https://img.spoonacular.com/ingredients_100x100/cashews.jpg\"\n" +
                "        }\n" +
                "    }\n" +
                "]";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post("/{username}/items", username)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("status", equalTo("success"));
    }
}