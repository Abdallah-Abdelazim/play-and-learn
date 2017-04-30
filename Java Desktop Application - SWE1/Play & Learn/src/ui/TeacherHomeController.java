package ui;

import database.DBFields;
import database.DBTeacher;
import game.Game;
import game.Template;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TeacherHomeController {

    @FXML
    private Label nameLabel = new Label();
    @FXML
    private Label emailLabel = new Label();
    @FXML
    private Label ageLabel = new Label();
    @FXML
    private Label genderLabel = new Label();


    ObservableList<String> availableFields_Play = FXCollections.observableArrayList();
    ObservableList<String> availableGames_Play = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> fieldsCB_Play = new ComboBox<>(availableFields_Play);
    @FXML
    private ComboBox<String> gamesCB_Play = new ComboBox<>(availableGames_Play);

    @FXML
    private Label addedMessageLabel = new Label();

    @FXML
    private Label successAddMessageLabel = new Label();

    @FXML
    private TextField name = new TextField();

    @FXML
    private TextArea description = new TextArea();

    @FXML
    private TextField questionBody = new TextField();

    @FXML
    private TextField answer1 = new TextField();

    @FXML
    private TextField answer2 = new TextField();

    @FXML
    private TextField answer3 = new TextField();

    @FXML
    private TextField answer4 = new TextField();

    @FXML
    private TextField theRightAnswer = new TextField();

    ObservableList<String> availableTypes_Add = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> typesCB_Add = new ComboBox<>(availableTypes_Add);


    ObservableList<String> availableFields_Add = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> fieldsCB_Add = new ComboBox<>(availableFields_Add);

    @FXML
    private Label selectedGameDescription = new Label();


    private Template gameTemplate = new Template();
    @FXML
    public void initialize(){
        DBFields.loadData();

        nameLabel.setText(DBTeacher.getTeachers().get(Main.activeUserIndex).getAccount().getCompleteName());
        emailLabel.setText(DBTeacher.getTeachers().get(Main.activeUserIndex).getAccount().getEmail());
        ageLabel.setText(( String.valueOf(DBTeacher.getTeachers().get(Main.activeUserIndex).getAccount().getAge())));
        genderLabel.setText(DBTeacher.getTeachers().get(Main.activeUserIndex).getAccount().getGender());

        fieldsCB_Play.setVisibleRowCount(3);
        fieldsCB_Play.getItems().add("Programing");
        fieldsCB_Play.getItems().add("Maths");
        fieldsCB_Play.getItems().add("Science");
        fieldsCB_Play.getItems().add("Physics");
        fieldsCB_Play.getItems().add("Chemistry");
        fieldsCB_Play.getItems().add("Literature");
        fieldsCB_Play.getItems().add("Technology");

        fieldsCB_Add.setVisibleRowCount(3);
        fieldsCB_Add.getItems().add("Programing");
        fieldsCB_Add.getItems().add("Maths");
        fieldsCB_Add.getItems().add("Science");
        fieldsCB_Add.getItems().add("Physics");
        fieldsCB_Add.getItems().add("Chemistry");
        fieldsCB_Add.getItems().add("Literature");
        fieldsCB_Add.getItems().add("Technology");

        typesCB_Add.setVisibleRowCount(3);
        typesCB_Add.getItems().add("MCQ");
        typesCB_Add.getItems().add("True and False");
    }

    @FXML
    public void onPlayButtonClicked(){
        if (fieldsCB_Play.getSelectionModel().getSelectedItem() != null
                && gamesCB_Play.getSelectionModel().getSelectedItem() != null){

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
    private void afterChoosingField_play(){

        Main.activeGameField = fieldsCB_Play.getValue();  // specify field of game to be opened next

        // Empty the ComboBox From previous selection (if any)
        //gamesCB_Play.valueProperty().set(null);
        gamesCB_Play.setValue(null);
        gamesCB_Play.getItems().clear();

        // Empty description label (if any)
        selectedGameDescription.setText("");

        gamesCB_Play.setDisable(false);

        // fill gamesCB with available games for the field
        gamesCB_Play.setVisibleRowCount(3);
        ArrayList<Game> gamesInSelectedField = DBFields.getGamesInField(fieldsCB_Play.getValue());
        if (gamesInSelectedField != null){  // handles if field didn't contain any games
            for (int i = 0; i<gamesInSelectedField.size() ; i++){
                gamesCB_Play.getItems().add(gamesInSelectedField.get(i).getName());
            }
        }


    }

    @FXML
    private void afterChoosingGame(){
        if (gamesCB_Play.getValue() != null){
            Main.activeGameName = gamesCB_Play.getValue(); // specify game to be opened next
            //selectedGameDescription.setWrapText(true);

            //System.out.println(Main.activeGameField);  // Test
            //System.out.println(Main.activeGameName);   // Test
            selectedGameDescription.setText(
                    DBFields.getGame(Main.activeGameField , Main.activeGameName).getDescription()
                    + "\n\nCreated by : " + DBFields.getGame(Main.activeGameField , Main.activeGameName).getCreatorTeacherUsername());
        }
    }

    @FXML
    private void afterChoosingType_add(){
        if (typesCB_Add.getValue().equals("MCQ")){
            // enable all 4
            questionBody.setDisable(false);
            answer1.setDisable(false);
            answer1.clear();
            answer2.setDisable(false);
            answer2.setEditable(true);
            answer2.clear();
            answer3.setDisable(false);
            answer3.setEditable(true);
            answer3.clear();
            answer4.setDisable(false);
            answer4.clear();
            theRightAnswer.setDisable(false);

        }
        else if (typesCB_Add.getValue().equals("True and False")){
            // enable only 2
            questionBody.setDisable(false);

            answer1.clear();
            answer1.setDisable(true);

            answer2.setDisable(false);
            answer2.setText("True");
            answer2.setEditable(false);

            answer3.setDisable(false);
            answer3.setText("False");
            answer3.setEditable(false);

            answer4.clear();
            answer4.setDisable(true);

            theRightAnswer.setDisable(false);

        }

    }

    @FXML
    private void onAddAnotherQuestionButtonClicked(){
        if (name.getText().equals("") || description.getText().equals("") || fieldsCB_Add.getSelectionModel().getSelectedItem() == null
                || typesCB_Add.getSelectionModel().getSelectedItem() == null
                || questionBody.getText().equals("") || answer2.getText().equals("") || answer3.getText().equals("")
                || ( (!answer1.isDisable()) && answer1.getText().equals("") )
                || ( (!answer4.isDisable()) && answer4.getText().equals("") )
                || theRightAnswer.getText().equals("")){

            AlertBox.display("Please fill all the data");
        }else{
            // everything is OK
            StringBuilder answers = new StringBuilder();

            if (typesCB_Add.getValue().equals("MCQ")){
                answers.append(answer1.getText());
                answers.append(";");

                answers.append(answer2.getText());
                answers.append(";");

                answers.append(answer3.getText());
                answers.append(";");

                answers.append(answer4.getText());
                answers.append(";");
            }
            else if (typesCB_Add.getValue().equals("True and False")){
                answers.append(answer2.getText());
                answers.append(";");

                answers.append(answer3.getText());
                answers.append(";");
            }

            //System.out.println(answers); // Test
            gameTemplate.addQuestion(questionBody.getText() , answers.toString() , theRightAnswer.getText());

            addedMessageLabel.setText("Question Added");

            questionBody.clear();
            if (typesCB_Add.getValue().equals("MCQ")){
                answer1.clear();

                answer2.clear();

                answer3.clear();

                answer4.clear();
            }
            theRightAnswer.clear();
        }

    }

    @FXML
    public void onAddGameButtonClicked(){
        if (name.getText().equals("") || description.getText().equals("") || fieldsCB_Add.getSelectionModel().getSelectedItem() == null
                || typesCB_Add.getSelectionModel().getSelectedItem() == null
                || questionBody.getText().equals("") || answer2.getText().equals("") || answer3.getText().equals("")
                || ( (!answer1.isDisable()) && answer1.getText().equals("") )
                || ( (!answer4.isDisable()) && answer4.getText().equals("") )
                || theRightAnswer.getText().equals("")){

            AlertBox.display("Please fill all the data");
        }
        else{
            // everything is OK
            Game game = new Game(name.getText() , description.getText()
                    , DBTeacher.getTeachers().get(Main.activeUserIndex).getAccount().getUsername()
                    , typesCB_Add.getValue());

            StringBuilder answers = new StringBuilder();

            if (typesCB_Add.getValue().equals("MCQ")){
                answers.append(answer1.getText());
                answers.append(";");

                answers.append(answer2.getText());
                answers.append(";");

                answers.append(answer3.getText());
                answers.append(";");

                answers.append(answer4.getText());
                answers.append(";");
            }
            else if (typesCB_Add.getValue().equals("True and False")){
                answers.append(answer2.getText());
                answers.append(";");

                answers.append(answer3.getText());
                answers.append(";");
            }

            gameTemplate.addQuestion(questionBody.getText() , answers.toString() , theRightAnswer.getText());

            addedMessageLabel.setText("Question Added");

            game.setTemplate(gameTemplate);

            //System.out.println(game);  // Test

            // add and save
            DBFields.addGameToField(fieldsCB_Add.getSelectionModel().getSelectedItem() , game);
            successAddMessageLabel.setText("Game Added Successfully");
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
