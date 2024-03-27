import io.qameta.allure.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Test Get Meal Plan")
public class GetMealPlanTest extends BaseTest {
    @Test(description = "Test ShoppingList")
    public void testComplexSearch() {
        given()
        .queryParam("apiKey", "1065eecace0f45d9abfa481db565817b")
        .queryParam("hash", "e43e22e2600cbd495d80d260a765068af209b256")
        .log().ifValidationFails()
        .when()
        .get("/frfusch21/shopping-list")
        .then()
        .log().body()
        .statusCode(200);
    }


    @Test(description = "Test Meal Planner Generate Endpoint")
    public void testMealPlannerGenerate() {
        given()
                .queryParam("apiKey", "1065eecace0f45d9abfa481db565817b")
                .log().ifValidationFails()
                .when()
                .get("/generate")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("week.monday.meals.size()", equalTo(3)); // Verifying 3 meals on Monday
    }

}
