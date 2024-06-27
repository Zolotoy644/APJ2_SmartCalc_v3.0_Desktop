package edu.school21.smartcalc.presenter;

import edu.school21.smartcalc.*;
import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.serializer.Expression;
import edu.school21.smartcalc.serializer.ExpressionHistory;
import edu.school21.smartcalc.serializer.HistorySerializer;
import javafx.scene.chart.XYChart;

import java.io.File;

import static edu.school21.smartcalc.model.PolishNotationModel.mainCalculation;

public class CalcPresenter {
    private CalcView view;
    private CreditView viewCredit;
    private DepositView viewDeposit;
    private GraphView viewGraph;
    private PolishNotationModel polishNotationModel;
    private HistoryView historyView;
    private HistorySerializer serializer;
    private ExpressionHistory history;

    public CalcPresenter(CalcView view, CreditView viewCredit, DepositView viewDeposit, GraphView viewGraph, HistoryView historyView, PolishNotationModel polishNotationModel) {
        this.view = view;
        this.viewCredit = viewCredit;
        this.polishNotationModel = polishNotationModel;
        this.viewDeposit = viewDeposit;
        this.viewGraph = viewGraph;
        this.historyView = historyView;
        serializer = new HistorySerializer("./history.ser");
        history = new ExpressionHistory();
        loadHistory();
    }

    public CalcView getView() {
        return view;
    }

    public CreditView getCreditView() {
        return viewCredit;
    }

    public PolishNotationModel getPolishNotationModel() {
        return polishNotationModel;
    }

    public void calculateCredit() {
        PolishNotationModel.CreditParam param = new PolishNotationModel.CreditParam();
        param.credit_sum(Double.parseDouble(viewCredit.getCreditSum().getText()));
        param.credit_time(Double.parseDouble(viewCredit.getCreditTerm().getText()));
        param.percent(Double.parseDouble(viewCredit.getInterestRate().getText()));
        if (viewCredit.getAnnuityButton().isSelected()) {
            param.type_payment(0);
        } else {
            param.type_payment(1);
        }
        PolishNotationModel.CreditResult result = PolishNotationModel.calculateCredit(param);
        viewCredit.getFirstPayment().setText(String.format("%.2f", result.first_payment()));
        viewCredit.getLastPayment().setText(String.format("%.2f", result.last_payment()));
        viewCredit.getOverpayment().setText(String.format("%.2f", result.overpayment()));
        viewCredit.getTotalPayment().setText(String.format("%.2f", result.total_payment()));

    }

    public void calculateDeposit() {
        PolishNotationModel.DepositParam param = new PolishNotationModel.DepositParam();
        param.percent(Double.parseDouble(viewDeposit.getPercentField().getText()));
        param.deposit_sum(Double.parseDouble(viewDeposit.getDepositSumField().getText()));
        param.deposit_time(Double.parseDouble(viewDeposit.getDepositTimeField().getText()));
        param.start_year(Integer.parseInt(viewDeposit.getStartYearField().getText()));
        param.start_date(viewDeposit.getMonthChoiceBox().getValue());
        if (viewDeposit.getSimpleButton().isSelected()) {
            param.deposit_type(0);
        } else {
            param.deposit_type(1);
        }
        PolishNotationModel.DepositResult result = PolishNotationModel.calculateDeposit(param);
        viewDeposit.getDepositLabel().setText(String.format("%.2f", result.total_deposit_sum()));
        viewDeposit.getInterestPayedLabel().setText(String.format("%.2f", result.total_percent_sum()));

    }

    public void calculatePolishNotation() {
        double x;
        String expression = view.getExpression().getText();
        if (!checkX(expression) || view.getxVal().getText().isEmpty()) {
            x = 0;
            calculate(x);
        } else {
            try {
                x = Double.parseDouble(view.getxVal().getText());
                calculate(x);
            } catch (NumberFormatException e) {
                view.getExpression().setText("X must be a number");
            }
        }

    }

    public void calculate(double x) {
        if (view.getExpression().getText().isEmpty()) {
            view.getExpression().setText("Wrong expression");
        } else {
            double result = mainCalculation(view.getExpression().getText(),  x);
            view.getExpression().setText(String.valueOf(result));
        }
    }

    public boolean checkX(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
                if (c == 'x') {
                    return true;
                }
        }
        return false;
    }

    public void calculateGraph() {
        if (viewGraph.getFuncExpression().getText().isEmpty()) {
            viewGraph.getFuncExpression().setText("Wrong expression");
        } else {
            viewGraph.getLineChart().getData().clear();
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(viewGraph.getFuncExpression().getText());
            double i = Double.parseDouble(viewGraph.getxMin().getText());
            while (i <= Double.parseDouble(viewGraph.getxMax().getText())) {
                series.getData().add(new XYChart.Data<>(i, mainCalculation(viewGraph.getFuncExpression().getText(), i)));
                i += 0.01;
            }
            viewGraph.getLineChart().getData().add(series);
        }
    }

    public void saveHistory() {
        serializer.saveHistory(history);
    }

    public void clearHistory() {
        File file = new File("./history.ser");

        if (file.exists()) {
            file.delete();
        }

        serializer.clearHistory();
        history.clear();
        //historyView.clearHistoryTable();
    }

    public void setSelectedExpression() {
        Expression selected = historyView.getExpression();
        view.setExpression(selected.getExpressionText());
        view.setXValue(selected.getXValue());
    }


    public void loadHistory() {
        File historyFile = new File("./history.ser");
        serializer = new HistorySerializer(historyFile.getAbsolutePath());
        history = serializer.getExpressionHistory();
        //historyView.loadHistory(history);
    }

    public HistoryView getHistoryView() {
        return historyView;
    }

    public ExpressionHistory getExpressionHistory() {
        return history;
    }

    public HistorySerializer getSerializer() {
        return serializer;
    }

    public CalcView getCalcView() {
        return view;
    }
}
