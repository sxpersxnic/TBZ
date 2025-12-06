package ch.tbz.m450.testing.tools.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class APITest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldReturnStartupStudents() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", is(5))
                .body("[0].name", is("Jonas"))
                .body("[1].name", is("Patrick"))
                .body("[2].name", is("Yves"))
                .body("[3].name", is("Peter"))
                .body("[4].name", is("Ann"));
    }

    @Test
    void shouldAddNewStudent() {
        var requestBody = """
                {
                  "name": "Charlie",
                  "email": "charlie@tbz.ch"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/students")
                .then()
                .statusCode(200);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .body("$.size()", is(6))
                .body("[5].name", is("Charlie"))
                .body("[5].email", is("charlie@tbz.ch"));
    }
}