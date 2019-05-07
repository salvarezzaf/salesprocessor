package com.jpmchase.salesprocessor.input;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileMessageReaderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void read_ValidInputFile_ReadLinesIntoListCorrectly() {
        MessageReader messageReader = new FileMessageReader("test-sales-notifications.txt");
        List salesNotfications = messageReader.read();
        assertThat(salesNotfications.size(), equalTo(3));
        assertThat(salesNotfications.get(0), equalTo("apple at 10p"));
        assertThat(salesNotfications.get(1), equalTo("20 sales of apples at 10p each"));
        assertThat(salesNotfications.get(2), equalTo("Add 20p apples"));
    }

    @Test
    public void read_InputFileNotExists_ExceptionThrown() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("A valid input file must exist");
        MessageReader messageReader = new FileMessageReader("file-not-exists.txt");
        messageReader.read();
    }


}
