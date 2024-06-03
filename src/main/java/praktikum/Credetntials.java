package praktikum;

import lombok.Data;

@Data
public class Credetntials {
    private final String login;
    private final String password;

    public static Credetntials fromCourier(Courier courier) {
        return new Credetntials(courier.getLogin(), courier.getPassword());
    }

}