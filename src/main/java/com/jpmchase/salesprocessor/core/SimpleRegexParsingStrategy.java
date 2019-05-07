package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.domain.Message;
import com.jpmchase.salesprocessor.domain.MessageTypeMatcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegexParsingStrategy implements MessageParsingStrategy {

    private List<Message> salesMessages;

    @Override
    public List<Message> parse(List<String> txtNotifications) {
        salesMessages = new ArrayList<>();

        txtNotifications.forEach(txtNotification -> {

            for (MessageTypeMatcher mtm : MessageTypeMatcher.values()) {
                Pattern msgPattern = Pattern.compile(mtm.getRegexPattern());
                Matcher matcher = msgPattern.matcher(Objects.requireNonNull(txtNotification,"Notifications must not be null"));

                if (matcher.matches()) {
                    createMessageByType(matcher, mtm.name());
                    break;
                }
            }
        });
        return salesMessages;
    }


    private void createMessageByType(Matcher msgTypeMatcher, String msgType) {

        Message message;

        switch (msgType.toLowerCase()) {
            case "type1":
                message = buildMessageForParameters(msgType, msgTypeMatcher.group(1), msgTypeMatcher.group(2), "", "");
                salesMessages.add(message);
                break;

            case "type2":
                message = buildMessageForParameters(msgType,msgTypeMatcher.group(2),msgTypeMatcher.group(3),msgTypeMatcher.group(1),"");
                salesMessages.add(message);
                break;

            case "type3":
                message = buildMessageForParameters(msgType,msgTypeMatcher.group(3),msgTypeMatcher.group(2),"",msgTypeMatcher.group(1));
                salesMessages.add(message);
                break;

        }
    }

    private Message buildMessageForParameters(String msgType, String productType, String salesValue, String occurrence, String saleOp) {

        Message.Builder builder = Message.builder()
                                         .msgType(msgType.toLowerCase())
                                         .productType(productType)
                                         .salesValue(new BigDecimal(salesValue));

        if (occurrence != null && !occurrence.isEmpty())
            builder.occurence(Integer.parseInt(occurrence));

        if (saleOp != null && !saleOp.isEmpty())
            builder.saleOperation(saleOp.toLowerCase());

        return builder.build();

    }


}
