package edu.school21.smartcalc.serializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionHistory implements Serializable {
    private final List<Expression> expressions;

    public ExpressionHistory(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public ExpressionHistory() {
        this.expressions = new ArrayList<>();
    }

    public void addData(Expression newExpression) {
        expressions.add(newExpression);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void clear() {
        expressions.clear();
    }

    @Override
    public String toString() {
        return "History{" +
                "expressions: " + Arrays.toString(expressions.toArray()) +
                "}";
    }
}
