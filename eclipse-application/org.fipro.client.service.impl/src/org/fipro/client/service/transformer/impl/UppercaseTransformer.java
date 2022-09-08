package org.fipro.client.service.transformer.impl;

import java.util.Locale;

import org.fipro.service.transformer.StringTransformer;
import org.osgi.service.component.annotations.Component;

@Component
public class UppercaseTransformer implements StringTransformer {

    @Override
    public String transform(String input) {
        return (input != null)
        	? input.toUpperCase(Locale.getDefault()) + " (offline) "
        	: "No input given";
    }
}