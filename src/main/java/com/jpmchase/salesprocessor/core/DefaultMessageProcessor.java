package com.jpmchase.salesprocessor.core;

import com.jpmchase.salesprocessor.domain.AdjustmentOp;
import com.jpmchase.salesprocessor.domain.Message;
import com.jpmchase.salesprocessor.domain.Sale;
import com.jpmchase.salesprocessor.input.MessageReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMessageProcessor implements MessageProcessor {

    private Map<String, Sale> salesRegistry;
    private Map<String, AdjustmentOp> adjustmentsRegistry;
    private int maxMsgBufferSize;
    private int interimReportSize;

    public DefaultMessageProcessor(int maxMsgBufferSize, int interimReportSize) {
        this.maxMsgBufferSize = maxMsgBufferSize;
        this.interimReportSize = interimReportSize;
        this.salesRegistry = new HashMap<>();
        this.adjustmentsRegistry = new HashMap<>();
    }

    @Override
    public void process(MessageReader msgReader, ParsingStrategyContext parsingCtx) {
        int reportCounter = 0;
        int totalMsgCounter = 0;
        List<String> txtNotifications = msgReader.read();
        List<Message> messages = parsingCtx.parseNotifications(txtNotifications);

        for(Message msg : messages) {

            if(totalMsgCounter<=maxMsgBufferSize) {
                if (reportCounter==interimReportSize) {
                    //print interim report with writer
                    System.out.println("Print Interim report");
                    reportCounter = 0;
                }






                reportCounter++;
                totalMsgCounter++;

            } else {
                System.out.println("Message " + msg.getMsgType() + " product " + msg.getProductType() + " rejected.... Max message buffer size exceeded");
            }




        }




    }
}
