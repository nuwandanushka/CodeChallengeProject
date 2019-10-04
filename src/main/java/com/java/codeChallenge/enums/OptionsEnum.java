package com.java.codeChallenge.enums;

public class OptionsEnum {
    public enum MainOption {
        SEARCH("1"),
        VIEW("2"),
        QUIET("quiet"),
        ANOTHER_TERM("1"),
        CONTINUE_SEARCH("2");

        public String value;

        MainOption(String s) {
            this.value = s;
        }
    }

    public enum SearchOptions {
        USERS("1"),
        TICKETS("2"),
        ORGANIZATIONS("3");

        public String value;

        SearchOptions(String s) {
            this.value = s;
        }
    }
}
