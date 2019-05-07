package com.jpmchase.salesprocessor.core;

import java.util.List;

public interface MessageParsingStrategy<M> {

  List<M> parse();

}
