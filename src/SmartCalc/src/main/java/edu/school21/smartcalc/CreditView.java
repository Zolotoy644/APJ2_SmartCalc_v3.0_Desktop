package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.presenter.CalcPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditView implements Initializable {
    @FXML
    private RadioButton annuityButton;
    @FXML
    private RadioButton differentiaiedButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button calculateButton;
    @FXML
    private TextField creditSum;
    @FXML
    private TextField creditTerm;
    @FXML
    private TextField interestRate;
    @FXML
    private Label firstPayment;
    @FXML
    private Label lastPayment;
    @FXML
    private Label overpayment;
    @FXML
    private Label totalPayment;


    private CalcPresenter presenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(new CalcView(), this,  new DepositView(), new GraphView(), new HistoryView(),new PolishNotationModel());
    }

    @FXML
    protected void onAnnuityButtonClick() {
        annuityButton.setSelected(true);
        differentiaiedButton.setSelected(false);
    }

    @FXML
    protected void onDifferentiaiedButtonClick() {
        annuityButton.setSelected(false);
        differentiaiedButton.setSelected(true);
    }

    @FXML
    protected void onCloseButtonClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCalculateButtonClick() {
        presenter.calculateCredit();
    }

    public TextField getCreditSum() {
        return creditSum;
    }

    public TextField getCreditTerm() {
        return creditTerm;
    }

    public TextField getInterestRate() {
        return interestRate;
    }

    public RadioButton getAnnuityButton() {
        return annuityButton;
    }

    public RadioButton getDifferentiaiedButton() {
        return differentiaiedButton;
    }

    public Label getFirstPayment() {
        return firstPayment;
    }

    public Label getLastPayment() {
        return lastPayment;
    }

    public Label getOverpayment() {
        return overpayment;
    }

    public Label getTotalPayment() {
        return totalPayment;
    }
}
