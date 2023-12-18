package controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerEndToEndTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testDoMathEndpointMultiplication() {
        given()
                .port(port)
                .contentType("application/json")
                .body("{\"operand1\": 5, \"operand2\": 4, \"operation\": \"*\"}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(20));
    }

    @Test
    public void testDoMathEndpointDivision() {
        given()
                .port(port)
                .contentType("application/json")
                .body("operand1: 10, operand2: 2, operation: /}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(5));
    }

    @Test
    public void testDoMathEndpointAddition() {
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1\": 3, \"operand2\": 7, \"operation\": \"+\"}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(10));
    }

    @Test
    public void testDoMathEndpointSubtraction() {
        given()
                .port(port)
                .contentType("application/json")
                .body("{\"operand1\": 8, \"operand2\": 4, \"operation\": \"-\"}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(4));
    }

    @Test
    public void testDoMathEndpointUnknownOperation() {
        given()
                .port(port)
                .contentType("application/json")
                .body("{\"operand1\": 8, \"operand2\": 4, \"operation\": \"unknown\"}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(500); // Assuming that an unknown operation returns a 500 status code
    }

    @Test
    public void testDoMathEndpointInvalidInput() {
        given()
                .port(port)
                .contentType("application/json")
                .body("{\"operand1\": 8, \"operand2\": 0, \"operation\": \"/\"}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(500); // Assuming that an invalid input returns a 500 status code
    }
}
