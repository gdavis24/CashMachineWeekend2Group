package rocks.zipcode.atm;
import com.sun.prism.shader.Texture_Color_Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import rocks.zipcode.atm.bank.Alert;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
    private TextField fieldEnterID = new TextField();
    private TextField fieldName = new TextField();
    private TextField fieldOtherAmount = new TextField();
    private TextField fieldID = new TextField();
    private TextField fieldEmail = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private Stage stage;
    //Scene loginScreen, loggedInHomeScreen, depositScreen, withdrawScreen, createAccountScreen;
    TextArea areaInfo = new TextArea();
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setScene(new Scene(loginScreen()));
        stage.show();
        fieldEnterID.setPrefSize(400, 100);
        fieldName.setPrefSize(400, 100);
        fieldOtherAmount.setPrefSize(400, 80);
        fieldID.setPrefSize(400, 100);
        fieldEmail.setPrefSize(400, 100);
        areaInfo.setPrefSize(400,100);
    }
    //loginScreen
    private Parent loginScreen() {
        //fieldEnterID.setText("99");
        stage.setTitle("REGS ATM");
        Label labelLoginScreen = new Label("Welcome!\n Please enter your account number.");
        Button btnSubmit = new Button("Login");
        btnSubmit.setFont(Font.font("Verdana", 30));
        btnSubmit.setPrefHeight(100);
        btnSubmit.setPrefWidth(400);
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(fieldEnterID.getText());
            if (id == 1 || id == 2) {
                cashMachine.login(id);
                areaInfo.setText(cashMachine.toString());
                stage.setScene(new Scene(loggedInHomeScreen()));
            } else {
                Alert.display("Alert Box", "Please enter a valid account number");
            }
        });
        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setPrefHeight(100);
        btnCreateAccount.setPrefWidth(400);
        btnCreateAccount.setFont(Font.font("Verdana", 30));
        btnCreateAccount.setOnAction(e -> {
            stage.setScene(new Scene(createAccountScreen()));

        });
        VBox layoutLoginScreen = new VBox(0);
        layoutLoginScreen.setPrefSize(400,500);
        layoutLoginScreen.getChildren().addAll(labelLoginScreen, fieldEnterID, btnSubmit, btnCreateAccount);
        //loginScreen = new Scene(layout1, 300, 300);
        return layoutLoginScreen;
    }
    //createAccountScreen
    private Parent createAccountScreen() {
        Label labelCreateAccount = new Label("Please fill out all fields");
        labelCreateAccount.setPrefSize(100, 100);
        Label labelId = new Label("ID");
        labelId.setPrefSize(100,100);
        Label labelName = new Label("Name");
        labelName.setPrefSize(100,100);
        String name = fieldName.getText();
        Label labelEmail = new Label("Email");
        String email = fieldEmail.getText();
        ObservableList<String> options = FXCollections.observableArrayList("Basic", "Premium");
        final ComboBox<String> combobox = new ComboBox<>(options);
        String accountType = combobox.getValue() + "";
        Button btnSubmitInfo = new Button("Submit");
        btnSubmitInfo.setPrefHeight(100);
        btnSubmitInfo.setPrefWidth(400);
        btnSubmitInfo.setFont(Font.font("Verdana", 30));
        btnSubmitInfo.setOnAction(e -> {
            if (!name.equals("")) {
                Alert.display("Alert Box", "Invalid input. Please enter a number.");
            } else if (email.contains("@")) {
                Alert.display("Alert Box", "Invalid input. Please enter a valid email address.");
            } else {
                cashMachine.addAccount(Integer.parseInt(fieldEnterID.getText()), name, email, 0, accountType);
                cashMachine.login(Integer.parseInt(fieldEnterID.getText()));
                areaInfo.setText(cashMachine.toString());
                stage.setScene(new Scene(loggedInHomeScreen()) );
            }
        });
        VBox layoutCreateAccount = new VBox(0);
        labelCreateAccount.setPrefSize(400, 400);
        layoutCreateAccount.getChildren().addAll(labelCreateAccount,labelId, fieldID,labelName, fieldName,labelEmail, fieldEmail, btnSubmitInfo, combobox);
        //createAccountScreen = new Scene(layoutCreateAccount, 300, 300);
        return layoutCreateAccount;
    }
    //loggedInHomeScreen
    private Parent loggedInHomeScreen() {
        Label label2 = new Label("Please select an option.");
        label2.setPrefSize(400, 100);
        label2.setFont(Font.font("Verdana", 25));
        //label2.setTextFill(Color.color(0,0,5));
        areaInfo.setText(cashMachine.toString());
        Button btnGoToDeposit = new Button("Deposit");
        btnGoToDeposit.setPrefHeight(100);
        btnGoToDeposit.setPrefWidth(400);
        btnGoToDeposit.setFont(Font.font("Verdana", 30));
        btnGoToDeposit.setOnAction(e -> {
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()));
        });
        Button btnGoToWithdraw = new Button("Withdraw");
        btnGoToWithdraw.setPrefHeight(100);
        btnGoToWithdraw.setPrefWidth(400);
        btnGoToWithdraw.setFont(Font.font("Verdana", 30));
        btnGoToWithdraw.setOnAction(e -> {
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });
        Button btnExit = new Button("Exit");
        btnExit.setPrefHeight(100);
        btnExit.setPrefWidth(400);
        btnExit.setFont(Font.font("Verdana", 30));
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            stage.setScene(new Scene(loginScreen()) );
            areaInfo.setText(cashMachine.toString());
        });
        VBox layout2 = new VBox(0);
        layout2.setPrefSize(400, 500);

        layout2.getChildren().addAll(areaInfo, label2, btnGoToDeposit, btnGoToWithdraw, btnExit);
        //loggedInHomeScreen = new Scene(layout2, 600, 600);
        return layout2;
    }
    //depositScreen
    private Parent depositScreen() {
        Label labelDepositScreen = new Label("Please select an amount to deposit.");
        Button btnDeposit10 = new Button("$10");
        btnDeposit10.setPrefHeight(90);
        btnDeposit10.setPrefWidth(400);
        btnDeposit10.setFont(Font.font("Verdana", 30));
        btnDeposit10.setOnAction(e -> {
            cashMachine.deposit(10);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()) );
        });
        Button btnDeposit50 = new Button("$50");
        btnDeposit50.setPrefHeight(90);
        btnDeposit50.setPrefWidth(400);
        btnDeposit50.setFont(Font.font("Verdana", 30));
        btnDeposit50.setOnAction(e -> {
            cashMachine.deposit(50);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()));
        });
        Button btnDeposit100 = new Button("$100");
        btnDeposit100.setPrefHeight(90);
        btnDeposit100.setPrefWidth(400);
        btnDeposit100.setFont(Font.font("Verdana", 30));
        btnDeposit100.setOnAction(e -> {
            cashMachine.deposit(100);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()));
        });
        Button btnDepositOtherAmount = new Button("Other amount");
        btnDepositOtherAmount.setPrefHeight(90);
        btnDepositOtherAmount.setPrefWidth(400);
        btnDepositOtherAmount.setFont(Font.font("Verdana", 30));
        btnDepositOtherAmount.setOnAction(e -> {
            cashMachine.deposit(Integer.parseInt(fieldOtherAmount.getText()));
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()));
        });
        Button btnBackToLoggedIn = new Button("Back to main");
        btnBackToLoggedIn.setPrefHeight(90);
        btnBackToLoggedIn.setPrefWidth(400);
        btnBackToLoggedIn.setFont(Font.font("Verdana", 30));
        btnBackToLoggedIn.setOnAction(e -> {
            stage.setScene(new Scene(loggedInHomeScreen()));
            areaInfo.setText(cashMachine.toString());
        });
        VBox layout3 = new VBox(0);
        layout3.setPrefSize(400, 500);
        layout3.getChildren().addAll( areaInfo, labelDepositScreen, btnDeposit10, btnDeposit50, btnDeposit100, fieldOtherAmount, btnDepositOtherAmount, btnBackToLoggedIn);
        //depositScreen = new Scene(layout3, 600, 600);
        return layout3;
    }
    //withdrawScreen
    private Parent withdrawScreen() {
        Label labelWithdrawScreen = new Label("Please select an amount.");
        Button btnWithdraw10 = new Button("$10");
        btnWithdraw10.setPrefHeight(90);
        btnWithdraw10.setPrefWidth(400);
        btnWithdraw10.setFont(Font.font("Verdana", 30));
        btnWithdraw10.setOnAction(e -> {
            cashMachine.withdraw(10);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });
        Button btnWithdraw50 = new Button("$50");
        btnWithdraw50.setPrefHeight(90);
        btnWithdraw50.setPrefWidth(400);
        btnWithdraw50.setFont(Font.font("Verdana", 30));
        btnWithdraw50.setOnAction(e -> {
            cashMachine.withdraw(50);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });
        Button btnWithdraw100 = new Button("$100");
        btnWithdraw100.setPrefHeight(90);
        btnWithdraw100.setPrefWidth(400);
        btnWithdraw100.setFont(Font.font("Verdana", 30));
        btnWithdraw100.setOnAction(e -> {
            cashMachine.withdraw(100);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });
        Button btnWithdrawOtherAmount = new Button("Other amount");
        btnWithdrawOtherAmount.setPrefHeight(90);
        btnWithdrawOtherAmount.setPrefWidth(400);
        btnWithdrawOtherAmount.setFont(Font.font("Verdana", 30));
        btnWithdrawOtherAmount.setOnAction(e -> {
            cashMachine.withdraw(Integer.parseInt(fieldOtherAmount.getText()));
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });
        Button btnBackToLoggedIn = new Button("Back to main");
        btnBackToLoggedIn.setPrefHeight(90);
        btnBackToLoggedIn.setPrefWidth(400);
        btnBackToLoggedIn.setFont(Font.font("Verdana", 30));
        btnBackToLoggedIn.setOnAction(e -> {
            stage.setScene(new Scene(loggedInHomeScreen()));
            areaInfo.setText(cashMachine.toString());
        });
        VBox layout4 = new VBox(0);
        layout4.setPrefSize(400, 500);
        layout4.getChildren().addAll(areaInfo, labelWithdrawScreen, btnWithdraw10, btnWithdraw50, btnWithdraw100, fieldOtherAmount, btnWithdrawOtherAmount, btnBackToLoggedIn);
        //withdrawScreen = new Scene(layout4, 600, 600);
        return layout4;
    }
    public static void main(String[] args) {
        launch(args);
    }
}