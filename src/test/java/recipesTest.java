import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@Feature("Recipes")
public class recipesTest extends BaseTestSpooner{

    String apiKey = "2458b9aa37b645e2a838124ac84a8492";

    @Test(description = "Test Search Recipes")
    public void searchRecipes(){
        given().queryParam("apiKey", apiKey)
                .queryParam("query", "pasta")
                .queryParam("maxFat", "25")
                .queryParam("number", "2")
                .log().ifValidationFails()
                .when()
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Search Recipes by Nutrient")
    public void searchRecipesByNutrient(){
        given().queryParam("apiKey", apiKey)
                .queryParam("minCarbs", "10")
                .queryParam("maxCarbs", "50")
                .queryParam("number", "2")
                .log().ifValidationFails()
                .when()
                .get("/recipes/findByNutrients")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Search Recipes by Ingredients")
    public void searchRecipesByIngredients(){
        given().queryParam("apiKey", apiKey)
                .queryParam("ingredients", "apple,flour,sugar")
                .queryParam("number", "2")
                .log().ifValidationFails()
                .when()
                .get("/recipes/findByIngredients")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Get Random Recipes")
    public void getRandomRecipes(){
        given().queryParam("apiKey", apiKey)
                .queryParam("include-tags", "vegetarian,dessert")
                .log().ifValidationFails()
                .when()
                .get("/recipes/random")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Analyze Recipe")
    public void testAnalyzeRecipe(){
        String requestBody = "{\n" +
                "    \"title\": \"Spaghetti Carbonara\",\n" +
                "    \"servings\": 2,\n" +
                "    \"ingredients\": [\n" +
                "        \"1 lb spaghetti\",\n" +
                "        \"3.5 oz pancetta\",\n" +
                "        \"2 Tbsps olive oil\",\n" +
                "        \"1  egg\",\n" +
                "        \"0.5 cup parmesan cheese\"\n" +
                "    ],\n" +
                "    \"instructions\": \"Bring a large pot of water to a boil and season generously with salt. Add the pasta to the water once boiling and cook until al dente. Reserve 2 cups of cooking water and drain the pasta. \"\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .body(requestBody)
                .when()
                .post("/recipes/analyze")
                .then()
                .statusCode(200)
                .extract().response();

    }

}
