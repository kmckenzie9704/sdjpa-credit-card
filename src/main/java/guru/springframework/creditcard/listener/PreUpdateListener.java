package guru.springframework.creditcard.listener;

import guru.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.PreUpdate;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {

    public PreUpdateListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {

        System.out.println("In PreUpdateListener");
        this.encrypt(preUpdateEvent.getState(), preUpdateEvent.getPersister().getPropertyNames(), preUpdateEvent.getEntity());

        return false;
    }
}
