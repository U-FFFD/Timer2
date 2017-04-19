import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        final StackPane rearPanel = new StackPane();
        Scene scene2 = new Scene(rearPanel, 900, 200);
        final Stage stage = new Stage();
        stage.setTitle("New Rear View");
        stage.setScene(scene2);
        rearPanel.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        rearPanel.setStyle("-fx-background-color:#1565C0");

        final Rectangle cover2 = new Rectangle(1000, 700);
        cover2.setStyle("-fx-fill: #051930");
    /*////////////////////////////////////////////////////////
                        CHANNEL GRID
     *///////////////////////////////////////////////////////
        GridPane connectionGrid = new GridPane();
        connectionGrid.setAlignment(Pos.CENTER);
        connectionGrid.setHgap(12);
        connectionGrid.setVgap(6);
        connectionGrid.setMaxSize(300, 206);
        connectionGrid.setTranslateX(-190);
        connectionGrid.setTranslateY(0);
        connectionGrid.setStyle("-fx-background-color: #3c7cc4;");

        final RadioButton cn1 = new RadioButton();
        final RadioButton cn2 = new RadioButton();
        final RadioButton cn3 = new RadioButton();
        final RadioButton cn4 = new RadioButton();
        final RadioButton cn5 = new RadioButton();
        final RadioButton cn6 = new RadioButton();
        final RadioButton cn7 = new RadioButton();
        final RadioButton cn8 = new RadioButton();

        Label channelLabel = new Label("Channel");
        Label channelLabel2 = new Label("Channel");
        Label cnL1 = new Label("  1");
        Label cnL2 = new Label("  2");
        Label cnL3 = new Label("  3");
        Label cnL4 = new Label("  4");
        Label cnL5 = new Label("  5");
        Label cnL6 = new Label("  6");
        Label cnL7 = new Label("  7");
        Label cnL8 = new Label("  8");

        cnL1.setId("label-bright");
        cnL2.setId("label-bright");
        cnL3.setId("label-bright");
        cnL4.setId("label-bright");
        cnL5.setId("label-bright");
        cnL6.setId("label-bright");
        cnL7.setId("label-bright");
        cnL8.setId("label-bright");

        connectionGrid.add(channelLabel, 0, 0);
        connectionGrid.add(cnL1, 1, 0);
        connectionGrid.add(cnL3, 2, 0);
        connectionGrid.add(cnL5, 3, 0);
        connectionGrid.add(cnL7, 4, 0);

        connectionGrid.add(cn1, 1, 1);
        connectionGrid.add(cn3, 2, 1);
        connectionGrid.add(cn5, 3, 1);
        connectionGrid.add(cn7, 4, 1);

        connectionGrid.add(cnL2, 1, 2);
        connectionGrid.add(cnL4, 2, 2);
        connectionGrid.add(cnL6, 3, 2);
        connectionGrid.add(cnL8, 4, 2);

        connectionGrid.add(channelLabel2, 0, 3);
        connectionGrid.add(cn2, 1, 3);
        connectionGrid.add(cn4, 2, 3);
        connectionGrid.add(cn6, 3, 3);
        connectionGrid.add(cn8, 4, 3);

        /////////////////     LISTENERS     ////////////////////////

        Rectangle usbShape1 = new Rectangle(54, 20);
        usbShape1.setStyle("-fx-fill: #383838");
        usbShape1.setTranslateX(200);
        usbShape1.setTranslateY(-40);

        Rectangle usbShape2 = new Rectangle(40, 5);
        usbShape2.setStyle("-fx-fill: #a8a8a8");
        usbShape2.setTranslateX(201);
        usbShape2.setTranslateY(-42);

        Rectangle usbBackground = new Rectangle(130, 50);
        usbBackground.setId("display");
        usbBackground.setTranslateX(178);
        usbBackground.setTranslateY(-40);

        Label usbLabel = new Label();
        usbLabel.setText("USB");
        usbLabel.setId("screen-text");
        usbLabel.setTranslateX(142);
        usbLabel.setTranslateY(-40);

        rearPanel.getChildren().add(usbBackground);
        rearPanel.getChildren().add(usbLabel);
        rearPanel.getChildren().add(usbShape1);
        rearPanel.getChildren().add(usbShape2);
        rearPanel.getChildren().add(connectionGrid);
        rearPanel.getChildren().add(cover2);
        stage.setY(740.0);
        stage.setX(500.0);





















        final StackPane root = new StackPane();
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("ChronoTimer 3.00");
        final ChronoTimer timer = new ChronoTimer();
        final String[] state = new String[1];
        state[0] = "BASE";
        final int[] lastCmdLength = {0};

        // Title
        final Text sceneTitle = new Text("Chrono Timer");
        sceneTitle.setId("fancytext");
        sceneTitle.setTranslateX(0);
        sceneTitle.setTranslateY(-260);

        // Swap Button
        Button swapButton = new Button();
        swapButton.setText("SWAP");
        swapButton.setTranslateX(-314);
        swapButton.setTranslateY(200);
        swapButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                timer.swap();
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

        // Display Label
        Label displayLabel = new Label();
        displayLabel.setText("Queue   /   Running   /   Final Time");
        displayLabel.setTranslateX(0);
        displayLabel.setTranslateY(242);

        // Display text field
        final Text screen = new Text();
        screen.setId("screen-text");
        VBox vBox = new VBox();
        vBox.getChildren().add(screen);
        final ScrollPane scroll = new ScrollPane();
        scroll.setTranslateX(0);
        scroll.setMaxSize(260, 212);
        Scene scene = new Scene(scroll, 252, 212);
        scroll.setTranslateY(120);
        scroll.setStyle("-fx-background-color: #598abf");
        screen.wrappingWidthProperty().bind(scene.widthProperty());
        scroll.setFitToWidth(true);
        scroll.setContent(vBox);
        vBox.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                scroll.setVvalue((Double) newValue);
            }
        });

        // Printer
        final Text printerOut = new Text();
        printerOut.setId("printer-text");
        VBox printBox = new VBox();
        printBox.getChildren().add(printerOut);
        final ScrollPane printScroll = new ScrollPane();
        printScroll.setTranslateX(312);
        printScroll.setTranslateY(-120);
        printScroll.setMaxSize(130, 140);
        printScroll.setStyle("-fx-background-color: #f2ead7");
        Scene printerScene = new Scene(printScroll, 120, 140);
        printerOut.wrappingWidthProperty().bind(printerScene.widthProperty());
        printScroll.setFitToWidth(true);
        printScroll.setContent(printBox);
        printBox.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                printScroll.setVvalue((Double) newValue);
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
                //String enteredCmd = screen.getText().substring(0,screen.getText().length());
                String enteredCmd = theList.getCmd();
                switch (enteredCmd) {
                    case "EVENT":
                        theList.setMode("RACE_TYPE");
                        screen.setText(screen.getText() + "\n" + "Choose Mode:");
                        theList.isCmd = false;
                        break;
                    case "IND":
                        timer.setMode("IND");
                        screen.setText(screen.getText() + "\n" + "Mode set to IND");
                        theList.isCmd = false;
                        theList.setMode("BASE");
                        break;
                    case "PAR IND":
                        timer.setMode("PARIND");
                        screen.setText(screen.getText() + "\n" + "Mode set to PARIND");
                        theList.isCmd = false;
                        theList.setMode("BASE");
                        break;
                    case "GROUP":
                        timer.setMode("GRP");
                        screen.setText(screen.getText() + "\n" + "Mode set to GRP");
                        theList.isCmd = false;
                        theList.setMode("BASE");
                        break;
                    case "ADD RACERS":
                        state[0] = "ADD";
                        screen.setText(screen.getText() + "\n" + "Use num pad to input bib numbers \n('*' to enter id, '#' to finish adding)\n#");
                        theList.isCmd = false;
                        break;
                    case "SET TIME":
                        state[0] = "TIME_SET";
                        screen.setText(screen.getText() + "\n" + "Use num pad to enter time as hh:mm:ss \n('*' to set)");
                        theList.isCmd = false;
                        break;
                    case "NEW RUN":
                        timer.newRun();
                        break;
                    case "END RUN":
                        timer.endRun();
                        break;
                    case "EXPORT" :
                        //timer.export();
                        break;
                    case "PRINT":
                        timer.print();
                        printerOut.setText(timer.print());
                        break;
                    case "RESET" :
                        timer.reset();
                        break;
                    default:
                        break;
                }
            }
        });

        upArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scroll.setVvalue(scroll.getVvalue() - 0.1);
            }
        });
        downArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scroll.setVvalue(scroll.getVvalue() + 0.1);
            }
        });
        leftArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (theList.isCmd() == false) {
                    screen.setText(screen.getText() + "\n" + theList.right());
                    theList.isCmd = true;
                } else {
                    int offset = theList.getCmd().length();
                    screen.setText(screen.getText().substring(0, screen.getText().length() - offset) + theList.left());
                }
            }
        });
        rightArrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (theList.isCmd() == false) {
                    screen.setText(screen.getText() + "\n" + theList.right());
                    theList.isCmd = true;
                } else {
                    int offset = theList.getCmd().length();
                    screen.setText(screen.getText().substring(0, screen.getText().length() - offset) + theList.right());
                }
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
        grid.setMaxSize(220, 200);
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

        final String[] racer = {""};
        final String[] time = new String[1];
        time[0] = "";
        key1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "1";
                    screen.setText(screen.getText() + "1");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 1);
                        screen.setText(screen.getText() + 1);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "2";
                    screen.setText(screen.getText() + "2");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 2);
                        screen.setText(screen.getText() + 2);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "3";
                    screen.setText(screen.getText() + "3");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 3);
                        screen.setText(screen.getText() + 3);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "4";
                    screen.setText(screen.getText() + "4");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 4);
                        screen.setText(screen.getText() + 4);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "5";
                    screen.setText(screen.getText() + "5");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 5);
                        screen.setText(screen.getText() + 5);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "6";
                    screen.setText(screen.getText() + "6");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 6);
                        screen.setText(screen.getText() + 6);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "7";
                    screen.setText(screen.getText() + "7");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 7);
                        screen.setText(screen.getText() + 7);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "8";
                    screen.setText(screen.getText() + "8");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 8);
                        screen.setText(screen.getText() + 8);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "9";
                    screen.setText(screen.getText() + "9");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 9);
                        screen.setText(screen.getText() + 9);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        key0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    racer[0] = racer[0] + "0";
                    screen.setText(screen.getText() + "0");
                } else if (state[0].equals("TIME_SET")) {
                    if (time[0].length() < 8) {
                        time[0] = (time[0] + 0);
                        screen.setText(screen.getText() + 0);
                        if ((time[0].length() == 2) || (time[0].length() == 5)) {
                            time[0] = time[0] + ":";
                            screen.setText(screen.getText() + ":");
                        }
                    }
                }
            }
        });
        keyStar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (state[0].equals("ADD")) {
                    if ((!racer[0].contains("^[0-9]+$")) && !racer[0].equals("")) {
                        int i = Integer.parseInt(racer[0]);
                        timer.addRacer(i);
                    }
                    racer[0] = "";
                    screen.setText(screen.getText() + "\n#");
                } else if (state[0].equals("TIME_SET")) {
                    timer.setTime(time[0]);
                }
            }
        });
        keyPound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state[0] = "BASE";
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

        grid.add(key1, 0, 0);
        grid.add(key2, 1, 0);
        grid.add(key3, 2, 0);
        grid.add(key4, 0, 1);
        grid.add(key5, 1, 1);
        grid.add(key6, 2, 1);
        grid.add(key7, 0, 2);
        grid.add(key8, 1, 2);
        grid.add(key9, 2, 2);
        grid.add(key0, 1, 3);
        grid.add(keyStar, 0, 3);
        grid.add(keyPound, 2, 3);
        //////////////////// END BUTTON GRID ////////////////////

        /*////////////////////////////////////////////////////////
                        CHANNEL GRID
         *///////////////////////////////////////////////////////
        final boolean[] isArmed = new boolean[8];
        GridPane channelGrid = new GridPane();
        channelGrid.setAlignment(Pos.CENTER);
        channelGrid.setHgap(12);
        channelGrid.setVgap(6);
        channelGrid.setMaxSize(300, 206);
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

        channelGrid.add(lCh1, 1, 0);
        channelGrid.add(lCh3, 2, 0);
        channelGrid.add(lCh5, 3, 0);
        channelGrid.add(lCh7, 4, 0);

        channelGrid.add(startText, 0, 1);
        channelGrid.add(ch1, 1, 1);
        channelGrid.add(ch3, 2, 1);
        channelGrid.add(ch5, 3, 1);
        channelGrid.add(ch7, 4, 1);

        channelGrid.add(armLabel, 0, 2);
        channelGrid.add(rb1, 1, 2);
        channelGrid.add(rb3, 2, 2);
        channelGrid.add(rb5, 3, 2);
        channelGrid.add(rb7, 4, 2);

        channelGrid.add(lCh2, 1, 3);
        channelGrid.add(lCh4, 2, 3);
        channelGrid.add(lCh6, 3, 3);
        channelGrid.add(lCh8, 4, 3);

        channelGrid.add(finishText, 0, 4);
        channelGrid.add(ch2, 1, 4);
        channelGrid.add(ch4, 2, 4);
        channelGrid.add(ch6, 3, 4);
        channelGrid.add(ch8, 4, 4);

        channelGrid.add(armLabel2, 0, 5);
        channelGrid.add(rb2, 1, 5);
        channelGrid.add(rb4, 2, 5);
        channelGrid.add(rb6, 3, 5);
        channelGrid.add(rb8, 4, 5);

        /////////////////     LISTENERS     ////////////////////////
        rb1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("1");
            }
        });
        rb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("2");
            }
        });
        rb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("3");
            }
        });
        rb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("4");
            }
        });
        rb5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("5");
            }
        });
        rb6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("6");
            }
        });
        rb7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("7");
            }
        });
        rb8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.toggleChannel("8");
            }
        });

        ch1.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("1");
            }
        });
        ch2.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("2");
            }
        });
        ch3.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("3");
            }
        });
        ch4.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("4");
            }
        });
        ch5.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("5");
            }
        });
        ch6.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("6");
            }
        });
        ch7.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("7");
            }
        });
        ch8.setOnAction(new EventHandler<ActionEvent>() { //WORKS
            @Override
            public void handle(ActionEvent event) {
                timer.triggerChannel("8");
            }
        });
        ////////    END CHANNEL GRID ///////////////////

        //////////////  SHAPES /////////////////////////////
        Rectangle display = new Rectangle(300, 260);
        display.setId("display");
        display.setTranslateX(0);
        display.setTranslateY(130);

        Rectangle innerDisplay = new Rectangle(266, 216);
        innerDisplay.setStyle("-fx-fill: #598abf");
        innerDisplay.setTranslateX(0);
        innerDisplay.setTranslateY(120);

        Rectangle printerShape1 = new Rectangle(180, 100);
        printerShape1.setId("printer-wider");
        printerShape1.setTranslateX(300);
        printerShape1.setTranslateY(-90);

        Rectangle printerShape2 = new Rectangle(120, 140);
        printerShape2.setId("printer-narrow");
        printerShape2.setTranslateX(310);
        printerShape2.setTranslateY(-120);

        Rectangle printerShadow = new Rectangle(192, 100);
        printerShadow.setStyle("-fx-fill: #5e7da5");
        printerShadow.setTranslateX(307);
        printerShadow.setTranslateY(-80);

        Rectangle fnPanelShadow = new Rectangle(244, 210);
        fnPanelShadow.setId("display");
        fnPanelShadow.setTranslateX(-314);
        fnPanelShadow.setTranslateY(126);

        final Rectangle cover = new Rectangle(1000, 700);
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
        root.getChildren().add(swapButton);
        root.getChildren().add(printScroll);
        root.getChildren().add(scroll);
        root.getChildren().add(displayLabel);
        root.getChildren().add(cover);
        root.getChildren().add(pwrButton);
        root.getChildren().add(sceneTitle);

        final FadeTransition rt = new FadeTransition(Duration.millis(3000), cover);
        final boolean[] isOn = {false};
        pwrButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isOn[0]) {
                    timer.power();
                    pwrButton.setId("");
                    rt.setFromValue(1.0);
                    rt.setToValue(0.0);
                    rt.play();
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    rearPanel.getChildren().remove(cover2);
                                    root.getChildren().remove(cover);
                                }
                            }));
                    timeline.play();
                    stage.show();
                    isOn[0] = true;
                } else {
                    pwrButton.setId("waiting-button");
                    root.getChildren().add(18, cover);
                    rt.setToValue(1.0);
                    rt.setFromValue(0.0);
                    rt.play();
                    isOn[0] = false;
                    stage.hide();
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
