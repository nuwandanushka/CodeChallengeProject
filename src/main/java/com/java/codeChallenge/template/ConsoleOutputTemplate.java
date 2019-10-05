package com.java.codeChallenge.template;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ConsoleOutputTemplate {

    default void displayResults(Collection<? extends Map> valueMap){
        if(valueMap.isEmpty()) System.out.println("No results found");
        getTemplate().createOutput(valueMap);
    }

    OutputTemplate getTemplate();
}
