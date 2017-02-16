/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
/**
 *
 * @author reillyt
 */
public class Bingo extends Application {
    
    Stage rootStage;
    
    class BingoNumberRowCol {
        public int m_row;
        public int m_col;
        
        public BingoNumberRowCol(int row, int col) {
            m_row = row;
            m_col = col;
        }
    }
    
    BingoNumberRowCol[] bingoNumberRowCol;
    
    // Data model
    ObservableList<ObservableList<BingoNumber>> bingoNumberData;

    // Strings for Header Labels
    String hdrStrings[] = new String[5];

    // Bingo Number Labels
    Label bingoNumberLabels [][] = new Label[5][16];
    
    // Bingo Number Labels
    Label checkCardNumberLabels [][] = new Label[5][6];
    
    
    GridPane calledNumbersGridPane;
    GridPane checkCardGridPane;
    
    ColumnConstraints col1Constraints;
    ColumnConstraints col2Constraints;
    RowConstraints row1Constraints;
    RowConstraints row2Constraints;

    Font calledNumbersFont;
    
    Font nextNumberButtonFont;
    Font nextNumberLabelFont;
    
    Font nextNumberChkBoxFont;
    
    Font checkCardButtonFont;
    Font checkCardSpinnerFont;
    Font checkCardGridFont;
    
    Font cardSeriesLabelFont;
    Font cardSeriesDataLabelFont;
    
    Font numberOfCardsLabelFont;
    Font numberOfCardsDataLabelFont;
    
    Font cardSeriesCreatorLabelFont;
    Font cardSeriesCreatorDataLabelFont;
    
    Font cardSeriesCreationDateLabelFont;
    Font cardSeriesCreationDateDataLabelFont;
    
    private DoubleProperty fontSize = new SimpleDoubleProperty(40);
    
    Scene scene;
    
    VBox leftVBox;
    VBox rightVBox;
    HBox contentPane;
    BorderPane rootPane;
    
    Button nextNumberButton;
    Label nextNumberLabel;
    
    Button checkCardButton;
    Spinner<Integer> checkCardSpinner;
    RestrictiveTextField integerTextField;
    
    ChoiceBox<String> bingoPatternChoiceBox;
    VBox bingoPatternVBox;
    HBox bingoPatternImageHBox;
    HBox bingoPatternContentHBox;
    
    HBox nextNumberHBox;
    HBox checkCardHBox;
    VBox checkCardVBox;
    
    CheckBox bChkBox;
    CheckBox iChkBox;
    CheckBox nChkBox;
    CheckBox gChkBox;
    CheckBox oChkBox;

    HBox bingoChkBoxHBox;

    VBox nextNumberVBox;
    
    Label cardSeriesLabel;
    Label cardSeriesDataLabel;
    Label numberOfCardsLabel;
    Label numberOfCardsDataLabel;
    Label cardSeriesCreatorLabel;
    Label cardSeriesCreatorDataLabel;
    Label cardSeriesCreationDateLabel;
    Label cardSeriesCreationDateDataLabel;
    
    HBox cardSeriesHBox;
    HBox numberOfCardsHBox;
    HBox cardSeriesCreatorHBox;
    HBox creationDateHBox;
    VBox cardSeriesVBox;
    HBox cardSeriesContentHBox;
    
    int secondPreviousNumberCalled;
    int firstPreviousNumberCalled;
    int lastNumberCalled;
    
    ShuffledIntegers shuffledInts;
    
    MenuBar menuBar;
    
    Menu fileMenu;
    MenuItem quitBingoItem;
        
    Menu gameMenu;
    MenuItem newGameItem;
        
    Menu cardsMenu;
    MenuItem newCardsItem;
    MenuItem saveCardsItem;
    MenuItem loadCardsItem;
    MenuItem printCardsItem;

    CardSeriesType bingoCardSeries;

    WinningCard winningCard;
    
    String bingoGamePattern = "AnyDiagonalRowOrColumn";
   
    int firstCardNumber = 1000;
    int midCardNumber = 1128;
    int lastCardNumber = 1255;

    Image bingoPatternImage1;
    Image bingoPatternImage2;
    Image bingoPatternImage3;
    Image bingoPatternImage4;

    ImageView bingoPatternImageView1;
    ImageView bingoPatternImageView2;
    ImageView bingoPatternImageView3;
    ImageView bingoPatternImageView4;

    DialogData newCardsData = new DialogData();

    JAXBContext jc;
    Marshaller cardSeriesMarshaller;
    Unmarshaller cardSeriesUnmarshaller;
    ObjectFactory objFactory;
    JAXBElement<CardSeriesType> root;

    public class DialogData {
        public String creatorName;
        public Integer numberOfCards;
        public String seriesName;
    }

    public void loadCards() {
        try {
            JAXBElement<CardSeriesType> obj = null;
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Card Series", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(rootStage);
            if (selectedFile != null) {
                bingoCardSeries = cardSeriesUnmarshaller.unmarshal(new StreamSource(selectedFile), CardSeriesType.class).getValue();
            }
            updateCardSeriesInfo();
            updateIntegerTextField();
        }
        catch (JAXBException exc) {
            System.out.println(exc);
        }
        System.out.println();
    }
    
    public void saveCards() {
        String absPath;
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(rootStage);
        if (selectedDirectory != null) {
            absPath = selectedDirectory.getAbsolutePath();
            try (FileWriter fw = new FileWriter(absPath + File.separator + bingoCardSeries.getSeriesId() + ".xml")) {
                cardSeriesMarshaller.marshal(root, fw);
            }
            catch (JAXBException | IOException exc) {
                System.out.println(exc);
            }
        }
    }
    
