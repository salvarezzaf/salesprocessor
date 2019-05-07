package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.domain.Message;

import java.util.List;

public interface MessageParsingStrategy {

  List<Message> parse(List<String> notifications);

}
