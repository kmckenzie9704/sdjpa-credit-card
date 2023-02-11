package guru.springframework.creditcard.services;

import org.springframework.stereotype.Service;

public interface EncryptionService {

    public String encrypt(String freeText);

    public String decrypt(String encodedText);
}
