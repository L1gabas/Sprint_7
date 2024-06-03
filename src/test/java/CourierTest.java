import praktikum.*;
import praktikum.Courier;
import praktikum.Credetntials;
import praktikum.ScooterServiceClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static praktikum.Constants.REQUEST_SPECIFICATION;
import static praktikum.Constants.RESPONSE_SPECIFICATION;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierTest {

    private Courier courier;
    private ScooterServiceClient client;
    private int id;

    @Before
    public void createTestData(){
        client = new ScooterServiceClient(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @After
    @Test
    @Description("Delete courier - status 201, Body:\"ok\" = true")
    public void deleteCourier(){
        courier = Courier.createRandom();
        client.create(courier);
        ValidatableResponse loginResponse = client.login(Credetntials.fromCourier(courier));
        id = loginResponse.extract().path("id");
        ValidatableResponse responseDelete = client.delete(id);
        responseDelete.assertThat().statusCode(SC_OK).and().body("ok", equalTo(true));
    }

    @Test
    @Description("Create courier (successful) - status 201, Body:\"ok\" = true")
    public void createCourierSuccessTest() {
        courier = Courier.createRandom();
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(SC_CREATED).and().body("ok", equalTo(true));
    }

    @Test
    @Description("Create courier without password - status 400")
    public void createCourierWithoutPassTest() {
        courier = Courier.createRandomWithoutPass();
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(SC_BAD_REQUEST);
        response.assertThat().body("message", Matchers.equalTo(Constants.INSUFFICIENT_DATA_MESSAGE));
    }

    @Test
    @Description("Create courier_is already in DB - status 409")
    public void createCourierAlreadyUse() {
        courier = new Courier("Andrew13","Gg1234567890","AVv");
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(SC_CONFLICT);
        response.assertThat().body("message", Matchers.equalTo(Constants.LOGIN_ALREADY_USE_MESSAGE));
    }

}