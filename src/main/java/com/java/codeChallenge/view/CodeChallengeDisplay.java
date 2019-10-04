package com.java.codeChallenge.view;

import java.util.Optional;

public class CodeChallengeDisplay {

    public void display(ICodeChallengeOutput codeChallengeOutput){
        Optional.ofNullable(codeChallengeOutput).ifPresent(output -> output.create());
    }
}
