package rocks.zipcode.atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import rocks.zipcode.atm.bank.AccountData;
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
    private TextField field3 = new TextField();
    private TextField field4 = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    Scene scene1, scene2, scene3, scene4, scene15;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("REGS ATM");

//Scene 1
        Label label1= new Label("Welcome!\n\n Please enter your account number.");
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


        Button btnToMenu = new Button("Back To Menu");
        btnToMenu.setOnAction(e -> {
            primaryStage.setScene(scene3);
        });


        Label label2= new Label("Deposit");
        TextArea deposit = new TextArea();
        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            primaryStage.setScene(scene3);

            VBox layout2 = new VBox(10);
            layout2.getChildren().addAll(label2, field2, areaInfo, btnDeposit, btnToMenu);
            scene3 = new Scene(layout2, 400, 400);

            primaryStage.setScene(scene3);
            primaryStage.show();

            int amount = Integer.parseInt(field2.getText());
            cashMachine.deposit(amount);
            areaInfo.setText(cashMachine.toString());
            deposit.setText("Deposit Successful");

        });



        Label label3= new Label("Withdraw");
        TextArea withdraw = new TextArea();
        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> { primaryStage.setScene(scene4);

            VBox layout3 = new VBox(10);
            layout3.getChildren().addAll(label3, field3, btnWithdraw, areaInfo);
            scene4 = new Scene(layout3, 400, 400);

            primaryStage.setScene(scene4);
            primaryStage.show();

            int amount = Integer.parseInt(field3.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
//                    if (cashMachine.withdraw(amount) <= 0) {
//                        withdrawDeposit.setText("Insufficient Funds :(");
//                    } else {
            withdraw.setText("Withdraw Successful");

        });

        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> {
            cashMachine.exit();
            primaryStage.setScene(scene1);
            areaInfo.setText(cashMachine.toString());
        });



        VBox layout2= new VBox(0);


        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(200, 200, 200, 200));
        flowPane.setMargin(btnDeposit, new Insets(100, 100, 100, 100));
        flowPane.setAlignment(Pos.BOTTOM_CENTER);
//        depFlow.setHgap(10);
        flowPane.getChildren().add(btnDeposit);
        btnDeposit.setPrefWidth(400);
        btnDeposit.setPrefHeight(200);
        btnWithdraw.setPrefWidth(400);
        btnWithdraw.setPrefHeight(200);
        btnLogout.setPrefWidth(400);
        btnLogout.setPrefHeight(200);
        layout2.getChildren().addAll(label2, /*btnLogout,field2*/areaInfo /*withdrawDeposit*/, btnDeposit,btnWithdraw, btnLogout);
        scene2= new Scene(layout2,400,500);



        btnWithdraw.setLayoutX(200);
        btnWithdraw.setLayoutY(200);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
