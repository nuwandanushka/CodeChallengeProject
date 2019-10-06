package com.java.codeChallenge.template;

import com.java.codeChallenge.exception.CodeChallengeException;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.java.codeChallenge.enums.ErrorCodeEnum.FILE_NOT_FOUND;

public interface ConsoleOutputTemplate {

    default void displayResults(Collection<? extends Map> valueMap){
        Optional.ofNullable(valueMap).orElseThrow(() -> new CodeChallengeException(FILE_NOT_FOUND));
        if(valueMap.isEmpty()) System.out.println(getEmptyMessage());
        getTemplate().createOutput(valueMap);
    }

    default String getEmptyMessage(){
        return "No results found";
    }

    OutputTemplate getTemplate();
}
