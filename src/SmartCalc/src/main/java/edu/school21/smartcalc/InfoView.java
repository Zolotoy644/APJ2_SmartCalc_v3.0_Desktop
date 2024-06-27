package edu.school21.smartcalc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InfoView {
    @FXML
    private Button closeButton;

    @FXML
    public void onCloseButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
