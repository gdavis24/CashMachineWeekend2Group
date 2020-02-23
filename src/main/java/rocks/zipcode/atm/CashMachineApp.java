package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import rocks.zipcode.atm.bank.Alert;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
    }
    //loginScreen
    private Parent loginScreen() {
        //fieldEnterID.setText("99");
        stage.setTitle("REGS ATM");
        Label labelLoginScreen = new Label("Welcome!\n Please enter your account number.");

        Button btnSubmit = new Button("Login");
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
        btnCreateAccount.setOnAction(e -> {
            stage.setScene(new Scene(createAccountScreen()));
        });
        VBox layoutLoginScreen = new VBox(10);
        layoutLoginScreen.getChildren().addAll(labelLoginScreen, fieldEnterID, btnSubmit, btnCreateAccount);
        //loginScreen = new Scene(layout1, 300, 300);
        return layoutLoginScreen;
    }
    //createAccountScreen
    private Parent createAccountScreen() {
        Label labelCreateAccount = new Label("Please fill out all fields");

        Label labelId = new Label("ID");
        Label labelName = new Label("Name");
        String name = fieldName.getText();
        Label labelEmail = new Label("Email");
        String email = fieldEmail.getText();

        ObservableList<String> options = FXCollections.observableArrayList("Basic", "Premium");
        final ComboBox<String> combobox = new ComboBox<>(options);

        String accountType = combobox.getValue() + "";
        Button btnSubmitInfo = new Button("Submit");
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

        VBox layoutCreateAccount = new VBox(10);
        layoutCreateAccount.getChildren().addAll(labelCreateAccount,labelId, fieldID,labelName, fieldName,labelEmail, fieldEmail, btnSubmitInfo, combobox);
        //createAccountScreen = new Scene(layoutCreateAccount, 300, 300);
        return layoutCreateAccount;
    }

    //loggedInHomeScreen
    private Parent loggedInHomeScreen() {
        Label label2 = new Label("Please select an option.");
        areaInfo.setText(cashMachine.toString());
        Button btnGoToDeposit = new Button("Deposit");
        btnGoToDeposit.setOnAction(e -> {
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(depositScreen()));
        });

        Button btnGoToWithdraw = new Button("Withdraw");

        btnGoToWithdraw.setOnAction(e -> {
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            stage.setScene(new Scene(loginScreen()) );
            areaInfo.setText(cashMachine.toString());
        });

        VBox layout2 = new VBox(20);

        layout2.getChildren().addAll(label2, btnGoToDeposit, btnGoToWithdraw, btnExit, areaInfo);
        //loggedInHomeScreen = new Scene(layout2, 600, 600);
        return layout2;
    }
    //depositScreen
    private Parent depositScreen() {
        Label labelDepositScreen = new Label("Please select an amount to deposit.");

        Button btnDeposit10 = new Button("$10");
        btnDeposit10.setOnAction(e -> {
            cashMachine.deposit(10);
            areaInfo.setText(cashMachine.toString());

            stage.setScene(new Scene(depositScreen()) );
        });

        Button btnDeposit50 = new Button("$50");
        btnDeposit50.setOnAction(e -> {
            cashMachine.deposit(50);
            areaInfo.setText(cashMachine.toString());

            stage.setScene(new Scene(depositScreen()));
        });

        Button btnDeposit100 = new Button("$100");
        btnDeposit100.setOnAction(e -> {
            cashMachine.deposit(100);
            areaInfo.setText(cashMachine.toString());

            stage.setScene(new Scene(depositScreen()));
        });

        Button btnDepositOtherAmount = new Button("Other amount");
        btnDepositOtherAmount.setOnAction(e -> {

            cashMachine.deposit(Integer.parseInt(fieldOtherAmount.getText()));
            areaInfo.setText(cashMachine.toString());

            stage.setScene(new Scene(depositScreen()));
        });


        Button btnBackToLoggedIn = new Button("Back to main");
        btnBackToLoggedIn.setOnAction(e -> {
            stage.setScene(new Scene(loggedInHomeScreen()));
            areaInfo.setText(cashMachine.toString());
        });


        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(labelDepositScreen, btnDeposit10, btnDeposit50, btnDeposit100, btnDepositOtherAmount, fieldOtherAmount, btnBackToLoggedIn, areaInfo);
        //depositScreen = new Scene(layout3, 600, 600);
        return layout3;
    }
    //withdrawScreen
    private Parent withdrawScreen() {
        Label labelWithdrawScreen = new Label("Please select an amount.");

        Button btnWithdraw10 = new Button("$10");
        btnWithdraw10.setOnAction(e -> {
            cashMachine.withdraw(10);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });

        Button btnWithdraw50 = new Button("$50");
        btnWithdraw50.setOnAction(e -> {
            cashMachine.withdraw(50);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });

        Button btnWithdraw100 = new Button("$100");
        btnWithdraw100.setOnAction(e -> {
            cashMachine.withdraw(100);
            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });

        Button btnWithdrawOtherAmount = new Button("Other amount");
        btnWithdrawOtherAmount.setOnAction(e -> {

            cashMachine.withdraw(Integer.parseInt(fieldOtherAmount.getText()));

            areaInfo.setText(cashMachine.toString());
            stage.setScene(new Scene(withdrawScreen()));
        });

        Button btnBackToLoggedIn = new Button("Back to main");
        btnBackToLoggedIn.setOnAction(e -> {
            stage.setScene(new Scene(loggedInHomeScreen()));
            areaInfo.setText(cashMachine.toString());
        });
        VBox layout4 = new VBox(20);
        layout4.getChildren().addAll(labelWithdrawScreen, btnWithdraw10, btnWithdraw50, btnWithdraw100, btnWithdrawOtherAmount, btnBackToLoggedIn, fieldOtherAmount, areaInfo);
        //withdrawScreen = new Scene(layout4, 600, 600);
        return layout4;
    }



    public static void main(String[] args) {
        launch(args);
    }
}