import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        final StackPane root = new StackPane();
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("ChronoTimer 3.00");

        // Title
        final Text sceneTitle = new Text("Chrono Timer");
        sceneTitle.setId("fancytext");
        sceneTitle.setTranslateX(0);
        sceneTitle.setTranslateY(-260);

        // Display Label
        Label displayLabel = new Label();
        displayLabel.setText("Queue   /   Running   /   Final Time");
        displayLabel.setTranslateX(0);
        displayLabel.setTranslateY(240);

        // Display text field
        final Label screen = new Label();
        screen.setId("screen-text");
        screen.setTranslateX(-100);
        screen.setTranslateY(36);
        screen.setText("Hello");

        // Swap Button
        Button swapButton = new Button();
        swapButton.setText("SWAP");
        swapButton.setTranslateX(-314);
        swapButton.setTranslateY(200);
        swapButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Swap Click");
            }
        });

        // Printer Power Button
        Button printerButton = new Button();
        printerButton.setText("PRINTER");
        printerButton.setTranslateX(310);
        printerButton.setTranslateY(-254);
        printerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Printer Pwr Click");
            }
        });

        //////////////      FUNCTION PANEL      //////////////////////////
        Button fnButton = new Button();
        fnButton.setText("FUNCTION");
        fnButton.setTranslateX(-314);
        fnButton.setTranslateY(100);

        Button leftArrow = new Button();
        Button rightArrow = new Button();
        Button upArrow = new Button();
        Button downArrow = new Button();

        upArrow.setId("up-arrow");
        downArrow.setId("down-arrow");
        leftArrow.setId("left-arrow");
        rightArrow.setId("right-arrow");

        upArrow.setTranslateX(-314);
        downArrow.setTranslateX(-314);
        leftArrow.setTranslateX(-400);
        rightArrow.setTranslateX(-228);

        upArrow.setTranslateY(58);
        downArrow.setTranslateY(142);
        leftArrow.setTranslateY(100);
        rightArrow.setTranslateY(101);

        // Listeners
        final CmdList theList = new CmdList();
        fnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Function Click");
            }
        });
        upArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Up Arrow");
            }
        });
        downArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Down Arrow");
            }
        });
        leftArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                screen.setText(theList.left());
            }
        });
        rightArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {  screen.setText(theList.right());
            }
        });
        //// END FUNCTION PANEL  ///////////////////////////

        /*/////////////////////////////////////////////////
                    BEGIN BUTTON GRID
         */////////////////////////////////////////////////
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);
        grid.setTranslateY(120);
        grid.setTranslateX(300);
        grid.setMaxSize(220,200);
        grid.setId("display");

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
        //////////////////// END BUTTON GRID ////////////////////

        /*////////////////////////////////////////////////////////
                        CHANNEL GRID
         *///////////////////////////////////////////////////////
        final boolean[] isArmed = new boolean[8];
        GridPane channelGrid = new GridPane();
        channelGrid.setAlignment(Pos.CENTER);
        channelGrid.setHgap(12);
        channelGrid.setVgap(6);
        channelGrid.setMaxSize(300,206);
        channelGrid.setTranslateX(0);
        channelGrid.setTranslateY(-124);
        channelGrid.setStyle("-fx-background-color: #3c7cc4;");

        final ToggleButton ch1 = new ToggleButton();
        final ToggleButton ch2 = new ToggleButton();
        final ToggleButton ch3 = new ToggleButton();
        final ToggleButton ch4 = new ToggleButton();
        final ToggleButton ch5 = new ToggleButton();
        final ToggleButton ch6 = new ToggleButton();
        final ToggleButton ch7 = new ToggleButton();
        final ToggleButton ch8 = new ToggleButton();

        final RadioButton rb1 = new RadioButton();
        final RadioButton rb2 = new RadioButton();
        final RadioButton rb3 = new RadioButton();
        final RadioButton rb4 = new RadioButton();
        final RadioButton rb5 = new RadioButton();
        final RadioButton rb6 = new RadioButton();
        final RadioButton rb7 = new RadioButton();
        final RadioButton rb8 = new RadioButton();

        Label armLabel = new Label("Enable/Disable");
        Label armLabel2 = new Label("Enable/Disable");
        Label startText = new Label("Start");
        Label finishText = new Label("Finish");
        Label lCh1 = new Label("  1");
        Label lCh2 = new Label("  2");
        Label lCh3 = new Label("  3");
        Label lCh4 = new Label("  4");
        Label lCh5 = new Label("  5");
        Label lCh6 = new Label("  6");
        Label lCh7 = new Label("  7");
        Label lCh8 = new Label("  8");

        lCh1.setId("label-bright");
        lCh2.setId("label-bright");
        lCh3.setId("label-bright");
        lCh4.setId("label-bright");
        lCh5.setId("label-bright");
        lCh6.setId("label-bright");
        lCh7.setId("label-bright");
        lCh8.setId("label-bright");

        ch1.setId("start-toggle-button");
        ch3.setId("start-toggle-button");
        ch5.setId("start-toggle-button");
        ch7.setId("start-toggle-button");
        ch2.setId("finish-toggle-button");
        ch4.setId("finish-toggle-button");
        ch6.setId("finish-toggle-button");
        ch8.setId("finish-toggle-button");

        channelGrid.add(lCh1,1,0);
        channelGrid.add(lCh3,2,0);
        channelGrid.add(lCh5,3,0);
        channelGrid.add(lCh7,4,0);

        channelGrid.add(startText,0,1);
        channelGrid.add(ch1, 1,1);
        channelGrid.add(ch3, 2, 1);
        channelGrid.add(ch5, 3, 1);
        channelGrid.add(ch7,4,1);

        channelGrid.add(armLabel,0,2);
        channelGrid.add(rb1,1,2);
        channelGrid.add(rb3,2,2);
        channelGrid.add(rb5,3,2);
        channelGrid.add(rb7,4,2);

        channelGrid.add(lCh2,1,3);
        channelGrid.add(lCh4,2,3);
        channelGrid.add(lCh6,3,3);
        channelGrid.add(lCh8,4,3);

        channelGrid.add(finishText,0,4);
        channelGrid.add(ch2, 1 ,4 );
        channelGrid.add(ch4, 2,4);
        channelGrid.add(ch6,3,4);
        channelGrid.add(ch8,4,4);

        channelGrid.add(armLabel2,0,5);
        channelGrid.add(rb2,1,5);
        channelGrid.add(rb4,2,5);
        channelGrid.add(rb6,3,5);
        channelGrid.add(rb8,4,5);

        /////////////////     LISTENERS     ////////////////////////
        rb1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb1.isSelected())
                    isArmed[0] = true;
                else
                    isArmed[0] = false;
            }
        });
        rb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb2.isSelected())
                    isArmed[1] = true;
                else
                    isArmed[1] = false;
            }
        });
        rb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb3.isSelected())
                    isArmed[2] = true;
                else
                    isArmed[2] = false;
            }
        });
        rb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb4.isSelected())
                    isArmed[3] = true;
                else
                    isArmed[3] = false;
            }
        });
        rb5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb5.isSelected())
                    isArmed[4] = true;
                else
                    isArmed[4] = false;
            }
        });
        rb6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb6.isSelected())
                    isArmed[5] = true;
                else
                    isArmed[5] = false;
            }
        });
        rb7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb7.isSelected())
                    isArmed[6] = true;
                else
                    isArmed[6] = false;
            }
        });
        rb8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb8.isSelected())
                    isArmed[7] = true;
                else
                    isArmed[7] = false;
            }
        });

        ch1.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[0]) {
                    if (ch1.isSelected())
                        ch1.setSelected(true);
                    else
                        ch1.setSelected(false);
                }
                else
                    ch1.setSelected(false);
            }
        });
        ch2.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[1]) {
                    if (ch2.isSelected())
                        ch2.setSelected(true);
                    else
                        ch2.setSelected(false);
                }
                else
                    ch2.setSelected(false);
            }
        });
        ch3.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[2]) {
                    if (ch3.isSelected())
                        ch3.setSelected(true);
                    else
                        ch3.setSelected(false);
                }
                else
                    ch3.setSelected(false);
            }
        });
        ch4.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[3]) {
                    if (ch4.isSelected())
                        ch4.setSelected(true);
                    else
                        ch4.setSelected(false);
                }
                else
                    ch4.setSelected(false);
            }
        });
        ch5.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[4]) {
                    if (ch5.isSelected())
                        ch5.setSelected(true);
                    else
                        ch5.setSelected(false);
                }
                else
                    ch5.setSelected(false);
            }
        });
        ch6.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[5]) {
                    if (ch6.isSelected())
                        ch6.setSelected(true);
                    else
                        ch6.setSelected(false);
                }
                else
                    ch6.setSelected(false);
            }
        });
        ch7.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[6]) {
                    if (ch7.isSelected())
                        ch7.setSelected(true);
                    else
                        ch7.setSelected(false);
                }
                else
                    ch7.setSelected(false);
            }
        });
        ch8.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                if (isArmed[7]) {
                    if (ch8.isSelected())
                        ch8.setSelected(true);
                    else
                        ch8.setSelected(false);
                }
                else
                    ch8.setSelected(false);
            }
        });
        ////////    END CHANNEL GRID ///////////////////

        //////////////  SHAPES /////////////////////////////
        Rectangle display = new Rectangle(300,260);
        display.setId("display");
        display.setTranslateX(0);
        display.setTranslateY(130);

        Rectangle innerDisplay = new Rectangle(266,216);
        innerDisplay.setStyle("-fx-fill: #6a9bd1");
        innerDisplay.setTranslateX(0);
        innerDisplay.setTranslateY(120);

        Rectangle printerShape1 = new Rectangle(180,100);
        printerShape1.setId("printer-wider");
        printerShape1.setTranslateX(300);
        printerShape1.setTranslateY(-90);

        Rectangle printerShape2 = new Rectangle(120,140);
        printerShape2.setId("printer-narrow");
        printerShape2.setTranslateX(310);
        printerShape2.setTranslateY(-120);

        Rectangle printerShadow = new Rectangle(192,100);
        printerShadow.setStyle("-fx-fill: #5e7da5");
        printerShadow.setTranslateX(307);
        printerShadow.setTranslateY(-80);

        Rectangle fnPanelShadow  = new Rectangle(244,210);
        fnPanelShadow.setId("display");
        fnPanelShadow.setTranslateX(-314);
        fnPanelShadow.setTranslateY(126);

        final Rectangle cover  = new Rectangle(1000,700);
        cover.setStyle("-fx-fill: #051930");
        /////////////// END SHAPES  ////////////////////////////
        // Power Button
        final Button pwrButton = new Button();
        pwrButton.setId("waiting-button");
        pwrButton.setText("POWER");
        pwrButton.setTranslateX(-316);
        pwrButton.setTranslateY(-254);

        root.getChildren().add(display);
        root.getChildren().add(innerDisplay);
        root.getChildren().add(fnPanelShadow);
        root.getChildren().add(printerShadow);
        root.getChildren().add(printerShape1);
        root.getChildren().add(printerShape2);
        root.getChildren().add(upArrow);
        root.getChildren().add(downArrow);
        root.getChildren().add(leftArrow);
        root.getChildren().add(rightArrow);
        root.getChildren().add(channelGrid);
        root.getChildren().add(grid);
        root.getChildren().add(printerButton);
        root.getChildren().add(fnButton);
        root.getChildren().add(displayLabel);
        root.getChildren().add(swapButton);
        root.getChildren().add(screen);
        root.getChildren().add(cover);
        root.getChildren().add(pwrButton);
        root.getChildren().add(sceneTitle);

        final FadeTransition rt = new FadeTransition(Duration.millis(3000), cover);
        final boolean[] isOn = {false};
        pwrButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isOn[0]) {
                    pwrButton.setId("");
                    rt.setFromValue(1.0);
                    rt.setToValue(0.0);
                    rt.play();
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    root.getChildren().remove(cover);
                                }
                            }));
                    timeline.play();
                    isOn[0] = true;
                }
                else {
                    pwrButton.setId("waiting-button");
                    root.getChildren().add(17,cover);
                    rt.setToValue(1.0);
                    rt.setFromValue(0.0);
                    rt.play();
                    isOn[0] = false;
                }
            }
        });

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Simulator sim = new Simulator();
        //sim.listen();
        launch(args);
    }
}
