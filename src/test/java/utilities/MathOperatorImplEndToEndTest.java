package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathOperatorImplEndToEndTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testMathOperationsEndToEnd() {
        // Addition
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 5, operand2: 4, operation: +}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(9));

        // Division
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 10, operand2: 2, operation: /}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(5));

        // Subtraction
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 7, operand2: 3, operation: -}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(4));

        // Multiplication
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 3, operand2: 6, operation: *}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(200)
                .body("calcResponse", equalTo(18));

        // Unknown operation
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 8, operand2: 4, operation: unknown}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(500); // Assuming that an unknown operation returns a 500 status code

        // Invalid input (division by zero)
        given()
                .port(port)
                .contentType("application/json")
                .body("{operand1: 8, operand2: 0, operation: /}")
                .when()
                .post("/api/doMath")
                .then()
                .statusCode(500); // Assuming that an invalid input returns a 500 status code
    }
}
