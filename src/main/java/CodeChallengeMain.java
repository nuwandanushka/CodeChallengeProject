import com.java.codeChallenge.storage.JsonObjectStorage;
import com.java.codeChallenge.view.CodeChallengeDisplay;
import com.java.codeChallenge.view.ConsoleOutput;

import java.util.HashMap;
import java.util.HashSet;

public class CodeChallengeMain {
    public static void  main (String[] args ){
        new CodeChallengeDisplay().display(new ConsoleOutput());
    }
}
