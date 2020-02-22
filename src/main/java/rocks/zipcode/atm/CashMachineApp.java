package rocks.zipcode.atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import rocks.zipcode.atm.bank.Alert;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
    private TextField field1 = new TextField();
    private TextField field2 = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("REGS ATM");

//Scene 1
        Label label1= new Label("Welcome!\n Please enter your account number.");
//        Button button1= new Button("Go to scene 2");
//        button1.setOnAction(e -> primaryStage.setScene(scene2));
        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Login");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field1.getText());
            if (id ==1 || id ==2) {
                cashMachine.login(id);

                areaInfo.setText(cashMachine.toString());
                primaryStage.setScene(scene2);
            }
            else
            {
                Alert.display("Alert Box", "Please enter a valid account number");}
        });



        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1,field1 , btnSubmit, areaInfo);
        scene1= new Scene(layout1, 400, 400);

//Scene 2
        Label label2= new Label("Please select an option.");

        TextArea withdrawDeposit = new TextArea();
        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field2.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
            withdrawDeposit.setText("Deposit Successful");

        });
        Button btnWithdraw = new Button("Withdraw");

        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field2.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
            withdrawDeposit.setText("Withdraw Successful");
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            primaryStage.setScene(scene1);
            areaInfo.setText(cashMachine.toString());
        });


        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(label2, btnDeposit,btnWithdraw,btnExit,field2,areaInfo, withdrawDeposit);
        scene2= new Scene(layout2,400,400);


        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}