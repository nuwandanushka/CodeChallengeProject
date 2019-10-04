package com.java.codeChallenge.template;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface OutputTemplate {

    void createOutput(Collection<? extends Map> valueMap);
}
