package com.epam.adok.core.util.converter.impl;

import com.epam.adok.core.util.converter.StringConverter;

public class StringToLongConverter implements StringConverter<Long> {

    @Override
    public Long convert(String source) {
        return Long.parseLong(source);
    }
}
