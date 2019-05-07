package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.domain.Message;

import java.util.List;

public class ParsingStrategyContext {

    private MessageParsingStrategy parsingStrategy;

    public void setParsingStrategy(MessageParsingStrategy parsingStrategy) {
        this.parsingStrategy = parsingStrategy;
    }

    public List<Message> parseNotifications(List<String> notifications) {
        return parsingStrategy.parse(notifications);
    }

}
