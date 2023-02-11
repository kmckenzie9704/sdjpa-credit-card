package guru.springframework.creditcard.config;

import guru.springframework.creditcard.listener.PostLoadListener;
import guru.springframework.creditcard.listener.PreInsertListener;
import guru.springframework.creditcard.listener.PreUpdateListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.EventListener;

//@Component
public class ListenerRegistration implements BeanPostProcessor {

    private final PreInsertListener preInsertListener;

    private final PostLoadListener postLoadListener;

    private final PreUpdateListener preUpdateListener;

    public ListenerRegistration(PreInsertListener preInsertListener, PostLoadListener postLoadListener, PreUpdateListener preUpdateListener) {
        this.preInsertListener = preInsertListener;
        this.postLoadListener = postLoadListener;
        this.preUpdateListener = preUpdateListener;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

//        if (bean instanceof LocalContainerEntityManagerFactoryBean){
//            LocalContainerEntityManagerFactoryBean lcemf = (LocalContainerEntityManagerFactoryBean) bean;
//            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lcemf.getNativeEntityManagerFactory();
//            EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
//
//            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
//            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
//            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
//        }

        return bean;
    }
}
