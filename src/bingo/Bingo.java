/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
    
    GridPane gridPane;
    
    ColumnConstraints col1;
    
    Scene scene;
    
    VBox leftVBox;
    VBox rightVBox;
    HBox contentPane;
    BorderPane rootPane;
    
    Button nextNumberButton;
    Label nextNumberLabel;
    
    HBox nextNumberHBox;
    
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

    CardSeriesType bingoCardSeries;

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
            cardSeriesDataLabel.setText(bingoCardSeries.getSeriesId());
            numberOfCardsDataLabel.setText(new Integer(bingoCardSeries.getCard().size()).toString());
            cardSeriesCreatorDataLabel.setText(bingoCardSeries.getSeriesCreator());
            cardSeriesCreationDateDataLabel.setText(bingoCardSeries.getCreationDate());
        }
        catch (JAXBException exc) {
            System.out.println(exc);
        }
        System.out.println();
    }
    
    public void saveCards() {
        String absPath;
        try {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            final File selectedDirectory = directoryChooser.showDialog(rootStage);
            if (selectedDirectory != null) {
                absPath = selectedDirectory.getAbsolutePath();
                FileWriter fw = new FileWriter(absPath + File.separator + bingoCardSeries.getSeriesId() + ".xml");
                cardSeriesMarshaller.marshal(root, fw);
            }
        }
        catch (JAXBException exc) {
            System.out.println(exc);
        }
        catch (IOException exc) {
            System.out.println(exc);
        }
        System.out.println();
    }
    
    public void newCards() {
        
        GridPane grid;
        TextField creatorName;
        Label creatorNameLabel;
        ComboBox numberOfCards;
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
        numberOfCards = new ComboBox();
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
            cardSeriesDataLabel.setText(newCardsData.seriesName);
            numberOfCardsDataLabel.setText(new Integer(bingoCardSeries.getCard().size()).toString());
            cardSeriesCreationDateDataLabel.setText(bingoCardSeries.getCreationDate());
            cardSeriesCreatorDataLabel.setText(newCardsData.creatorName);
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
        cardsMenu.getItems().addAll(newCardsItem, saveCardsItem, loadCardsItem);
        
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
        hdrStrings[0] = "T";
        hdrStrings[1] = "I";
        hdrStrings[2] = "G";
        hdrStrings[3] = "E";
        hdrStrings[4] = "R";
 
        // Initialize Bingo Number Labels
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 16; row++) {
                if (0 == row) {
                    bingoNumberLabels[col][row] = new Label(hdrStrings[col]);
                    bingoNumberLabels[col][row].setFont(new Font(40));
                    bingoNumberLabels[col][row].setStyle("-fx-font-weight: bolder");
                }
                else
                {
                    bingoNumberLabels[col][row] =
                        new Label(new Integer(bingoNumberData.get(col).get(row-1).getBingoNumber()).toString());
                    bingoNumberLabels[col][row].setFont(new Font(40));
                    bingoNumberLabels[col][row].setStyle("-fx-text-fill: lightgrey");
                }
            }
        }

        bingoNumberRowCol = new BingoNumberRowCol[76];
        
        for (int i = 1; i < 76; i++) {
            bingoNumberRowCol[i] = new BingoNumberRowCol(Math.floorMod(i-1, 15), (i-1)/15);
        }

        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        col1 = new ColumnConstraints();
        col1.setHalignment(HPos.CENTER);
        col1.setMinWidth(57.0);
        gridPane.getColumnConstraints().addAll(col1, col1, col1, col1, col1);
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 16; row++) {
                gridPane.add(bingoNumberLabels[col][row], col, row);
            }
        }

        nextNumberButton = new Button("Next Number");
        nextNumberButton.setOnAction((event) -> {nextNumber();});

        nextNumberLabel = new Label("");
        nextNumberLabel.setPrefWidth(175);
        nextNumberLabel.setFont(new Font(64));
        nextNumberLabel.setStyle("-fx-text-fill: red; -fx-padding: 0 0 0 20");
        
        nextNumberHBox = new HBox(nextNumberButton, nextNumberLabel);
        nextNumberHBox.setAlignment(Pos.CENTER);
        nextNumberHBox.setStyle("-fx-padding: 5");
        nextNumberHBox.setSpacing(10.0);
        nextNumberHBox.setPrefWidth(350);
        
        bChkBox = new CheckBox(hdrStrings[0]);
        iChkBox = new CheckBox(hdrStrings[1]);
        nChkBox = new CheckBox(hdrStrings[2]);
        gChkBox = new CheckBox(hdrStrings[3]);
        oChkBox = new CheckBox(hdrStrings[4]);

        bChkBox.setSelected(true);
        iChkBox.setSelected(true);
        nChkBox.setSelected(true);
        gChkBox.setSelected(true);
        oChkBox.setSelected(true);

        bingoChkBoxHBox = new HBox(bChkBox, iChkBox, nChkBox, gChkBox, oChkBox);
        bingoChkBoxHBox.setAlignment(Pos.CENTER);
        bingoChkBoxHBox.setStyle("-fx-padding: 5");
        bingoChkBoxHBox.setSpacing(20.0);
        bingoChkBoxHBox.setPrefWidth(350);

        nextNumberVBox = new VBox(nextNumberHBox, bingoChkBoxHBox);
        nextNumberVBox.setStyle("-fx-border-color: black; -fx-padding: 5");
        nextNumberVBox.setSpacing(10.0);
        nextNumberVBox.setPrefWidth(350);

        leftVBox = new VBox(gridPane);
       
        

        cardSeriesLabel = new Label("Card Series: ");
        cardSeriesLabel.setStyle("-fx-font-weight: bold");
        cardSeriesDataLabel = new Label(bingoCardSeries.getSeriesId());

        numberOfCardsLabel = new Label("Number of Cards: ");
        numberOfCardsLabel.setStyle("-fx-font-weight: bold");
        numberOfCardsDataLabel = new Label(new Integer(bingoCardSeries.getCard().size()).toString());
 
        cardSeriesCreatorLabel = new Label("Card Series Creator: ");
        cardSeriesCreatorLabel.setStyle("-fx-font-weight: bold");
        cardSeriesCreatorDataLabel = new Label(bingoCardSeries.getSeriesCreator());

        cardSeriesCreationDateLabel = new Label("Creation Date: ");
        cardSeriesCreationDateLabel.setStyle("-fx-font-weight: bold");
        cardSeriesCreationDateDataLabel = new Label(bingoCardSeries.getCreationDate());
        
        cardSeriesHBox = new HBox(cardSeriesLabel, cardSeriesDataLabel);
        numberOfCardsHBox = new HBox(numberOfCardsLabel, numberOfCardsDataLabel);
        cardSeriesCreatorHBox = new HBox(cardSeriesCreatorLabel, cardSeriesCreatorDataLabel);
        creationDateHBox = new HBox(cardSeriesCreationDateLabel, cardSeriesCreationDateDataLabel);
        cardSeriesVBox = new VBox(cardSeriesHBox, numberOfCardsHBox, cardSeriesCreatorHBox, creationDateHBox);
        cardSeriesContentHBox = new HBox(cardSeriesVBox);
        cardSeriesContentHBox.setStyle("-fx-border-color: black; -fx-padding: 5");

        rightVBox = new VBox(nextNumberVBox, cardSeriesContentHBox);
        rightVBox.setStyle("-fx-padding: 5");
        rightVBox.setSpacing(5);

        contentPane = new HBox(leftVBox, rightVBox);
        
        createMenus();
        
        rootPane = new BorderPane();
        rootPane.setCenter(contentPane);
        rootPane.setTop(menuBar);
        
        scene = new Scene(rootPane, 600, 786);
        
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
        nextNumberLabel.setText(hdrStrings[(lastNumberCalled-1)/15] + "-" + new Integer(lastNumberCalled).toString());
        
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
