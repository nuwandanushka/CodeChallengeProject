package com.java.codeChallenge.template;
import java.util.Map;
import java.util.Set;

public interface ConsoleOutputTemplate<T extends OutputTemplate> {

    default void displayResults(Set<? extends Map> valueMap){
        getTemplate().createOutput(valueMap);
    }

    public T getTemplate();
}
