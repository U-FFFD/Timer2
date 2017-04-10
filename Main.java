import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("ChronoTimer 3.00");

        Button pwrButton = new Button();
        pwrButton.setText("POWER");
        pwrButton.setTranslateX(-350);
        pwrButton.setTranslateY(-250);
        pwrButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button 1 output");
            }
        });

        Button fnButton = new Button();
        fnButton.setText("FUNCTION");
        fnButton.setTranslateX(-350);
        fnButton.setTranslateY(0);
        fnButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button 2 output");
            }
        });

        Button swapButton = new Button();
        swapButton.setText("SWAP");
        swapButton.setTranslateX(-350);
        swapButton.setTranslateY(250);
        swapButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button 3 output");
            }
        });

        Button printerButton = new Button();
        printerButton.setText("Printer Pwr");
        printerButton.setTranslateX(300);
        printerButton.setTranslateY(-280);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);
        grid.setTranslateY(100);
        grid.setTranslateX(200);

        Button key1 = new Button();
        Button key2 = new Button();
        Button key3 = new Button();
        Button key4 = new Button();
        Button key5 = new Button();
        Button key6 = new Button();
        Button key7 = new Button();
        Button key8 = new Button();
        Button key9 = new Button();
        Button key0 = new Button();
        Button keyStar = new Button();
        Button keyPound = new Button();

        key1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 1");
            }
        });
        key2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 2");
            }
        });
        key3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 3");
            }
        });
        key4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 4");
            }
        });
        key5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 5");
            }
        });
        key6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 6");
            }
        });
        key7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 7");
            }
        });
        key8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 8");
            }
        });
        key9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 9");
            }
        });
        key0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("NUM 0");
            }
        });
        keyStar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("*");
            }
        });
        keyPound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("#");
            }
        });

        key1.setText("1");
        key2.setText("2");
        key3.setText("3");
        key4.setText("4");
        key5.setText("5");
        key6.setText("6");
        key7.setText("7");
        key8.setText("8");
        key9.setText("9");
        key0.setText("0");
        keyStar.setText("*");
        keyPound.setText("#");

        grid.add(key1,0,0);
        grid.add(key2,1,0);
        grid.add(key3,2,0);
        grid.add(key4,0,1);
        grid.add(key5,1,1);
        grid.add(key6,2,1);
        grid.add(key7,0,2);
        grid.add(key8,1,2);
        grid.add(key9,2,2);
        grid.add(key0,1,3);
        grid.add(keyStar,0,3);
        grid.add(keyPound,2,3);

        grid.setMaxSize(50,50);

        // Begin Trigger controller grid
        GridPane channelGrid = new GridPane();
        channelGrid.setAlignment(Pos.CENTER);
        channelGrid.setHgap(10);
        channelGrid.setVgap(18);
        channelGrid.setTranslateY(40);
        channelGrid.setTranslateX(-60);

        final ToggleGroup group = new ToggleGroup();

        Label enableText = new Label("Enable/Disable");
        Label enableText2 = new Label("Enable/Disable");
        Label lCh1 = new Label("1");
        Label lCh2 = new Label("2");
        Label lCh3 = new Label("3");
        Label lCh4 = new Label("4");
        Label lCh5 = new Label("5");
        Label lCh6 = new Label("6");
        Label lCh7 = new Label("7");
        Label lCh8 = new Label("8");

        ToggleButton ch1 = new ToggleButton();
        ToggleButton ch2 = new ToggleButton();
        ToggleButton ch3 = new ToggleButton();
        ToggleButton ch4 = new ToggleButton();
        ToggleButton ch5 = new ToggleButton();
        ToggleButton ch6 = new ToggleButton();
        ToggleButton ch7 = new ToggleButton();
        ToggleButton ch8 = new ToggleButton();

        channelGrid.add(lCh1,1,0);
        channelGrid.add(lCh3,2,0);
        channelGrid.add(lCh5,3,0);
        channelGrid.add(lCh7,4,0);

        channelGrid.add(enableText,0,1);

        channelGrid.add(ch1, 1,1);
        channelGrid.add(ch3, 2, 1);
        channelGrid.add(ch5, 3, 1);
        channelGrid.add(ch7,4,1);

        channelGrid.add(lCh2,1,2);
        channelGrid.add(lCh4,2,2);
        channelGrid.add(lCh6,3,2);
        channelGrid.add(lCh8,4,2);

        channelGrid.add(enableText2,0,3);

        channelGrid.add(ch2, 1 ,3 );
        channelGrid.add(ch4, 2,3);
        channelGrid.add(ch6,3,3);
        channelGrid.add(ch8,4,3);

        channelGrid.setMaxSize(260,150);


        Text sceneTitle = new Text("Chrono Timer");
        sceneTitle.setId("fancytext");
        sceneTitle.setTranslateX(0);
        sceneTitle.setTranslateY(-240);

        root.getChildren().add(sceneTitle);
        root.getChildren().add(channelGrid);
        root.getChildren().add(grid);
        root.getChildren().add(printerButton);
        root.getChildren().add(pwrButton);
        root.getChildren().add(fnButton);
        root.getChildren().add(swapButton);

        //channelGrid.setStyle("-fx-background-color: green;");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.listen();
        //launch(args);
    }
}
