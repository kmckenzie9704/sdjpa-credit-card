package guru.springframework.creditcard.listener;

import guru.springframework.creditcard.services.EncryptionService;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {


    public PostLoadListener(EncryptionService encryptionService) {
        super(encryptionService);
    }

    @Override
    public void onPostLoad(PostLoadEvent postLoadEvent) {

        System.out.println("In PostLoadEvent");

        this.decrypt(postLoadEvent.getEntity());

    }
}