    public void newCards() {
        
        GridPane grid;
        TextField creatorName;
        Label creatorNameLabel;
        ComboBox<Integer> numberOfCards;
        Label d_numberOfCardsLabel;
        TextField seriesName;
        Label seriesNameLabel;
        
        Dialog<DialogData> dialog = new Dialog<>();
        dialog.setTitle("New Card Series");
        
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        creatorName = new TextField();
        creatorName.setPromptText("Creator Name:");
        creatorNameLabel = new Label("Creator Name:");
        
        ObservableList<Integer> data = FXCollections.observableArrayList();
        data.addAll(8, 16, 32, 64, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 3072, 4096);
        numberOfCards = new ComboBox<>();
        numberOfCards.itemsProperty().setValue(data);
        numberOfCards.setValue(data.get(6));
        d_numberOfCardsLabel = new Label("Number of Cards:");

        seriesName = new TextField();
        seriesName.setPromptText("Series Name:");
        seriesNameLabel = new Label("Series Name:");

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setHalignment(creatorNameLabel, HPos.RIGHT);
        GridPane.setHalignment(d_numberOfCardsLabel, HPos.RIGHT);
        GridPane.setHalignment(seriesNameLabel, HPos.RIGHT);

        grid.add(seriesNameLabel, 0, 0);
        grid.add(seriesName, 1, 0);
        grid.add(d_numberOfCardsLabel, 0, 1);
        grid.add(numberOfCards, 1, 1);
        grid.add(creatorNameLabel, 0, 2);
        grid.add(creatorName, 1, 2);
        
        dialog.getDialogPane().setContent(grid);
        
        Platform.runLater(() -> seriesName.requestFocus());
                
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                newCardsData.seriesName = seriesName.getText();
                newCardsData.numberOfCards = (Integer)numberOfCards.getSelectionModel().getSelectedItem();
                newCardsData.creatorName = creatorName.getText();
                return newCardsData;
            }
            newCardsData.numberOfCards = 0;
            return null;
        });
        
        dialog.showAndWait();
        
        if (newCardsData.numberOfCards != 0) {
            cardSeries(newCardsData.seriesName, newCardsData.creatorName, newCardsData.numberOfCards);
            updateCardSeriesInfo();
            updateIntegerTextField();
        }
    }
    
    private void createMenus() {

        fileMenu = new Menu("File");
        quitBingoItem = new MenuItem("Quit Bingo");
        quitBingoItem.setOnAction((event) -> {Platform.exit();});
        fileMenu.getItems().addAll(quitBingoItem);
        
        gameMenu = new Menu("Game");
        newGameItem = new MenuItem("New Game");
        newGameItem.setOnAction((event) -> {newGame();});
        gameMenu.getItems().addAll(newGameItem);
        
        cardsMenu = new Menu("Cards");
        newCardsItem = new MenuItem("New Cards");
        newCardsItem.setOnAction((event) -> {newCards();});
        saveCardsItem = new MenuItem("Save Cards");
        saveCardsItem.setOnAction((event) -> {saveCards();});
        loadCardsItem = new MenuItem("Load Cards");
        loadCardsItem.setOnAction((event) -> {loadCards();});
        printCardsItem = new MenuItem("Print Cards to HTML");
        printCardsItem.setOnAction((event) -> {printCardsToFile();});
        cardsMenu.getItems().addAll(newCardsItem, saveCardsItem, loadCardsItem, printCardsItem);
        
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, cardsMenu);
        menuBar.setUseSystemMenuBar(true);
                
    //    gameMenu = menuBar()->addMenu(tr("&Game"));
    //    gameMenu->addAction(newGameAction);
    //    gameMenu->addAction(aboutAction);
    //    gameMenu->addAction(preferencesAction);

    //    cardsMenu = menuBar()->addMenu(tr("&Cards"));
    //    cardsMenu->addAction(newCardsAction);
    //    cardsMenu->addAction(loadCardsAction);
    //    cardsMenu->addAction(saveCardsAction);
    //    cardsMenu->addAction(printCardsToFileAction);

    //    helpMenu = menuBar()->addMenu(tr("&Help"));
    //    helpMenu->addAction(helpAction);
    }

    private void initBingo(Stage stage) {

        rootStage = stage;

        bingoCardSeries = new CardSeriesType();
        cardSeries("DefaultSeries-256", "Timothy P. Reilly", 256);
        
        winningCard = new WinningCard();
        
        // Data model
        bingoNumberData = FXCollections.<ObservableList<BingoNumber>>observableArrayList();

        // Initialize data model
        for (int i = 0; i < 5; i++) {
            final ObservableList<BingoNumber> col = FXCollections.<BingoNumber>observableArrayList();
            bingoNumberData.add(i, col);
            for (int j = 1; j <= 15; j++) {
                col.add(new BingoNumber((15*i)+j, NumberCallState.NotCalled));
            }
        }
        try {
            jc = JAXBContext.newInstance(CardSeriesType.class, CardType.class, ObjectFactory.class);
            cardSeriesMarshaller = jc.createMarshaller();
            cardSeriesMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            cardSeriesUnmarshaller = jc.createUnmarshaller();
            cardSeriesUnmarshaller.setSchema(null);
            objFactory = new ObjectFactory();
            root = objFactory.createCardSeries(bingoCardSeries);
        }
        catch (JAXBException exc) {
            
        }
        
        // Strings for Header Labels
        hdrStrings[0] = "B";
        hdrStrings[1] = "I";
        hdrStrings[2] = "N";
        hdrStrings[3] = "G";
        hdrStrings[4] = "O";
 
        calledNumbersFont = new Font("Calibri", 40);
        
        // Initialize Bingo Number Labels
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 16; row++) {
                if (0 == row) {
                    bingoNumberLabels[col][row] = new Label(hdrStrings[col]);
                    bingoNumberLabels[col][row].setFont(calledNumbersFont);
                    bingoNumberLabels[col][row].setStyle("-fx-font-weight: bolder");
                }
                else
                {
                    bingoNumberLabels[col][row] =
                        new Label(Integer.toString(bingoNumberData.get(col).get(row-1).getBingoNumber()));
                    bingoNumberLabels[col][row].setFont(calledNumbersFont);
                    bingoNumberLabels[col][row].setStyle("-fx-text-fill: lightgrey");
                }
            }
        }

        bingoNumberRowCol = new BingoNumberRowCol[76];
        
        for (int i = 1; i < 76; i++) {
            bingoNumberRowCol[i] = new BingoNumberRowCol(Math.floorMod(i-1, 15), (i-1)/15);
        }

        calledNumbersGridPane = new GridPane();
        calledNumbersGridPane.autosize();
        calledNumbersGridPane.setGridLinesVisible(true);

        col1Constraints = new ColumnConstraints();
        col1Constraints.setHalignment(HPos.CENTER);
        col1Constraints.setMinWidth(57.0);
        col1Constraints.setPercentWidth(20.0);
        
        row1Constraints = new RowConstraints();
        row1Constraints.setPercentHeight(100.0/16.0);
        row1Constraints.setValignment(VPos.CENTER);
        
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 16; row++) {
                calledNumbersGridPane.add(bingoNumberLabels[col][row], col, row);
            }
        }
        for (int col = 0; col < 5; col++) {
            calledNumbersGridPane.getColumnConstraints().add(col, col1Constraints);
        }
        for (int row = 0; row < 16; row++) {
            calledNumbersGridPane.getRowConstraints().add(row, row1Constraints);
        }

        nextNumberButtonFont = new Font("Calibri", 14);

        nextNumberButton = new Button("Next Number");
        nextNumberButton.setFont(nextNumberButtonFont);
        nextNumberButton.setOnAction((event) -> {nextNumber();});
        
        nextNumberLabelFont = new Font("Calibri", 64);

        nextNumberLabel = new Label("");
        //nextNumberLabel.setPrefWidth(185);
        nextNumberLabel.setMinWidth(185);
        nextNumberLabel.setFont(nextNumberLabelFont);
        nextNumberLabel.setStyle("-fx-text-fill: red; -fx-padding: 0 0 0 20");
        
        nextNumberHBox = new HBox(nextNumberButton, nextNumberLabel);
        nextNumberHBox.setAlignment(Pos.CENTER);
        nextNumberHBox.setStyle("-fx-padding: 5");
        nextNumberHBox.setSpacing(10.0);
        //nextNumberHBox.setPrefWidth(350);
        nextNumberHBox.setMinWidth(350);
        nextNumberHBox.autosize();

        nextNumberChkBoxFont = new Font("Calibri", 14);

        bChkBox = new CheckBox(hdrStrings[0]);
        iChkBox = new CheckBox(hdrStrings[1]);
        nChkBox = new CheckBox(hdrStrings[2]);
        gChkBox = new CheckBox(hdrStrings[3]);
        oChkBox = new CheckBox(hdrStrings[4]);

        bChkBox.setFont(nextNumberChkBoxFont);
        iChkBox.setFont(nextNumberChkBoxFont);
        nChkBox.setFont(nextNumberChkBoxFont);
        gChkBox.setFont(nextNumberChkBoxFont);
        oChkBox.setFont(nextNumberChkBoxFont);

        bChkBox.setSelected(true);
        iChkBox.setSelected(true);
        nChkBox.setSelected(true);
        gChkBox.setSelected(true);
        oChkBox.setSelected(true);

        bingoChkBoxHBox = new HBox(bChkBox, iChkBox, nChkBox, gChkBox, oChkBox);
        bingoChkBoxHBox.setAlignment(Pos.CENTER);
        bingoChkBoxHBox.setStyle("-fx-padding: 5");
        bingoChkBoxHBox.setSpacing(20.0);
        //bingoChkBoxHBox.setPrefWidth(350);
        bingoChkBoxHBox.setMinWidth(350);
        bingoChkBoxHBox.autosize();

        nextNumberVBox = new VBox(nextNumberHBox, bingoChkBoxHBox);
        nextNumberVBox.setStyle("-fx-border-color: black; -fx-padding: 18, 5, 18, 5");
        nextNumberVBox.setSpacing(10.0);
        //nextNumberVBox.setPrefWidth(350);
        nextNumberVBox.setMinWidth(350);
        nextNumberVBox.autosize();

        leftVBox = new VBox(calledNumbersGridPane);
        leftVBox.setStyle("-fx-padding: 5; -fx-spacing: 5");
        leftVBox.autosize();
        
        integerTextField = new RestrictiveTextField();
        integerTextField.setFont(checkCardSpinnerFont);
        integerTextField.setMaxLength(4);
        integerTextField.setRestrict("[0-9]");
        integerTextField.setPrefWidth(50.0);
        integerTextField.setText(Integer.toString(midCardNumber));

        checkCardButtonFont = new Font("Calibri", 14);
        checkCardSpinnerFont = new Font("Calibri", 14);
        checkCardGridFont = new Font("Calibri", 40);
        
        checkCardButton = new Button("Check Card");
        checkCardButton.setFont(checkCardButtonFont);
        checkCardButton.autosize();
        checkCardButton.setOnAction((event) -> {checkCard();});
        
        // Initialize Check Card Number Labels
        List<Integer> bNums = bingoCardSeries.getCard().get(midCardNumber-1000).getBNumbers();
        List<Integer> iNums = bingoCardSeries.getCard().get(midCardNumber-1000).getINumbers();
        List<Integer> nNums = bingoCardSeries.getCard().get(midCardNumber-1000).getNNumbers();
        List<Integer> gNums = bingoCardSeries.getCard().get(midCardNumber-1000).getGNumbers();
        List<Integer> oNums = bingoCardSeries.getCard().get(midCardNumber-1000).getONumbers();
        
        for (int row = 0; row < 6; row++) {
            if (0 == row) {
                for (int col = 0; col < 5; col++) {
                    checkCardNumberLabels[col][0] = new Label(hdrStrings[col]);
                    checkCardNumberLabels[col][0].setFont(checkCardGridFont);
                    checkCardNumberLabels[col][0].setStyle("-fx-font-weight: bolder");
                }
            }
            else
            {
                // Handle bNums
                checkCardNumberLabels[0][row] = new Label(bNums.get(row-1).toString());
                checkCardNumberLabels[0][row].setFont(checkCardGridFont);
                checkCardNumberLabels[0][row].setStyle("-fx-text-fill: lightgrey");
                
                // Handle iNums
                checkCardNumberLabels[1][row] = new Label(iNums.get(row-1).toString());
                checkCardNumberLabels[1][row].setFont(checkCardGridFont);
                checkCardNumberLabels[1][row].setStyle("-fx-text-fill: lightgrey");
                
                if (3 == row)
                {
                    // Handle nNums
                    checkCardNumberLabels[2][row] = new Label("F");
                    checkCardNumberLabels[2][row].setFont(checkCardGridFont);
                    checkCardNumberLabels[2][row].setStyle("-fx-text-fill: red");
                }
                else
                {
                    // Handle nNums
                    checkCardNumberLabels[2][row] = new Label(nNums.get(row-1).toString());
                    checkCardNumberLabels[2][row].setFont(checkCardGridFont);
                    checkCardNumberLabels[2][row].setStyle("-fx-text-fill: lightgrey");
                }

                
                // Handle gNums
                checkCardNumberLabels[3][row] = new Label(gNums.get(row-1).toString());
                checkCardNumberLabels[3][row].setFont(checkCardGridFont);
                checkCardNumberLabels[3][row].setStyle("-fx-text-fill: lightgrey");
               
                // Handle oNums
                checkCardNumberLabels[4][row] = new Label(oNums.get(row-1).toString());
                checkCardNumberLabels[4][row].setFont(checkCardGridFont);
                checkCardNumberLabels[4][row].setStyle("-fx-text-fill: lightgrey");
            }
        }

        checkCardGridPane = new GridPane();
        checkCardGridPane.setGridLinesVisible(true);
        checkCardGridPane.setAlignment(Pos.CENTER);
        col2Constraints = new ColumnConstraints();
        col2Constraints.setHalignment(HPos.CENTER);
        col2Constraints.setMinWidth(57.0);
        col2Constraints.setPercentWidth(20.0);
        
        
        row2Constraints = new RowConstraints();
        row2Constraints.setPercentHeight(100.0/6.0);
        row2Constraints.setValignment(VPos.CENTER);
        for (int row = 0; row < 6; row++) {
            checkCardGridPane.getRowConstraints().add(row, row2Constraints);
        }

        for (int col = 0; col < 5; col++) {
        checkCardGridPane.getColumnConstraints().add(col, col2Constraints);
            for (int row = 0; row < 6; row++) {
                checkCardGridPane.add(checkCardNumberLabels[col][row], col, row);
            }
        }

        checkCardHBox = new HBox(checkCardButton, integerTextField);
        checkCardHBox.setAlignment(Pos.CENTER);
        checkCardHBox.setStyle("-fx-padding: 5");
        checkCardHBox.setSpacing(20.0);
        checkCardHBox.autosize();
        
        checkCardVBox = new VBox(checkCardHBox, checkCardGridPane);
        //checkCardVBox.setPrefHeight(350);
        checkCardVBox.setMinHeight(350);
        checkCardVBox.autosize();
        checkCardVBox.setAlignment(Pos.CENTER);
        checkCardVBox.setStyle("-fx-border-color: black; -fx-padding: 18, 5, 18, 5");

        bingoPatternChoiceBox = new ChoiceBox<>(winningCard.bingoPatternChoiceBoxItems);
        bingoPatternChoiceBox.autosize();
        bingoPatternChoiceBox.getSelectionModel().selectFirst();
        bingoPatternChoiceBox.getSelectionModel().selectedItemProperty()
                             .addListener((observable, oldValue, newValue) -> {
            bingoGamePattern = newValue;
            updateBingoPatternImages(bingoGamePattern);
        });
        
        bingoPatternChoiceBox.setStyle("-fx-font-size: 20px");
        
        bingoPatternImage1 = new Image("images/56/Row.png");
        bingoPatternImage2 = new Image("images/56/Column.png");
        bingoPatternImage3 = new Image("images/56/UpDiagonal.png");
        bingoPatternImage4 = new Image("images/56/ABlankCard.png");

        bingoPatternImageView1 = new ImageView(bingoPatternImage1);
        bingoPatternImageView2 = new ImageView(bingoPatternImage2);
        bingoPatternImageView3 = new ImageView(bingoPatternImage3);
        bingoPatternImageView4 = new ImageView(bingoPatternImage4);

        bingoPatternImageHBox = new HBox(bingoPatternImageView1,
                                         bingoPatternImageView2,
                                         bingoPatternImageView3,
                                         bingoPatternImageView4);
        bingoPatternImageHBox.setAlignment(Pos.CENTER);
        bingoPatternImageHBox.setSpacing(20.0);
        bingoPatternImageHBox.autosize();
        
        bingoPatternVBox = new VBox(bingoPatternChoiceBox,
                                    bingoPatternImageHBox);
        bingoPatternVBox.setAlignment(Pos.CENTER);
        bingoPatternVBox.setSpacing(5.0);

        bingoPatternContentHBox = new HBox(bingoPatternVBox);
        bingoPatternContentHBox.setStyle("-fx-border-color: black; -fx-padding: 18, 5, 18, 5");
        bingoPatternContentHBox.setAlignment(Pos.CENTER);
        bingoPatternContentHBox.autosize();
        
        cardSeriesLabelFont = new Font("Calibri", 14);
        cardSeriesDataLabelFont = new Font("Calibri", 14);
        numberOfCardsLabelFont = new Font("Calibri", 14);
        numberOfCardsDataLabelFont = new Font("Calibri", 14);
        cardSeriesCreatorLabelFont = new Font("Calibri", 14);
        cardSeriesCreatorDataLabelFont = new Font("Calibri", 14);
        cardSeriesCreationDateLabelFont = new Font("Calibri", 14);
        cardSeriesCreationDateDataLabelFont = new Font("Calibri", 14);
        
        cardSeriesLabel = new Label("Card Series: ");
        cardSeriesLabel.setFont(cardSeriesLabelFont);
        cardSeriesLabel.setStyle("-fx-font-weight: bold");
        cardSeriesDataLabel = new Label(bingoCardSeries.getSeriesId());
        cardSeriesDataLabel.setFont(cardSeriesDataLabelFont);

        numberOfCardsLabel = new Label("Number of Cards: ");
        numberOfCardsLabel.setStyle("-fx-font-weight: bold");
        numberOfCardsLabel.setFont(numberOfCardsLabelFont);
        numberOfCardsDataLabel = new Label(Integer.toString(bingoCardSeries.getCard().size()));
        numberOfCardsDataLabel.setFont(numberOfCardsDataLabelFont);

        cardSeriesCreatorLabel = new Label("Card Series Creator: ");
        cardSeriesCreatorLabel.setStyle("-fx-font-weight: bold");
        cardSeriesCreatorLabel.setFont(cardSeriesCreatorLabelFont);
        cardSeriesCreatorDataLabel = new Label(bingoCardSeries.getSeriesCreator());
        cardSeriesCreatorDataLabel.setFont(cardSeriesCreatorDataLabelFont);

        cardSeriesCreationDateLabel = new Label("Creation Date: ");
        cardSeriesCreationDateLabel.setStyle("-fx-font-weight: bold");
        cardSeriesCreationDateLabel.setFont(cardSeriesCreationDateLabelFont);
        cardSeriesCreationDateDataLabel = new Label(bingoCardSeries.getCreationDate());
        cardSeriesCreationDateDataLabel.setFont(cardSeriesCreationDateDataLabelFont);
        
        cardSeriesHBox = new HBox(cardSeriesLabel, cardSeriesDataLabel);
        cardSeriesHBox.autosize();
        numberOfCardsHBox = new HBox(numberOfCardsLabel, numberOfCardsDataLabel);
        numberOfCardsHBox.autosize();
        cardSeriesCreatorHBox = new HBox(cardSeriesCreatorLabel, cardSeriesCreatorDataLabel);
        cardSeriesCreatorHBox.autosize();
        creationDateHBox = new HBox(cardSeriesCreationDateLabel, cardSeriesCreationDateDataLabel);
        creationDateHBox.autosize();
        cardSeriesVBox = new VBox(cardSeriesHBox, numberOfCardsHBox, cardSeriesCreatorHBox, creationDateHBox);
        cardSeriesVBox.autosize();
        cardSeriesContentHBox = new HBox(cardSeriesVBox);
        cardSeriesContentHBox.setStyle("-fx-border-color: black; -fx-padding: 18, 5, 18, 5");
        cardSeriesContentHBox.autosize();

        rightVBox = new VBox(nextNumberVBox, checkCardVBox, bingoPatternContentHBox, cardSeriesContentHBox);
        rightVBox.autosize();
        rightVBox.setStyle("-fx-padding: 5; -fx-spacing: 5");
        rightVBox.setSpacing(5);

        contentPane = new HBox(leftVBox, rightVBox);
        contentPane.autosize();
        
        createMenus();
        
        rootPane = new BorderPane();
        rootPane.autosize();
        rootPane.setCenter(contentPane);
        rootPane.setTop(menuBar);
        
        scene = new Scene(rootPane, 654, 808);

        fontSize.bind(scene.widthProperty().add(scene.heightProperty()).divide(50));
        calledNumbersGridPane.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        checkCardGridPane.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));

        secondPreviousNumberCalled = 0;
        firstPreviousNumberCalled = 0;
        lastNumberCalled = 0;

        shuffledInts = new ShuffledIntegers(1, 75);
    }
    
    private void newGame() {
        
        bChkBox.setDisable(false);
        iChkBox.setDisable(false);
        nChkBox.setDisable(false);
        gChkBox.setDisable(false);
        oChkBox.setDisable(false);

        bChkBox.setSelected(true);
        iChkBox.setSelected(true);
        nChkBox.setSelected(true);
        gChkBox.setSelected(true);
        oChkBox.setSelected(true);

        lastNumberCalled = 0;
        firstPreviousNumberCalled = 0;
        secondPreviousNumberCalled = 0;
        nextNumberLabel.setText("");
        
        for (int i=1; i<76; i++)
        {
            bingoNumberData.get(bingoNumberRowCol[i].m_col).get(bingoNumberRowCol[i].m_row).setCallState(NumberCallState.NotCalled);
            bingoNumberLabels[bingoNumberRowCol[i].m_col][bingoNumberRowCol[i].m_row+1].setStyle("-fx-text-fill: lightgrey");
//            if (bingoNumberVector[i]->m_inPlay)
//            {
//                bingoNumberVector[i]->m_inPlay = false;
//                bingoNumbersTableWidget->item(bingoNumberVector[i]->m_row,
//                                              bingoNumberVector[i]->m_col)->setForeground(QBrush(nFontColor, Qt::SolidPattern));
//            }
        }
        shuffledInts.reShuffle();

//        if (numberGeneration == "Automatic")
//        {
            nextNumberButton.setDisable(false);
//            manualNextNumberButton->setDisabled(true);
//            manualNextNumberSpinBox->setDisabled(true);
//        }
//        else
//        {
//            nextNumberButton->setDisabled(true);
//            manualNextNumberButton->setEnabled(true);
//            manualNextNumberSpinBox->setEnabled(true);
//        }
//        updateCheckCardLineEdit();
    }
    
    private void checkCard() {
 
        String str = integerTextField.getText();
        int cardNumber = Integer.valueOf(str);
        if (cardNumber < firstCardNumber) {
            cardNumber = firstCardNumber;
            integerTextField.setText(Integer.toString(cardNumber));
        }
        
        if (cardNumber > lastCardNumber) {
            cardNumber = lastCardNumber;
             integerTextField.setText(Integer.toString(cardNumber));
       }
        
        CardType card = bingoCardSeries.getCard().get(cardNumber-1000);

        List<Integer> bNums = card.getBNumbers();
        List<Integer> iNums = card.getINumbers();
        List<Integer> nNums = card.getNNumbers();
        List<Integer> gNums = card.getGNumbers();
        List<Integer> oNums = card.getONumbers();

        BitSet checkCardBitSet = new BitSet(25);

        for (int row=1; row<6; row++)
        {
            // Handle B
            checkCardNumberLabels[0][row].setText(bNums.get(row-1).toString());
            if (bingoNumberData.get(bingoNumberRowCol[bNums.get(row-1)].m_col).get(bingoNumberRowCol[bNums.get(row-1)].m_row).getCallState() == NumberCallState.Called)
            {
                checkCardNumberLabels[0][row].setStyle("-fx-text-fill: red");
                checkCardBitSet.set(row-1);
            }
            else
            {
                checkCardNumberLabels[0][row].setStyle("-fx-text-fill: lightgrey");
            }

            // Handle I
            checkCardNumberLabels[1][row].setText(iNums.get(row-1).toString());
            if (bingoNumberData.get(bingoNumberRowCol[iNums.get(row-1)].m_col).get(bingoNumberRowCol[iNums.get(row-1)].m_row).getCallState() == NumberCallState.Called)
            {
                checkCardNumberLabels[1][row].setStyle("-fx-text-fill: red");
                checkCardBitSet.set(row-1+5);
            }
            else
            {
                checkCardNumberLabels[1][row].setStyle("-fx-text-fill: lightgrey");
            }

            // Handle N
            if ((row)!=3)
            {
                checkCardNumberLabels[2][row].setText(nNums.get(row-1).toString());
                if (bingoNumberData.get(bingoNumberRowCol[nNums.get(row-1)].m_col).get(bingoNumberRowCol[nNums.get(row-1)].m_row).getCallState() == NumberCallState.Called)
                {
                    checkCardNumberLabels[2][row].setStyle("-fx-text-fill: red");
                    checkCardBitSet.set(row-1+10);
                }
                else
                {
                    checkCardNumberLabels[2][row].setStyle("-fx-text-fill: lightgrey");
                }
            }
            else
            {
                checkCardNumberLabels[2][row].setStyle("-fx-text-fill: red");
                checkCardNumberLabels[2][row].setText("F");
                checkCardBitSet.set(row-1+10);
            }

            // Handle G
            checkCardNumberLabels[3][row].setText(gNums.get(row-1).toString());
            if (bingoNumberData.get(bingoNumberRowCol[gNums.get(row-1)].m_col).get(bingoNumberRowCol[gNums.get(row-1)].m_row).getCallState() == NumberCallState.Called)
            {
                checkCardNumberLabels[3][row].setStyle("-fx-text-fill: red");
                checkCardBitSet.set(row-1+15);
            }
            else
            {
                checkCardNumberLabels[3][row].setStyle("-fx-text-fill: lightgrey");
            }

            // Handle O
            checkCardNumberLabels[4][row].setText(oNums.get(row-1).toString());
            if (bingoNumberData.get(bingoNumberRowCol[oNums.get(row-1)].m_col).get(bingoNumberRowCol[oNums.get(row-1)].m_row).getCallState() == NumberCallState.Called)
            {
                checkCardNumberLabels[4][row].setStyle("-fx-text-fill: red");
                checkCardBitSet.set(row-1+20);
            }
            else
            {
                checkCardNumberLabels[4][row].setStyle("-fx-text-fill: lightgrey");
            }
        }
        if (winningCard.winner(checkCardBitSet, bingoGamePattern))
        {
                checkCardNumberLabels[2][3].setStyle("-fx-text-fill: red");
                checkCardNumberLabels[2][3].setText("W");
        }
    }
    
    private void updateIntegerTextField() {
        integerTextField.setText(Integer.toString(midCardNumber));
        checkCard();
    }

    private void updateCardSeriesInfo() {
        String seriesId = bingoCardSeries.getSeriesId();
        int numberOfCards = bingoCardSeries.getCard().size();
        String creationDate = bingoCardSeries.getCreationDate();
        String creatorName = bingoCardSeries.getSeriesCreator();

        firstCardNumber = 1000;
        midCardNumber = firstCardNumber + (numberOfCards / 2);
        lastCardNumber = firstCardNumber + numberOfCards - 1;
        
        cardSeriesDataLabel.setText(seriesId);
        numberOfCardsDataLabel.setText(Integer.toUnsignedString(numberOfCards));
        cardSeriesCreationDateDataLabel.setText(creationDate);
        cardSeriesCreatorDataLabel.setText(creatorName);
    }

    private void updateBingoPatternImages(String pattern) {
        switch (pattern) {
            case "AnyDiagonalRowOrColumn":
                bingoPatternImage1 = new Image("images/56/Row.png");
                bingoPatternImage2 = new Image("images/56/Column.png");
                bingoPatternImage3 = new Image("images/56/UpDiagonal.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Airplane":
                bingoPatternImage1 = new Image("images/56/Airplane1.png");
                bingoPatternImage2 = new Image("images/56/Airplane2.png");
                bingoPatternImage3 = new Image("images/56/Airplane3.png");
                bingoPatternImage4 = new Image("images/56/Airplane4.png");
                break;
            case "Arrowhead":
                bingoPatternImage1 = new Image("images/56/Arrowhead1.png");
                bingoPatternImage2 = new Image("images/56/Arrowhead2.png");
                bingoPatternImage3 = new Image("images/56/Arrowhead3.png");
                bingoPatternImage4 = new Image("images/56/Arrowhead4.png");
                break;
            case "BAndOBingo":
                bingoPatternImage1 = new Image("images/56/BAndOBingo.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Barbell":
                bingoPatternImage1 = new Image("images/56/Barbell.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "BlockOf6":
                bingoPatternImage1 = new Image("images/56/BlockOf6-1.png");
                bingoPatternImage2 = new Image("images/56/BlockOf6-2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "BlockOf8":
                bingoPatternImage1 = new Image("images/56/BlockOf8-1.png");
                bingoPatternImage2 = new Image("images/56/BlockOf8-2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "BlockOf9":
                bingoPatternImage1 = new Image("images/56/BlockOf9.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "BowTie1":
                bingoPatternImage1 = new Image("images/56/BowTie1.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "BowTie2":
                bingoPatternImage1 = new Image("images/56/BowTie2-1.png");
                bingoPatternImage2 = new Image("images/56/BowTie2-2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Cents":
                bingoPatternImage1 = new Image("images/56/Cents.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "ChampagneGlassEmpty":
                bingoPatternImage1 = new Image("images/56/ChampagneGlassEmpty.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "ChampagneGlassFull":
                bingoPatternImage1 = new Image("images/56/ChampagneGlassFull.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Checkmark":
                bingoPatternImage1 = new Image("images/56/Checkmark1.png");
                bingoPatternImage2 = new Image("images/56/Checkmark2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Column":
                bingoPatternImage1 = new Image("images/56/Column.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Coverall":
                bingoPatternImage1 = new Image("images/56/Coverall.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Diagonal":
                bingoPatternImage1 = new Image("images/56/UpDiagonal.png");
                bingoPatternImage2 = new Image("images/56/DownDiagonal.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Diamond":
                bingoPatternImage1 = new Image("images/56/Diamond.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DiamondInside":
                bingoPatternImage1 = new Image("images/56/DiamondInside.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DiamondOutline":
                bingoPatternImage1 = new Image("images/56/DiamondOutline.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DollarSign":
                bingoPatternImage1 = new Image("images/56/DollarSign.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DoubleBingo":
                bingoPatternImage1 = new Image("images/56/DoubleBingo-1.png");
                bingoPatternImage2 = new Image("images/56/DoubleBingo-2.png");
                bingoPatternImage3 = new Image("images/56/DoubleBingo-3.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DoubleOutside":
                bingoPatternImage1 = new Image("images/56/DoubleOutside.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "DownDiagonal":
                bingoPatternImage1 = new Image("images/56/DownDiagonal.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "FieldGoal":
                bingoPatternImage1 = new Image("images/56/FieldGoal.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "FourCornersBig":
                bingoPatternImage1 = new Image("images/56/FourCornersBig.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "FourCornersSmall":
                bingoPatternImage1 = new Image("images/56/FourCornersSmall.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "FrameInside":
                bingoPatternImage1 = new Image("images/56/FrameInside.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "FrameOutside":
                bingoPatternImage1 = new Image("images/56/FrameOutside.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "GoBingo":
                bingoPatternImage1 = new Image("images/56/GoBingo.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Kite":
                bingoPatternImage1 = new Image("images/56/Kite1.png");
                bingoPatternImage2 = new Image("images/56/Kite2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Ladder":
                bingoPatternImage1 = new Image("images/56/Ladder.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LayerCake":
                bingoPatternImage1 = new Image("images/56/LayerCake.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterC":
                bingoPatternImage1 = new Image("images/56/LetterC.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterH":
                bingoPatternImage1 = new Image("images/56/LetterH.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterI":
                bingoPatternImage1 = new Image("images/56/LetterI.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterL":
                bingoPatternImage1 = new Image("images/56/LetterL.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterM":
                bingoPatternImage1 = new Image("images/56/LetterM.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterN":
                bingoPatternImage1 = new Image("images/56/LetterN.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterP":
                bingoPatternImage1 = new Image("images/56/LetterP.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterS":
                bingoPatternImage1 = new Image("images/56/LetterS.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterT":
                bingoPatternImage1 = new Image("images/56/LetterT.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterW":
                bingoPatternImage1 = new Image("images/56/LetterW.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterX":
                bingoPatternImage1 = new Image("images/56/LetterX.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterY":
                bingoPatternImage1 = new Image("images/56/LetterY.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LetterZ":
                bingoPatternImage1 = new Image("images/56/LetterZ.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "LoveLetter":
                bingoPatternImage1 = new Image("images/56/LoveLetter.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Lucky7":
                bingoPatternImage1 = new Image("images/56/Lucky7.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "PercentSign":
                bingoPatternImage1 = new Image("images/56/PercentSign.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "PicnicTable":
                bingoPatternImage1 = new Image("images/56/PicnicTable.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "PlusSign":
                bingoPatternImage1 = new Image("images/56/PlusSign.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Pyramid":
                bingoPatternImage1 = new Image("images/56/Pyramid.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "RailroadTracks":
                bingoPatternImage1 = new Image("images/56/RailroadTracks.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Row":
                bingoPatternImage1 = new Image("images/56/Row.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Sputnik":
                bingoPatternImage1 = new Image("images/56/Sputnik.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Stamp1":
                bingoPatternImage1 = new Image("images/56/Stamp1.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Stamp2":
                bingoPatternImage1 = new Image("images/56/Stamp2.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Stamp4":
                bingoPatternImage1 = new Image("images/56/Stamp4.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "StampAnd3Corners":
                bingoPatternImage1 = new Image("images/56/StampAnd3Corners.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "StampAndLine":
                bingoPatternImage1 = new Image("images/56/StampAndLine1.png");
                bingoPatternImage2 = new Image("images/56/StampAndLine2.png");
                bingoPatternImage3 = new Image("images/56/StampAndLine3.png");
                bingoPatternImage4 = new Image("images/56/StampAndLine4.png");
                break;
            case "StraightAndCorners":
                bingoPatternImage1 = new Image("images/56/StraightAndCorners1.png");
                bingoPatternImage2 = new Image("images/56/StraightAndCorners2.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "TelephonePole":
                bingoPatternImage1 = new Image("images/56/TelephonePole.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "TopAndBottom":
                bingoPatternImage1 = new Image("images/56/TopAndBottom.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "TopHat":
                bingoPatternImage1 = new Image("images/56/TopHat.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Tree":
                bingoPatternImage1 = new Image("images/56/Tree.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "TripleBingo":
                bingoPatternImage1 = new Image("images/56/TripleBingo.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            case "Turtle":
                bingoPatternImage1 = new Image("images/56/Turtle1.png");
                bingoPatternImage2 = new Image("images/56/Turtle2.png");
                bingoPatternImage3 = new Image("images/56/Turtle3.png");
                bingoPatternImage4 = new Image("images/56/Turtle4.png");
                break;
            case "UpDiagonal":
                bingoPatternImage1 = new Image("images/56/UpDiagonal.png");
                bingoPatternImage2 = new Image("images/56/ABlankCard.png");
                bingoPatternImage3 = new Image("images/56/ABlankCard.png");
                bingoPatternImage4 = new Image("images/56/ABlankCard.png");
                break;
            default:
        }
        bingoPatternImageView1.setImage(bingoPatternImage1);
        bingoPatternImageView2.setImage(bingoPatternImage2);
        bingoPatternImageView3.setImage(bingoPatternImage3);
        bingoPatternImageView4.setImage(bingoPatternImage4);
    }
    private void nextNumber() {
        
        if (!bChkBox.isDisabled()) {
            bChkBox.setDisable(true);
            iChkBox.setDisable(true);
            nChkBox.setDisable(true);
            gChkBox.setDisable(true);
            oChkBox.setDisable(true);
        }

        if (secondPreviousNumberCalled != 0)
        {
            bingoNumberLabels[bingoNumberRowCol[secondPreviousNumberCalled].m_col][bingoNumberRowCol[secondPreviousNumberCalled].m_row+1].setStyle("-fx-text-fill: green");
        }
        secondPreviousNumberCalled = firstPreviousNumberCalled;
        firstPreviousNumberCalled = lastNumberCalled;
//        if (manual)
//        {
//           lastNumberCalled = manualNextNumberSpinBox->value();
//        }
//        else
//        {
        lastNumberCalled = shuffledInts.nextInteger(bChkBox.isSelected(), iChkBox.isSelected(), nChkBox.isSelected(), gChkBox.isSelected(), oChkBox.isSelected());
        nextNumberLabel.setText(hdrStrings[(lastNumberCalled-1)/15] + "-" + Integer.toString(lastNumberCalled));
        
        if (shuffledInts.done())
        {
            nextNumberButton.setDisable(true);
        }
 //       }
        bingoNumberData.get(bingoNumberRowCol[lastNumberCalled].m_col).get(bingoNumberRowCol[lastNumberCalled].m_row).setCallState(NumberCallState.Called);
        bingoNumberLabels[bingoNumberRowCol[lastNumberCalled].m_col][bingoNumberRowCol[lastNumberCalled].m_row+1].setStyle("-fx-text-fill: red");

        if (secondPreviousNumberCalled != 0)
        {
            bingoNumberLabels[bingoNumberRowCol[secondPreviousNumberCalled].m_col][bingoNumberRowCol[secondPreviousNumberCalled].m_row+1].setStyle("-fx-text-fill: red");
        }

        if (firstPreviousNumberCalled != 0)
        {
            bingoNumberLabels[bingoNumberRowCol[firstPreviousNumberCalled].m_col][bingoNumberRowCol[firstPreviousNumberCalled].m_row+1].setStyle("-fx-text-fill: red");
        }
    }
    
    private CardType bingoCard(int cardNumber)
    {
        CardType card = new CardType();

        // Set card number
        card.setCardNumber(cardNumber);

        // Initialize bnums list and index for later random selection.
        List<Integer> bnums = new ArrayList<>();
        for (int i=0; i<15; i++)
        {
            bnums.add(i+1);
        }
        int bnumLastIndex = 14;

        // Initialize inums list and index for later random selection.
        List<Integer> inums = new ArrayList<>();
        for (int i=0; i<15; i++)
        {
            inums.add(i+16);
        }
        int inumLastIndex = 14;

        // Initialize nnums list and index for later random selection.
        List<Integer> nnums = new ArrayList<>();
        for (int i=0; i<15; i++)
        {
            nnums.add(i+31);
        }
        int nnumLastIndex = 14;

        // Initialize gnums list and index for later random selection.
        List<Integer> gnums = new ArrayList<>();
        for (int i=0; i<15; i++)
        {
            gnums.add(i+46);
        }
        int gnumLastIndex = 14;

        // Initialize onums list and index for later random selection.
        List<Integer> onums = new ArrayList<>();
        for (int i=0; i<15; i++)
        {
            onums.add(i+61);
        }
        int onumLastIndex = 14;

        int b, i, n, g, o, temp;

        // Randomly select 5 numbers from bnums vector and save in m_bNums vector.
        for ( int m=0; m<5; m++)
        {
            b = (int)(Math.floor(Math.random() * (bnumLastIndex))) + 1;
            temp = bnums.get(bnumLastIndex);
            card.getBNumbers().add(bnums.get(b));
            bnums.set(bnumLastIndex, bnums.get(b));
            bnums.set(b, temp);
            bnumLastIndex--;
        }

        // Randomly select 5 numbers from inums vector and save in m_iNums vector.
        for ( int m=0; m<5; m++)
        {
            i = (int)(Math.floor(Math.random() * (inumLastIndex))) + 1;
            temp = inums.get(inumLastIndex);
            card.getINumbers().add(inums.get(i));
            inums.set(inumLastIndex, inums.get(i));
            inums.set(i, temp);
            inumLastIndex--;
        }

        // Randomly select 5 numbers from nnums vector and save in m_nNums vector.
        for ( int m=0; m<5; m++)
        {
            n = (int)(Math.floor(Math.random() * (nnumLastIndex))) + 1;
            temp = nnums.get(nnumLastIndex);
            card.getNNumbers().add(nnums.get(n));
            nnums.set(nnumLastIndex, nnums.get(n));
            nnums.set(n, temp);
            nnumLastIndex--;
        }
        // Freespace is marked with 0.
        card.getNNumbers().set(2, 0);

        // Randomly select 5 numbers from gnums vector and save in m_gNums vector.
        for ( int m=0; m<5; m++)
        {
            g = (int)(Math.floor(Math.random() * (gnumLastIndex))) + 1;
            temp = gnums.get(gnumLastIndex);
            card.getGNumbers().add(gnums.get(g));
            gnums.set(gnumLastIndex, gnums.get(g));
            gnums.set(g, temp);
            gnumLastIndex--;
        }

        // Randomly select 5 numbers from onums vector and save in m_oNums vector.
        for ( int m=0; m<5; m++)
        {
            o = (int)(Math.floor(Math.random() * (onumLastIndex))) + 1;
            temp = onums.get(onumLastIndex);
            card.getONumbers().add(onums.get(o));
            onums.set(onumLastIndex, onums.get(o));
            onums.set(o, temp);
            onumLastIndex--;
        }
        return card;
    }
    
    private void cardSeries(String seriesId,
                            String creatorName,
                            int numberOfCards) {
        
        // delete existing card series
        while(!bingoCardSeries.getCard().isEmpty())
        {
            bingoCardSeries.getCard().clear();
        }

        bingoCardSeries.setSeriesId(seriesId);
        bingoCardSeries.setSeriesCreator(creatorName);

        Calendar cal1 = new GregorianCalendar();
        Date creationDate = cal1.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");        
        bingoCardSeries.setCreationDate(dateFormat.format(creationDate));

        for (int i=0; i<numberOfCards; i++)
        {
            bingoCardSeries.getCard().add(bingoCard(i+1000));
        }
    }
    
    private void printCardsToFile() {
        
        CardType card;

        // Get seriesId
        String seriesId = bingoCardSeries.getSeriesId();

        // Get how many bingo cards in current series
        int numberOfCards = bingoCardSeries.getCard().size();

        String html = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n";

        html += "  'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>\n";
        html += "<html xmlns='http://www.w3.org/1999/xhtml'>\n";
        html += "  <head>\n";
        html += "    <meta http-equiv='Content-Type'\n";
        html += "      content='text/html; charset=iso-8859-1' />\n";
        html += "    <style type='text/css'>\n";
        html += "      body {\n";
        html += "        background-color: white;\n";
        html += "        font-family: sans-serif;\n";
        html += "      }\n";
        html += "      .table1 {\n";
        html += "        margin-left: auto;\n";
        html += "        margin-right: auto;\n";
        html += "        padding: 0px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .table2 {\n";
        html += "        margin: 0px;\n";
        html += "        padding: 0px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .table3 {\n";
        html += "        width: 100%;\n";
        html += "        margin: 0px;\n";
        html += "        padding: 0px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .table4 {\n";
        html += "        margin: 0px;\n";
        html += "        padding: 0px;\n";
        html += "        border-style: solid;\n";
        html += "        border-width: 1px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .td00 {\n";
        html += "        padding: 5px 15px 20px 5px;\n";
        html += "      }\n";
        html += "      .td01 {\n";
        html += "        padding: 5px 5px 20px 15px;\n";
        html += "      }\n";
        html += "      .td10 {\n";
        html += "        padding: 20px 15px 0px 5px;\n";
        html += "      }\n";
        html += "      .td11 {\n";
        html += "        padding: 20px 5px 0px 15px;\n";
        html += "      }\n";
        html += "      .td2 {\n";
        html += "        font-size: 16px;\n";
        html += "        text-align: left;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .td3 {\n";
        html += "        font-size: 16px;\n";
        html += "        text-align: right;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .tdbingo {\n";
        html += "        width: 60px;\n";
        html += "        font-size: 77px;\n";
        html += "        text-align: center;\n";
        html += "        border-style: solid;\n";
        html += "        border-width: 1px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .tdnumber {\n";
        html += "        font-size: 40px;\n";
        html += "        text-align: center;\n";
        html += "        border-style: solid;\n";
        html += "        border-width: 1px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .tdF {\n";
        html += "        font-size: 40px;\n";
        html += "        color: red;\n";
        html += "        text-align: center;\n";
        html += "        border-style: solid;\n";
        html += "        border-width: 1px;\n";
        html += "        border-collapse: collapse;\n";
        html += "      }\n";
        html += "      .tr1 {\n";
        html += "        height: 63px;\n";
        html += "      }\n";
        html += "    </style>\n";
        html += "  </head>\n";
        html += "  <body>\n";

        for (int page=0; page<(int)(numberOfCards/4); page++) {
            html += "    <table class='table1'>\n";

            for (int row=0; row<2; row++) {
                html += "      <tr>\n";

                for (int col=0; col<2; col++) {

                    int cardNumber = (page*4) + (row*2) + col + 1000;
                    card = bingoCardSeries.getCard().get(cardNumber-1000);

                    List<Integer> bNums = card.getBNumbers();
                    List<Integer> iNums = card.getINumbers();
                    List<Integer> nNums = card.getNNumbers();
                    List<Integer> gNums = card.getGNumbers();
                    List<Integer> oNums = card.getONumbers();

                    html += "        <td class='td" + Integer.toString(row) + Integer.toString(col) + "'>\n";
                    html += "          <table class='table2'>\n";
                    html += "            <tr>\n";
                    html += "              <td>\n";
                    html += "                <table class='table3'>\n";
                    html += "                  <tr>\n";
                    html += "                    <td class='td2'>" + seriesId + "\n";
                    html += "                    </td>\n";
                    html += "                    <td class='td3'>" + Integer.toString(cardNumber) + "\n";
                    html += "                    </td>\n";
                    html += "                  </tr>\n";
                    html += "                </table>\n";
                    html += "              </td>\n";
                    html += "            </tr>\n";
                    html += "            <tr>\n";
                    html += "              <td>\n";
                    html += "                <table class='table4'>\n";
                    html += "                  <tr>\n";
                    html += "                    <td class='tdbingo'>B\n";
                    html += "                    </td>\n";
                    html += "                    <td class='tdbingo'>I\n";
                    html += "                    </td>\n";
                    html += "                    <td class='tdbingo'>N\n";
                    html += "                    </td>\n";
                    html += "                    <td class='tdbingo'>G\n";
                    html += "                    </td>\n";
                    html += "                    <td class='tdbingo'>O\n";
                    html += "                    </td>\n";
                    html += "                  </tr>\n";
                    for (int numrow=0; numrow<5; numrow++) {
                        html += "                  <tr class='tr1'>\n";
                        html += "                    <td class='tdnumber'>" + Integer.toString(bNums.get(numrow)) + "\n";
                        html += "                    </td>\n";
                        html += "                    <td class='tdnumber'>" + Integer.toString(iNums.get(numrow)) + "\n";
                        html += "                    </td>\n";
                        if (numrow == 2) {
                            html += "                    <td class='tdF'>F\n";
                        }
                        else {
                            html += "                    <td class='tdnumber'>" + Integer.toString(nNums.get(numrow)) + "\n";
                        }
                        html += "                    </td>\n";
                        html += "                    <td class='tdnumber'>" + Integer.toString(gNums.get(numrow)) + "\n";
                        html += "                    </td>\n";
                        html += "                    <td class='tdnumber'>" + Integer.toString(oNums.get(numrow)) + "\n";
                        html += "                    </td>\n";
                        html += "                  </tr>\n";
                    }
                    html += "                </table>\n";
                    html += "              </td>\n";
                    html += "            </tr>\n";
                    html += "          </table>\n";
                    html += "        </td>\n";
                    
                } // for (int col=0; col<2; col++)

                html += "      </tr>\n";
                
            } // for (int row=0; row<2; row++)
            
            html += "    </table>\n";
//            html += "<br style='page-break-after: always' />";
    
        } // for (int page=0; page<(int)(numberOfCards/4); page++)

        html += "  </body>\n";
        html += "</html>\n";

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(rootStage);
        if (selectedDirectory != null) {
            String absPath = selectedDirectory.getAbsolutePath();
            try (FileWriter fw = new FileWriter(absPath + File.separator + bingoCardSeries.getSeriesId() + ".html")) {
                fw.write(html);
            }
            catch (IOException exc) {
                System.out.println(exc);
            }
        }
    }  // end printCardsToFile()
    
    @Override
    public void start(Stage primaryStage) {
        
        initBingo(primaryStage);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
