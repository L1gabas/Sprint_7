package praktikum;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OrderSteps {

    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    public OrderSteps(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        this.responseSpecification = responseSpecification;
        this.requestSpecification = requestSpecification;
    }

    @Step("Get orders")
    public ValidatableResponse getOrders() {
        return given()
                .spec(requestSpecification)
                .get(Constants.CREATE_ORDER)
                .then()
                .spec(responseSpecification);
    }

    @Step("getOrders body contains list - status 200, list notNullValue")
    public void checkOrderListNotNull(ValidatableResponse response) {
        response
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("orders", notNullValue());
    }

}