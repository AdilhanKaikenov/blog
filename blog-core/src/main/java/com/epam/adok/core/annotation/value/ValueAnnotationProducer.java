package com.epam.adok.core.annotation.value;

import com.epam.adok.core.exception.PropertyManagerException;
import com.epam.adok.core.propmanager.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class ValueAnnotationProducer {

    private static final Logger log = LoggerFactory.getLogger(ValueAnnotationProducer.class);

    @Produces
    @Value
    public String produceValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        log.info("Entering produceValue() method.");

        Value annotation = injectionPoint.getAnnotated().getAnnotation(Value.class);

        String propertiesSource = annotation.propertiesSource();
        String key = annotation.key();
        PropertiesManager manager = new PropertiesManager(propertiesSource);

        String value = manager.get(key);

        if (value == null) {
            log.info("Key {} in {} file was not found.", key, propertiesSource);
        }
        log.info("Leaving produceValue() method. Value : {}", value);
        return value;
    }
}
