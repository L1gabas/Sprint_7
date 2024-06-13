import praktikum.OrderSteps;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static praktikum.Constants.REQUEST_SPECIFICATION;
import static praktikum.Constants.RESPONSE_SPECIFICATION;

public class OrderTest {
    OrderSteps orderSteps;

    @Before
    public void createTestData() {
        orderSteps = new OrderSteps(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @Test
    @Description("Get list order  - status 200, Body:\"orders\", notNull")
    public void getOrderListNotNull() {
        ValidatableResponse response = orderSteps.getOrders();
        orderSteps.checkOrderListNotNull(response);
    }

}