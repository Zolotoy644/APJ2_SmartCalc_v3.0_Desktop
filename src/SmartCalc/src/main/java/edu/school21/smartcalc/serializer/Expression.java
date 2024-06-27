package edu.school21.smartcalc.serializer;

import java.io.Serializable;

public class Expression implements Serializable {
    private final String expressionText;

    private final Double xValue;

    public Expression(String expressionText, Double xValue) {
        this.expressionText = expressionText;
        this.xValue = xValue;
    }

    public Double getXValue() {
        return xValue;
    }

    public String getExpressionText() {
        return expressionText;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "text: " + expressionText +
                ", x value: " + xValue.toString() +
                "}";
    }
}