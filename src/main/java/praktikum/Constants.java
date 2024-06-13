package praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Constants {

    public static final String CREATE_USER_ENDPOINT = "/api/v1/courier";
    public static final String LOGIN_ENDPOINT = "/api/v1/courier/login";
    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    public static final String DELETE_USER_ENDPOINT = "/api/v1/courier/";
    public static final String CREATE_ORDER = "/api/v1/orders";
    public static final String INSUFFICIENT_DATA_MESSAGE = "Недостаточно данных для создания учетной записи";
    public static final String LOGIN_ALREADY_USE_MESSAGE = "Этот логин уже используется. Попробуйте другой.";
    public static final String CREDETANTIALS_NOT_FOUND_MESSAGE = "Учетная запись не найдена";
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .log(LogDetail.ALL)
                    .addHeader("Content-Type", "application/json")
                    .setBaseUri("https://qa-scooter.praktikum-services.ru")
                    .build();
    public static final ResponseSpecification RESPONSE_SPECIFICATION =
            new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();
}
