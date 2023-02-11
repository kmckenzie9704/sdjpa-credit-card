package guru.springframework.creditcard.domain;

import guru.springframework.creditcard.config.SpringContextHelper;
import guru.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.*;

public class CreditCardJPACallback {


    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard){

        System.out.println("before update was called....");

        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void postLoad(CreditCard creditCard){

        System.out.println("postLoad was called....");
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));

    }

    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
