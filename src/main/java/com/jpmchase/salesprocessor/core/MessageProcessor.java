package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.input.MessageReader;

public interface MessageProcessor {

    void process (MessageReader msgReader, ParsingStrategyContext parsingCtx);

}
