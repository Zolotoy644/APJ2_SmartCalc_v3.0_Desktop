package edu.school21.smartcalc.model;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include= "PolishNotation.h")
@Namespace("s21")
public class PolishNotationModel extends Pointer {
    static { Loader.load(); }

    public PolishNotationModel() { allocate(); }
    private native void allocate();

    public static class CreditParam extends Pointer {
        static { Loader.load(); }
        public CreditParam() { allocate(); }
        private native void allocate();
        public native @Cast("double") double credit_sum();
        public native CreditParam credit_sum(double setter);
        public native @Cast("double") double credit_time();
        public native CreditParam credit_time(double setter);
        public native @Cast("double") double percent();
        public native CreditParam percent(double setter);
        public native int type_payment();
        public native CreditParam type_payment(int setter);
    }

    public static class CreditResult extends Pointer {
        static { Loader.load(); }
        public CreditResult() { allocate(); }
        private native void allocate();
        public native @Cast("double") double first_payment();
        public native CreditResult first_payment(double setter);
        public native @Cast("double") double last_payment();
        public native CreditResult last_payment(double setter);
        public native @Cast("double") double overpayment();
        public native CreditResult overpayment(double setter);
        public native @Cast("double") double total_payment();
        public native CreditResult total_payment(double setter);
    }

    public static class DepositParam extends Pointer {
        static { Loader.load(); }
        public DepositParam() { allocate(); }
        private native void allocate();
        public native @Cast("double") double deposit_sum();
        public native DepositParam deposit_sum(double setter);
        public native @Cast("double") double deposit_time();
        public native DepositParam deposit_time(double setter);
        public native @Cast("double") double percent();
        public native DepositParam percent(double setter);
        public native int deposit_type();
        public native DepositParam deposit_type(int setter);
        public native int start_date();
        public native DepositParam start_date(int setter);
        public native int start_year();
        public native DepositParam start_year(int setter);
    }

    public static class DepositResult extends Pointer {
        static { Loader.load(); }
        public DepositResult() { allocate(); }
        private native void allocate();
        public native @Cast("double") double total_percent_sum();
        public native DepositResult total_percent_sum(double setter);
        public native @Cast("double") double total_deposit_sum();
        public native DepositResult total_deposit_sum(double setter);
    }

    public static native @Cast("bool") boolean isOperator(char c);
    public static native int getPrecedence(char op);
    public static native @Cast("bool") boolean isDigit(char c);
    public static native @Cast("bool") boolean isVariable(char c);
    public static native @ByVal StringQueue infixToPostfix(@StdString @ByRef String infixExpression);
    public static native @Cast("double") double evaluatePostfix(@ByRef StringQueue postfixExpression, @Cast("double") double x);
    public static native @StdString String convertToCalculationFormat(@Const @ByRef @StdString String expression);
    public static native @Cast("double") double mainCalculation(@Const @ByRef @StdString String expression, double xValue);
    public static native @ByVal DepositResult calculateDeposit(@ByVal DepositParam param);
    public static native @ByVal CreditResult calculateCredit(@ByVal CreditParam param);
}