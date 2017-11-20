package com.epam.adok.core.annotation.value;

import com.epam.adok.core.exception.PropertyManagerException;
import com.epam.adok.core.propmanager.PropertiesManager;
import com.epam.adok.core.util.converter.StringConverter;
import com.epam.adok.core.util.converter.StringConvertersHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.reflect.Type;

@ApplicationScoped
public class ValueAnnotationProducer {

    private static final Logger log = LoggerFactory.getLogger(ValueAnnotationProducer.class);

    @Produces
    @Value
    public Integer produceIntegerValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        return (Integer) produceValue(injectionPoint);
    }

    @Produces
    @Value
    public Long produceLongValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        return (Long) produceValue(injectionPoint);
    }

    @Produces
    @Value
    public String produceStringValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        return getStringValue(injectionPoint);
    }

    private Object produceValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        log.info("Entering produceValue() method.");

        String value = getStringValue(injectionPoint);

        Type type = injectionPoint.getType();
        log.info("Injection type : {}", type);

        StringConverter<?> converter = StringConvertersHolder.getTheRightConverter(type);

        Object convertedValue = converter.convert(value);

        log.info("Converted Value : {}", convertedValue);

        log.info("Leaving produceValue() method. Value : {}", value);
        return convertedValue;
    }

    private String getStringValue(InjectionPoint injectionPoint) throws PropertyManagerException {
        log.info("Entering getStringValue() method.");

        Value annotation = injectionPoint.getAnnotated().getAnnotation(Value.class);

        String propertiesSource = annotation.propertiesSource();
        String key = annotation.key();
        PropertiesManager manager = new PropertiesManager(propertiesSource);

        String value = manager.get(key);

        if (value == null) {
            log.info("Key {} in {} file was not found.", key, propertiesSource);
            return null;
        }
        return value;
    }
}
