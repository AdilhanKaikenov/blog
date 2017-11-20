package com.epam.adok.core.util.converter;

import com.epam.adok.core.util.converter.impl.StringToIntegerConverter;
import com.epam.adok.core.util.converter.impl.StringToLongConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Startup
public class StringConvertersHolder {

    private static final Logger log = LoggerFactory.getLogger(StringConvertersHolder.class);

    private static final Map<Type, StringConverter<?>> converters = new HashMap<>();

    public StringConvertersHolder() {
        log.info("Entering StringConvertersHolder() constructor...");
        converters.put(Integer.class, new StringToIntegerConverter());
        converters.put(Long.class, new StringToLongConverter());
        log.info("Converters size {}", converters.size());
    }

    public static StringConverter<?> getTheRightConverter(Type type) {
        log.info("Entering getTheRightConverter() method...");
        StringConverter<?> converter = converters.get(type);
        log.info("Leaving getTheRightConverter() method : {}", converter);
        return converter;
    }
}
