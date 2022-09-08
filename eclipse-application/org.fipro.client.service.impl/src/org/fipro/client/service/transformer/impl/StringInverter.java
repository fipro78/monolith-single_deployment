package org.fipro.client.service.transformer.impl;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Component
public class StringInverter implements StringTransformer {

    @Override
    public String transform(String input) {
        return (input != null) 
        	? new StringBuilder(input).reverse().append(" (offline) ").toString()
        	: "No input given";
    }
}