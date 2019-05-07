package com.jpmchase.salesprocessor.domain;

import java.math.BigDecimal;

public class AdjustmentOp {

    private String operation;
    private BigDecimal opValue;
    private String productType;
    private BigDecimal previousTotal;
    private BigDecimal newTotal;

    public AdjustmentOp(String operation, BigDecimal opValue, String productType, BigDecimal previousTotal, BigDecimal newTotal) {
        this.operation = operation;
        this.opValue = opValue;
        this.productType = productType;
        this.previousTotal = previousTotal;
        this.newTotal = newTotal;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getOpValue() {
        return opValue;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getPreviousTotal() {
        return previousTotal;
    }

    public BigDecimal getNewTotal() {
        return newTotal;
    }
}
