package edu.school21.smartcalc;

import edu.school21.smartcalc.model.PolishNotationModel;
import edu.school21.smartcalc.presenter.CalcPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphView implements Initializable {
    private CalcPresenter presenter;
    @FXML
    private Button closeButton;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private TextField xMin;
    @FXML
    private TextField xMax;
    @FXML
    private TextField yMin;
    @FXML
    private TextField yMax;
    @FXML
    private TextField funcExpression;

    @FXML
    public void onCloseButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new CalcPresenter(new CalcView(),new CreditView(), new DepositView(), this, new HistoryView(), new PolishNotationModel());
        lineChart.setTitle("Function graph");
        xAxis.setLabel("x");
        yAxis.setLabel("y");
    }

    public NumberAxis getxAxis() {
        return xAxis;
    }

    public NumberAxis getyAxis() {
        return yAxis;
    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public TextField getxMin() {
        return xMin;
    }

    public TextField getxMax() {
        return xMax;
    }

    public TextField getyMin() {
        return yMin;
    }

    public TextField getyMax() {
        return yMax;
    }

    public TextField getFuncExpression() {
        return funcExpression;
    }

    public void onDrawButtonClicked() {
        presenter.calculateGraph();
    }
}
