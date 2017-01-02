package ui;


import database.DBFields;
import database.DBStudent;
import game.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentHomeController {

    @FXML
    private Label nameLabel = new Label();
    @FXML
    private Label emailLabel = new Label();
    @FXML
    private Label ageLabel = new Label();
    @FXML
    private Label genderLabel = new Label();

    ObservableList<String> availableFields = FXCollections.observableArrayList();
    ObservableList<String> availableGames = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> fieldsCB = new ComboBox<>(availableFields);
    @FXML
    private ComboBox<String> gamesCB = new ComboBox<>(availableGames);


    @FXML
    private Label selectedGameDescription = new Label();

    @FXML
    public void initialize(){
        DBFields.loadData();

        nameLabel.setText(DBStudent.getStudents().get(Main.activeUserIndex).getAccount().getCompleteName());
        emailLabel.setText(DBStudent.getStudents().get(Main.activeUserIndex).getAccount().getEmail());
        ageLabel.setText(( String.valueOf(DBStudent.getStudents().get(Main.activeUserIndex).getAccount().getAge())));
        genderLabel.setText(DBStudent.getStudents().get(Main.activeUserIndex).getAccount().getGender());

        fieldsCB.setVisibleRowCount(3);
        // fill fieldsCB with available fields
        fieldsCB.getItems().add("Programing");
        fieldsCB.getItems().add("Maths");
        fieldsCB.getItems().add("Science");
        fieldsCB.getItems().add("Physics");
        fieldsCB.getItems().add("Chemistry");
        fieldsCB.getItems().add("Literature");
        fieldsCB.getItems().add("Technology");
    }

    @FXML
    public void onPlayButtonClicked(){
        if (fieldsCB.getSelectionModel().getSelectedItem() != null
                && gamesCB.getSelectionModel().getSelectedItem() != null){

            try {
                Parent root = FXMLLoader.load(getClass().getResource("playWindow.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Play Game");
                stage.setScene(new Scene(root, 850, 650));
                stage.getIcons().add(
                        new Image(
                                MainWindowController.class.getResourceAsStream("Island.png")));
                stage.setResizable(true);
                //stage.setMaximized(true);
                stage.setMinWidth(850);
                stage.setMinHeight(650);

                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        else{
            AlertBox.display("please choose a Field & choose a game then click play");
        }
    }

    @FXML
    private void afterChoosingField(){

        Main.activeGameField = fieldsCB.getValue();  // specify field of game to be opened next

        // Empty the ComboBox From previous selection (if any)
        //gamesCB_Play.valueProperty().set(null);
        gamesCB.setValue(null);
        gamesCB.getItems().clear();

        // Empty description label (if any)
        selectedGameDescription.setText("");

        gamesCB.setDisable(false);

        // fill gamesCB with available games for the field
        gamesCB.setVisibleRowCount(3);
        ArrayList<Game> gamesInSelectedField = DBFields.getGamesInField(fieldsCB.getValue());
        for (int i = 0; i<gamesInSelectedField.size() ; i++){
            gamesCB.getItems().add(gamesInSelectedField.get(i).getName());
        }

    }

    @FXML
    private void afterChoosingGame(){
        if (gamesCB.getValue() != null){
            Main.activeGameName = gamesCB.getValue(); // specify game to be opened next
            //selectedGameDescription.setWrapText(true);

            //System.out.println(Main.activeGameField);  // Test
            //System.out.println(Main.activeGameName);   // Test
            selectedGameDescription.setText(DBFields.getGame(Main.activeGameField , Main.activeGameName).getDescription()
                    + "\n\nCreated by : " + DBFields.getGame(Main.activeGameField , Main.activeGameName).getCreatorTeacherUsername());
        }
    }

    @FXML
    public void onAboutClicked(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("aboutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root, 350, 250));
            stage.getIcons().add(
                    new Image(
                            MainWindowController.class.getResourceAsStream( "Island.png" )));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onClosedClicked(){
        Platform.exit();
    }

    @FXML
    public void onLogoutClicked(){
        Main.activeUserIndex = -1;
        Main.activeGameField = null;
        Main.activeGameName = null;
        Stage primaryStage = (Stage) nameLabel.getScene().getWindow();
        primaryStage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

            primaryStage.setTitle("Play and Learn - Login");
            primaryStage.setScene(new Scene(root, 650, 450));
            //primaryStage.getIcons().add(new Image("file:Island.png"));  // doesn't work
            primaryStage.getIcons().add(
                    new Image(
                            Main.class.getResourceAsStream( "Island.png" )));
            primaryStage.setMinWidth(650);
            primaryStage.setMinHeight(450);

            // display confirmation on exit
            primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                e.consume();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("You are missing a lot of fun!");
                alert.setContentText("Are you sure you want to exit?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(
                        new Image(
                                MainWindowController.class.getResourceAsStream("Island.png")));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    Platform.exit();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            });

            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
