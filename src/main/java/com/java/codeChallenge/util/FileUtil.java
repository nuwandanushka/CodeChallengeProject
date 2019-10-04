package com.java.codeChallenge.util;

import com.java.codeChallenge.enums.ErrorCodeEnum;
import com.java.codeChallenge.exception.CodeChallengeException;

import java.io.File;
import java.net.URL;

public class FileUtil {

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new CodeChallengeException(ErrorCodeEnum.FILE_NOT_FOUND);
        } else {
            return new File(resource.getFile().replaceAll("%20"," "));
        }

    }

}
