package com.jpmchase.salesprocessor.domain;

public enum MessageTypeMatcher {

    TYPE1("(\\w+) at (\\d+)p"), TYPE2("(\\d+) sales of (\\w+) at (\\d+)p each"), TYPE3("(\\w+) (\\d+)p (\\w+)");

    private String regExPattern;

    MessageTypeMatcher(String regex) {
        this.regExPattern = regex;
    }

    public String getRegexPattern() {
        return this.regExPattern;
    }
}
