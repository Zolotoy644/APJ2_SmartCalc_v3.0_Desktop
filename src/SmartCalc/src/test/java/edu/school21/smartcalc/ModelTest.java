package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    @Test
    public void testMainCalculation() {
        String expression = "3*x+2";
        double xValue = 5.0;
        double expectedResult = 17.0;

        double result = PolishNotationModel.mainCalculation(expression, xValue);

        assertEquals(expectedResult, result, 0.01); // Указываем допустимое отклонение
    }

    @Test
    public void testMainCalculationSecond() {
        String expression = "sin(x)*cos(x)";
        double xValue = 5.0;
        double expectedResult = -0.272010591;

        double result = PolishNotationModel.mainCalculation(expression, xValue);

        assertEquals(expectedResult, result, 0.0000001); // Указываем допустимое отклонение
    }

    @Test
    public void testCalculationCredit() {

        PolishNotationModel.CreditParam param = new PolishNotationModel.CreditParam();
        param.type_payment(0);
        param.credit_sum(1000000);
        param.credit_time(60);
        param.percent(10);

        PolishNotationModel.CreditResult result = PolishNotationModel.calculateCredit(param);

        double expectedFist = 21247.04;
        double expectedLast = 21247.04;
        double expectedOverpayment = 274822.68;
        double expectedTotal = 1274822.68;

        assertEquals(expectedFist, result.first_payment(), 0.01);
        assertEquals(expectedLast, result.last_payment(), 0.01);
        assertEquals(expectedOverpayment, result.overpayment(), 0.01);
        assertEquals(expectedTotal, result.total_payment(), 0.01);
    }

    @Test
    public void testCalculationDeposit() {

        PolishNotationModel.DepositParam param = new PolishNotationModel.DepositParam();
        param.deposit_type(0);
        param.deposit_sum(1000000);
        param.deposit_time(60);
        param.percent(10);
        param.start_date(6);
        param.start_year(2024);

        PolishNotationModel.DepositResult result = PolishNotationModel.calculateDeposit(param);

        double expectedDeposit = 1500273.97;
        double expectedPercent = 500273.97;


        assertEquals(expectedDeposit, result.total_deposit_sum(), 0.01);
        assertEquals(expectedPercent, result.total_percent_sum(), 0.01);

    }

    @Test
    public void testIsOperator() {
        assertTrue(PolishNotationModel.isOperator('+'));
        assertTrue(PolishNotationModel.isOperator('-'));
        assertTrue(PolishNotationModel.isOperator('*'));
        assertTrue(PolishNotationModel.isOperator('/'));
        assertFalse(PolishNotationModel.isOperator('9'));
    }

    @Test
    public void testGetPrecedence() {
        assertEquals(2, PolishNotationModel.getPrecedence('*'));
        assertEquals(2, PolishNotationModel.getPrecedence('/'));
        assertEquals(1, PolishNotationModel.getPrecedence('+'));
        assertEquals(1, PolishNotationModel.getPrecedence('-'));
        assertEquals(0, PolishNotationModel.getPrecedence('9'));
    }

    @Test
    public void testIsDigit() {
        assertTrue(PolishNotationModel.isDigit('5'));
        assertTrue(PolishNotationModel.isDigit('9'));
        assertFalse(PolishNotationModel.isDigit('+'));
    }
}
