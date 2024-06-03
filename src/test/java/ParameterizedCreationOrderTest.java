import praktikum.Order;
import praktikum.OrderSteps;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static praktikum.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.apache.http.HttpStatus.*;

@RunWith(Parameterized.class)
public class ParameterizedCreationOrderTest {
    OrderSteps orderSteps;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String colorForHeader;
    private final String[] color;

    public ParameterizedCreationOrderTest(String firstName,
                                          String lastName,
                                          String address,
                                          int metroStation,
                                          String phone,
                                          int rentTime,
                                          String deliveryDate,
                                          String comment,
                                          String colorForHeader,
                                          String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colorForHeader = colorForHeader;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Test {index} Цвет самоката: {8}")
    public static Object[][] getTestData() {

        return new Object[][]{
                {"Boris", "Barbaris", "Garden, 11 apt.", 4, "+7 900 000 13 13", 5, "2024-04-06", "Boris do u have barbaris?", "Черный", new String[]{"BLACK"}},
                {"Igor", "Knigor", "Library, 12 apt.", 4, "+7 913 600 31 31", 5, "2024-07-06", "Igor have book", "Серый", new String[]{"GREY"}},
                {"But", "Bol", "Ya, 13 apt.", 4, "+7 950 500 54 54", 4, "2024-05-07", "bolno silno", "Серый и Черный", new String[]{"GREY", "BLACK"}},
                {"Giga", "Chad", "Memes, 14 apt.", 4, "+7 951 400 98 98", 3, "2024-06-07", "cheekbones of god", "Цвет не выбран", new String[]{""}}
        };
    }

    @Before
    public void setUp() {
        orderSteps = new OrderSteps(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @Test
    @Description("Create order")
    public void creationOrders(){
        Order order = new Order(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                colorForHeader,
                color);

        given()
                .spec(REQUEST_SPECIFICATION)
                .body(order)
                .post(CREATE_ORDER_ENDPOINT)
                .then()
                .assertThat()
                .body("track", notNullValue())
                .statusCode(SC_CREATED);
    }

}