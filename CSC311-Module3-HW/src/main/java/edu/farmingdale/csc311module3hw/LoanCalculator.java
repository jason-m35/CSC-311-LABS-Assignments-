package edu.farmingdale.csc311module3hw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import java.text.DecimalFormat;

public class LoanCalculator extends Application {


    public void start(Stage primaryStage) {
        // create labels & text fields
        Label interestRateLabel = new Label("Annual Interest Rate:");
        TextField txtInterestRate = new TextField();

        Label loanYearsLabel = new Label("Number of Years:");
        TextField txtYears = new TextField();

        Label loanAmountLabel = new Label("Loan Amount:");
        TextField txtLoanAmount = new TextField();

        Label monthlyPaymentLabel = new Label("Monthly Payment:");
        TextField txtMonthlyPayment = new TextField();


        Label totalPaymentLabel = new Label("Total Payment:");
        TextField txtTotalPayment = new TextField();

        Button calculateBtn = new Button("Calculate");

        // event handling
        calculateBtn.setOnAction(e -> {
            double annualInterestRate = Double.parseDouble(txtInterestRate.getText());
            int years = Integer.parseInt(txtYears.getText());
            double loanAmount = Double.parseDouble(txtLoanAmount.getText());

            double monthlyInterestRate = annualInterestRate / 1200;
            int numberOfMonths = years * 12;

            double monthlyPayment = (loanAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));
            double totalPayment = monthlyPayment * numberOfMonths;

            DecimalFormat df = new DecimalFormat("$##,##0.00");
            txtMonthlyPayment.setText(df.format(monthlyPayment));
            txtTotalPayment.setText(df.format(totalPayment));
        });

        // layout setup
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(interestRateLabel, 0, 0);
        gridPane.add(txtInterestRate, 1, 0);
        gridPane.add(loanYearsLabel, 0, 1);
        gridPane.add(txtYears, 1, 1);
        gridPane.add(loanAmountLabel, 0, 2);
        gridPane.add(txtLoanAmount, 1, 2);
        gridPane.add(monthlyPaymentLabel, 0, 3);
        gridPane.add(txtMonthlyPayment, 1, 3);
        gridPane.add(totalPaymentLabel, 0, 4);
        gridPane.add(txtTotalPayment, 1, 4);
        gridPane.add(calculateBtn, 1, 5);

        // scene & stage setup
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
