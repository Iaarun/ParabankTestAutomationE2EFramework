package demoapitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostAPITests {

    @Test
    public void createProductTest() {
        // Implement the logic to create a product using POST request
        // Use RestAssured to send a POST request to the API endpoint
        // Validate the response and assert the expected results
        // You can use JSON payload to create a new product
        // Example:

        RestAssured.baseURI = "http://localhost:3000";
        String payload = "{ \"title\": \"New Product\", \"price\": 29.99, \"description\": \"A new product description\", \"category\": \"electronics\", \"image\": \"https://example.com/product.jpg\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when().post("/products");

        System.out.println("Status code is: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test
    public void updateproductTest() {
            RestAssured.baseURI = "http://localhost:3000";

    String payload = "" +
            "{\n" +
            "    \"title\": \"New Product\",\n" +
            "    \"price\": 50.99,\n" +
            "    \"description\": \"A new product description\",\n" +
            "    \"category\": \"electronics\",\n" +
            "    \"image\": \"https://example.com/product.jpg\",\n" +
            "    \"id\": 5\n" +
            "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when().put("/products/5");
        System.out.println("Status code is: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("price").toString(), "50.99");

}

@Test
public void updateProductUSingPojo(){
        RestAssured.baseURI = "http://localhost:3000";

        Product product = new Product();
        product.setId(5);
        product.setTitle("New Product");
        product.setPrice(50.99);
        product.setDescription("A new product description");
        product.setCategory("electronics");
        product.setImage("https://example.com/product.jpg");

        Rating rating = new Rating();
            rating.setRate(4.5);
            rating.setCount(100);
            product.setRating(rating);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(product)
                    .when().put("/products/5");
            System.out.println("Status code is: " + response.getStatusCode());
            System.out.println("Response Body: " + response.asString());

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("price").toString(), "50.99");
}
}
