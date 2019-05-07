package com.jpmchase.salesprocessor.domain;

import java.math.BigDecimal;
import java.util.Optional;

public final class Message {


    private final String msgType;
    private final String productType;
    private final BigDecimal saleValue;
    private final Optional<Integer> occurrence;
    private final Optional<String> saleOperation;

    public static class Builder {

        private String msgType;
        private String productType;
        private BigDecimal saleValue;
        private Optional<Integer> occurrence = Optional.empty();
        private Optional<String> saleOperation = Optional.empty();

        private Builder(){}

        public Builder msgType(String msgType) {
            this.msgType = msgType;
            return this;
        }

        public Builder productType(String productType) {
            this.productType = productType;
            return this;
        }

        public Builder salesValue(BigDecimal saleValue) {
            this.saleValue = saleValue;
            return this;
        }

        public Builder occurence(int occurrence) {
            this.occurrence = Optional.of(occurrence);
            return this;
        }

        public Builder saleOperation(String operation) {
            this.saleOperation = Optional.of(operation);
            return this;
        }


        public Message build() {

            if (!isValidInstance())
                throw new IllegalArgumentException("Some mandatory fields on instruction were not set to correct values");

            return new Message(this);
        }

        boolean isValidInstance() {
            return (msgType != null && productType != null && saleValue!=null);
        }
    }


    private Message (Builder builder) {

        msgType = builder.msgType;
        productType = builder.productType;
        saleValue = builder.saleValue;
        occurrence = builder.occurrence;
        saleOperation = builder.saleOperation;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public Optional<Integer> getOccurrence() {
        return occurrence;
    }

    public Optional<String> getSaleOperation() {
        return saleOperation;
    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public String toString() {

        return "MsgType: " + this.msgType + ", ProductType: " + this.productType +
                ", SaleValue: " + this.saleValue + ", Occurrence: " + this.occurrence +
                ", SaleOperation: " + this.saleOperation;
    }
}
