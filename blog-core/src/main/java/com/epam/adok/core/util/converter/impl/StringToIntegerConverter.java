package com.epam.adok.core.util.converter.impl;

import com.epam.adok.core.util.converter.StringConverter;

public class StringToIntegerConverter implements StringConverter<Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.parseInt(source);
    }
}
