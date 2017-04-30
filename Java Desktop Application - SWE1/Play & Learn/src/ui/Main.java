package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application {

    // used make communication between different windows
    public static int activeUserIndex = -1;
    public static String activeGameField = null;
    public static String activeGameName = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
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
    }



//    @Override
//    public void stop(){
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        //alert.setTitle("Confirmation Dialog");
//        alert.setHeaderText("You are missing a lot of fun!");
//        alert.setContentText("Are you sure you want to exit?");
//        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(
//                new Image(
//                        MainWindowController.class.getResourceAsStream( "Island.png" )));
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            // ... user chose OK
//            Platform.exit();
//        } else {
//            // ... user chose CANCEL or closed the dialog
//        }
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
