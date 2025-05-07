package abopijservice.code.bankcardmanagement.card;

import net.datafaker.Faker;

public class CreateVisaNumberService {

    public static String create() {
        Faker faker = new Faker();
        return  faker.finance().creditCard();
    }
}
