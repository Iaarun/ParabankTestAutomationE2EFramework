package demoapitest;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAPITests {

    @Test
    public void getallProductsTest() {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = given()
                .when().get("/products");
        System.out.println("Status code is: " + response.getStatusCode());
        System.out.println("Status Line is: " + response.statusLine());
        System.out.println("Response Time is: " + response.getTime());
        System.out.println("Content Type is: " + response.getContentType());
        System.out.println(response.getHeader("Content-Encoding"));

        //    Headers headers= response.headers();
        response.headers().forEach(header -> {
            System.out.println(header.getName() + ": " + header.getValue());
        });
        //print the response individual name and value
        String count = response.jsonPath().get("[0].rating.count").toString();
        System.out.println("Count is: " + count);
        Assert.assertEquals(count, "120");
        System.out.println(response.asString());


    }

    @Test
    public void getSingleProductTest() {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = given()
                .when().get("/products/5");
        System.out.println("Status code is: " + response.getStatusCode());
        System.out.println("Status Line is: " + response.statusLine());
        System.out.println("Response Time is: " + response.getTime());
        System.out.println("Content Type is: " + response.getContentType());
        System.out.println(response.getHeader("Content-Encoding"));
        System.out.println(response.asString());
    }
}
