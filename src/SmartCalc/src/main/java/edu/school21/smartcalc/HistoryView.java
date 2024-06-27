package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.presenter.CalcPresenter;
import edu.school21.smartcalc.serializer.Expression;
import edu.school21.smartcalc.serializer.ExpressionHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryView implements Initializable {
    private CalcPresenter presenter;
    private Expression expression;


    @FXML
    private TableView<Expression> historyTable;
    @FXML
    private TableColumn<Expression, String> expressionColumn;
    @FXML
    private TableColumn<Expression, Double> xColumn;
    @FXML
    private Button closeButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(new CalcView(), new CreditView(),  new DepositView(), new GraphView(), this, new PolishNotationModel());
        TableColumn<Expression, Double> xValueColumn = new TableColumn<>("X Value");
        xValueColumn.setCellValueFactory(new PropertyValueFactory<>("xValue"));
        TableColumn<Expression, String> expressionTextColumn = new TableColumn<>("Expression Text");
        expressionTextColumn.setCellValueFactory(new PropertyValueFactory<>("expressionText"));
        loadHistory(presenter.getExpressionHistory());
        setUpHistoryTable();
    }

    public void addExpressionRecord(Expression expression) {
        historyTable.getItems().add(expression);
    }

    public void loadHistory(ExpressionHistory history) {
        ObservableList<Expression> data =
                FXCollections.observableArrayList(history.getExpressions());
        historyTable.setItems(data);
    }

    public void clearHistoryTable() {
        historyTable.getItems().clear();
    }

    private void setUpHistoryTable() {
        expressionColumn.setCellValueFactory(new PropertyValueFactory<>("expressionText"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("xValue"));
        TableView.TableViewSelectionModel<Expression> selectionModel
                = historyTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, expressionOld, newVal) -> {
            if (newVal != null) {
                expression = newVal;
                presenter.getCalcView().setExpression(newVal.getExpressionText());
                presenter.getCalcView().setXValue(newVal.getXValue());
                //presenter.setSelectedExpression();
            }
        });
    }

    public void setExpression(String data) {
        presenter.getView().getExpression().setText(data);
    }

    public void setXValue(Double xValue) {
         presenter.getView().getxVal().setText(xValue.toString());
    }

    public Expression getExpression() {
        return expression;
    }

    @FXML
    public void onCloseButtonClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onClearButtonClick() {
        clearHistoryTable();
        presenter.getSerializer().clearHistory();
        presenter.getExpressionHistory().clear();
        presenter.clearHistory();
    }

    public void setPresenter(CalcPresenter presenter) {
        this.presenter = presenter;
    }

    public TableView<Expression> getHistoryTable() {
        return historyTable;
    }
}
