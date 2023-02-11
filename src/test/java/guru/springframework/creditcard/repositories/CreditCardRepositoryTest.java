package guru.springframework.creditcard.repositories;

import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String CREDIT_CARD_NUMBER = "12345678900000";

    @Test
    public void testSaveAndStoreCreditCard(){


        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("2341");

        CreditCard savedCard = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("Getting saved CC " + savedCard.getCreditCardNumber());

        System.out.println("Real CC: " + CREDIT_CARD_NUMBER);

        System.out.println("Encrypted CC: " + encryptionService.encrypt(CREDIT_CARD_NUMBER));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("select * from credit_card where id = " + savedCard.getId());

        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertThat(savedCard.getCreditCardNumber()).isNotEqualTo(dbCardValue);
        assertThat(dbCardValue).isEqualTo(encryptionService.encrypt(CREDIT_CARD_NUMBER));

        CreditCard fetchedCard = creditCardRepository.findById(savedCard.getId()).get();

        System.out.println("Getting fetched CC " + fetchedCard.getCreditCardNumber());

        assertThat(savedCard.getCreditCardNumber()).isEqualTo(fetchedCard.getCreditCardNumber());

    }
}