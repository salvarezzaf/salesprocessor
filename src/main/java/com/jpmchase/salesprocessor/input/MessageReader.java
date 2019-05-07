package com.jpmchase.salesprocessor.input;

import java.util.List;

public interface MessageReader<T> {

    List<T> read();

}
