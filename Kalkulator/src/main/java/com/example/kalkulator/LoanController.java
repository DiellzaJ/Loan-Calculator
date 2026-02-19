package com.example.kalkulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

public class LoanController {

    @FXML
    private Label lblInterest;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPayment;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtInterestRate;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableView<Payment> table;

    @FXML
    public void initialize() {
        initializeTableColumns();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleCalculate() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            double ir = Double.parseDouble(txtInterestRate.getText());
            int years = Integer.parseInt(txtDuration.getText());

            Loan loan = new Loan(years, ir, amount);
            loan.buildTable();

            double irPaid = loan.getTotalInterest() / loan.getTotalPaid() * 100;
            double capital = amount / loan.getTotalPaid() * 100;

            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                    new PieChart.Data("Interest", irPaid),
                    new PieChart.Data("Capital", capital)
            );

            pieChart.setData(chartData);

            lblInterest.setText(String.format("$%.2f", loan.getTotalInterest()));
            lblPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));
            lblTotal.setText(String.format("$%.2f", loan.getTotalPaid()));

            ObservableList<Payment> payments = FXCollections.observableArrayList(loan.getPayments());
            table.setItems(payments);

        } catch (Exception e) {
            alertUser(e.getMessage());
        }
    }

    private void initializeTableColumns() {
        TableColumn<Payment, Integer> monthCol = new TableColumn<>("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<Payment, Double> balanceCol = getPaymentDoubleTableColumn("Balance", "oldBalance");

        TableColumn<Payment, Double> paymentCol = getPaymentDoubleTableColumn("Payment", "payment");

        TableColumn<Payment, Double> interestCol = getPaymentDoubleTableColumn("Interest Paid", "interest");

        TableColumn<Payment, Double> capitalCol = getPaymentDoubleTableColumn("Capital Paid", "capital");

        TableColumn<Payment, Double> newBalanceCol = getPaymentDoubleTableColumn("Remaining Balance", "remaining");

        table.getColumns().addAll(monthCol, balanceCol, paymentCol, interestCol, capitalCol, newBalanceCol);
    }

    @NotNull
    private static TableColumn<Payment, Double> getPaymentDoubleTableColumn(String Balance, String oldBalance) {
        TableColumn<Payment, Double> balanceCol = new TableColumn<>(Balance);
        balanceCol.setCellValueFactory(new PropertyValueFactory<>(oldBalance));
        balanceCol.setCellFactory(cell -> new TableCell<>() {
            @Override
            protected void updateItem(Double aDouble, boolean empty) {
                super.updateItem(aDouble, empty);
                if (aDouble != null) {
                    setText(String.format("$%.2f", aDouble));
                }
            }
        });
        return balanceCol;
    }

    private void alertUser(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("ERROR message");
        alert.show();
    }
}
