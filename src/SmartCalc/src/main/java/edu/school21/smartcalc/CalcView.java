package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.presenter.CalcPresenter;
import edu.school21.smartcalc.serializer.Expression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalcView implements Initializable {


    @FXML
    private TextField expression;
    @FXML
    private Button pointButton;
    @FXML
    private TextField xVal;
    @FXML
    private Button credButton;
    @FXML
    private Button depositButton;

    private CalcPresenter presenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(this, new CreditView(),  new DepositView(), new GraphView(), new HistoryView(), new PolishNotationModel());
        //presenter.saveHistory();
    }

    @FXML
    protected void onNumButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String fullExpression = expression.getText();
        fullExpression += button.getText();
        expression.setText(fullExpression);
        if (button.getText().equals(".")) {
            pointButton.setDisable(true);
        }
        if (Character.isDigit(button.getText().charAt(0))) {
            pointButton.setDisable(false);
        }
    }

    @FXML
    protected void onFuncButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String fullExpression = expression.getText();
        fullExpression += button.getText() + "(";
        expression.setText(fullExpression);
    }

    @FXML
    protected void onAcButtonClick() {
        expression.clear();
    }

    @FXML
    protected void onEqualButtonClick() {
        presenter.getExpressionHistory().addData(new Expression(expression.getText(), Double.parseDouble(xVal.getText())));
        presenter.saveHistory();
        presenter.calculatePolishNotation();
        if (presenter.getHistoryView().getHistoryTable() != null) {
            presenter.getHistoryView().loadHistory(presenter.getExpressionHistory());
        }
    }

    @FXML
    protected void onCredButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("credit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Credit Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onDepositButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deposit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 410, 425);
        Stage stage = new Stage();
        stage.setTitle("Deposit Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public TextField getxVal() {
        return xVal;
    }

    public TextField getExpression() {
        return expression;
    }

    @FXML
    protected void onCloseButtonClick() {
        presenter.saveHistory();
        System.exit(0);
    }

    @FXML
    protected void onAboutButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 476);
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onGraphButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graph-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 656, 516);
        Stage stage = new Stage();
        stage.setTitle("Draw graph");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onHistoryButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("history-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("History");
        stage.setScene(scene);
        stage.setResizable(false);

        HistoryView controller = fxmlLoader.getController();
        controller.setPresenter(presenter);

        stage.show();
    }

    public void setExpression(String expressionText) {
        expression.setText(expressionText);
    }

    public void setXValue(Double xValue) {
        xVal.setText(Double.toString(xValue));
    }

    public CalcPresenter getPresenter() {
        return presenter;
    }
}