package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.presenter.CalcPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositView implements Initializable {
    CalcPresenter presenter;

    @FXML
    private RadioButton simpleButton;
    @FXML
    private RadioButton compoundButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField depositSumField;
    @FXML
    private TextField depositTimeField;
    @FXML
    private TextField percentField;
    @FXML
    private TextField startYearField;
    @FXML
    private ChoiceBox<Integer> monthChoiceBox;
    @FXML
    private Label interestPayedLabel;
    @FXML
    private Label depositLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(new CalcView(), new CreditView(), this, new GraphView(), new HistoryView(),new PolishNotationModel());
        ObservableList<Integer> month = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        monthChoiceBox.setItems(month);
        monthChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    protected void onSimpleButtonClick() {
        simpleButton.setSelected(true);
        compoundButton.setSelected(false);
    }

    @FXML
    protected void onCompoundButtonClick() {
        simpleButton.setSelected(false);
        compoundButton.setSelected(true);
    }

    @FXML
    protected void onCloseButtonClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCalculateButtonClick() {
        presenter.calculateDeposit();
    }

    public RadioButton getSimpleButton() {
        return simpleButton;
    }

    public RadioButton getCompoundButton() {
        return compoundButton;
    }

    public TextField getDepositTimeField() {
        return depositTimeField;
    }

    public TextField getPercentField() {
        return percentField;
    }

    public TextField getStartYearField() {
        return startYearField;
    }

    public ChoiceBox<Integer> getMonthChoiceBox() {
        return monthChoiceBox;
    }

    public TextField getDepositSumField() {
        return depositSumField;
    }

    public Label getInterestPayedLabel() {
        return interestPayedLabel;
    }

    public Label getDepositLabel() {
        return depositLabel;
    }

}
