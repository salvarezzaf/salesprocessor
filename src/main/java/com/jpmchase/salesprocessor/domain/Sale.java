package com.jpmchase.salesprocessor.domain;

import java.math.BigDecimal;

public class Sale {

    private String productType;
    private BigDecimal value;
    private int salesCounter;

    public Sale(String productType, BigDecimal value, int salesCounter) {
        this.productType = productType;
        this.value = value;
        this.salesCounter = salesCounter;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getSalesCounter() {
        return salesCounter;
    }

    public void setSalesCounter(int salesCounter) {
        this.salesCounter = salesCounter;
    }
}
