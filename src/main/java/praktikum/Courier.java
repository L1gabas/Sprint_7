package praktikum;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;


@Data
public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public static Courier createRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public static Courier createRandomWithoutPass() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, null, firstName);
    }

}