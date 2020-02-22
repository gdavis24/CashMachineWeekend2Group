package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

public class Main extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    Stage window;
    Scene scene1, scene2;



    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Welcome, please set account ID \n 1: Basic Account \n 2: Premium Account");
        Button buttonBasic = new Button("1: Basic");
        Button buttonPremium = new Button("2: Premium");
        buttonBasic.setOnAction(e -> window.setScene(scene2));
        //button2.setOnAction(e -> window.setScene(scene3));

        //Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox((20));
        layout1.getChildren().addAll(label1,buttonBasic);
        scene1 = new Scene (layout1, 200, 200);

        //Button 2
        Button button3 = new Button("Go back to scene 1");
        button3.setOnAction(e -> window.setScene(scene1));

        //Layout2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button3);
        scene2 = new Scene(layout2, 600,300);
        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Logout");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });



        window.setScene(scene1);
        window.setTitle("Cash Machine!!!");
        window.show();

    }

    public static void main (String[] args){
        launch(args);
    }

}