package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.domain.Message;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleRegexParsingStartegyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private SimpleRegexParsingStrategy regexParsingStrategy;

    @Before
    public void setup() {
        regexParsingStrategy = new SimpleRegexParsingStrategy();
    }

    @Test
    public void parse_WithTypeOneTxtNotifications_ReturnsTwoValidTypeOneMessages() {
        List<Message> messages = regexParsingStrategy.parse(Arrays.asList("banana at 10p", "apple at 20p"));
        assertThat(messages.size(), equalTo(2));
        Message msg1 = messages.get(0);
        Message msg2 = messages.get(1);

        assertThat(msg1.getMsgType(), equalTo("type1"));
        assertThat(msg2.getMsgType(), equalTo("type1"));
        assertThat(msg1.getProductType(), equalTo("banana"));
        assertThat(msg2.getProductType(), equalTo("apple"));
        assertThat(msg1.getSaleValue(), equalTo(new BigDecimal(10)));
        assertThat(msg2.getSaleValue(), equalTo(new BigDecimal(20)));

    }

    @Test
    public void parse_WithType2TxtNotifications_ReturnsTwoValidType2Messages() {
        List<Message> messages = regexParsingStrategy.parse(Arrays.asList("20 sales of bananas at 10p each", "2 sales of pineapples at 50p each"));
        assertThat(messages.size(), equalTo(2));
        Message msg1 = messages.get(0);
        Message msg2 = messages.get(1);

        assertThat(msg1.getMsgType(), equalTo("type2"));
        assertThat(msg2.getMsgType(), equalTo("type2"));
        assertThat(msg1.getProductType(), equalTo("bananas"));
        assertThat(msg2.getProductType(), equalTo("pineapples"));
        assertThat(msg1.getSaleValue(), equalTo(new BigDecimal(10)));
        assertThat(msg2.getSaleValue(), equalTo(new BigDecimal(50)));
        assertThat(msg1.getOccurrence(), equalTo(Optional.of(20)));
        assertThat(msg2.getOccurrence(), equalTo(Optional.of(2)));

    }

    @Test
    public void parse_WithType3TxtNotifications_ReturnsTwoValidType3Messages() {
        List<Message> messages = regexParsingStrategy.parse(Arrays.asList("Add 30p strawberries", "Multiply 40p bananas"));
        assertThat(messages.size(), equalTo(2));
        Message msg1 = messages.get(0);
        Message msg2 = messages.get(1);

        assertThat(msg1.getMsgType(), equalTo("type3"));
        assertThat(msg2.getMsgType(), equalTo("type3"));
        assertThat(msg1.getProductType(), equalTo("strawberries"));
        assertThat(msg2.getProductType(), equalTo("bananas"));
        assertThat(msg1.getSaleValue(), equalTo(new BigDecimal(30)));
        assertThat(msg2.getSaleValue(), equalTo(new BigDecimal(40)));
        assertThat(msg1.getOccurrence().isPresent(), is(false));
        assertThat(msg2.getOccurrence().isPresent(), is(false));
        assertThat(msg1.getSaleOperation(), equalTo(Optional.of("add")));
        assertThat(msg2.getSaleOperation(), equalTo(Optional.of("multiply")));

    }

    @Test
    public void parse_WithTxtOfInvalidRegexPattern_ReturnsEmptyListOfMessages() {

        List<Message> messages = regexParsingStrategy.parse(Arrays.asList("30p of apples", "20 sales of bananas", " multiply bananas by 10p"));
        assertTrue(messages.isEmpty());
    }

    @Test
    public void parse_WithNullTxtNofications_BreaksPreCondition() {

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Notifications must not be null");

        regexParsingStrategy.parse(Arrays.asList("30p of apples", "20 sales of bananas", " multiply bananas by 10p", null));
    }


}
