package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void display(String message){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Alert");
        stage.setMinWidth(300);
        stage.setMinHeight(150);
        stage.getIcons().add(
                new Image(
                        MainWindowController.class.getResourceAsStream( "Island.png" )));
        stage.setResizable(true);

        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(250);
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> stage.close());
        closeButton.setDefaultButton(true);

        VBox layout = new VBox();
        layout.getChildren().addAll(label , closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
