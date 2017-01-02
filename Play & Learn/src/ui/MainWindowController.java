package ui;

import database.DBStudent;
import database.DBTeacher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class MainWindowController {

    @FXML
    private TextField username = new TextField();

    @FXML
    private PasswordField password = new PasswordField();

    @FXML
    private ToggleGroup loginType = new ToggleGroup();

    @FXML
    private RadioButton asStudentBtn = new RadioButton("Login as Student");

    @FXML
    private RadioButton asTeacherBtn = new RadioButton("Login as Teacher");


    @FXML
    public void initialize(){
        DBStudent.loadData();
        DBTeacher.loadData();

        asStudentBtn.setUserData("student");
        asTeacherBtn.setUserData("teacher");
    }


    public boolean loginAsStudent(String username,String password){
        return DBStudent.verify(username, password);
    }

    public boolean loginAsTeacher(String username, String password){
        return DBTeacher.verify(username, password);
    }

    @FXML
    public void onLoginButtonClicked(ActionEvent event){
        if(!((username.getText().equals("")) || (password.getText().equals("")))){
            String usernameStr = username.getText();
            String passwordStr = password.getText();
            String loginTypeStr = loginType.getSelectedToggle().getUserData().toString();

            if (usernameStr.contains(" ") || passwordStr.contains(" ")){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("Error Dialog");
                alert.setHeaderText("Invalid Values Entered");
                alert.setContentText("username and password must not contain spaces");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(
                        new Image(
                                MainWindowController.class.getResourceAsStream( "Island.png" )));

                alert.showAndWait();

            }else{
                /** Test **/
                //System.out.println(loginTypeStr);

                if (loginTypeStr.equals("student")){  // login as student is chosen
                    /** Test **/
                    //System.out.println(usernameStr);
                    //System.out.println(passwordStr);

                    // validate the username and password for student
                    if (loginAsStudent(usernameStr, passwordStr)){  // OK
                        //log him in
                        Stage currentStage = (Stage) username.getScene().getWindow();

                        try{
                            Parent root = FXMLLoader.load(getClass().getResource("studentHome.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Your Home Profile - Student");
                            stage.setScene(new Scene(root, 850, 650));
                            stage.getIcons().add(
                                    new Image(
                                            MainWindowController.class.getResourceAsStream( "Island.png" )));
                            stage.setResizable(true);
                            //stage.setMaximized(true);
                            stage.setMinWidth(850);
                            stage.setMinHeight(650);


                            currentStage.close();
                            stage.show();
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                    else{  // not OK
                        //System.out.println(DBStudent.getStudents());  // Test

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        //alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Username or Password is incorrect.");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(
                                new Image(
                                        MainWindowController.class.getResourceAsStream( "Island.png" )));

                        alert.showAndWait();
                    }
                }
                else if (loginTypeStr.equals("teacher")){ // login as teacher is chosen
                    /** Test **/
                    //System.out.println(usernameStr);
                    //System.out.println(passwordStr);

                    // validate the username and password for teacher
                    if (loginAsTeacher(usernameStr, passwordStr)){  // OK
                        // log him in
                        Stage currentStage = (Stage) username.getScene().getWindow();

                        try{
                            Parent root = FXMLLoader.load(getClass().getResource("teacherHome.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Your Home Profile - Teacher");
                            stage.setScene(new Scene(root, 850, 650));
                            stage.getIcons().add(
                                    new Image(
                                            MainWindowController.class.getResourceAsStream( "Island.png" )));
                            stage.setResizable(true);
                            //stage.setMaximized(true);
                            stage.setMinWidth(850);
                            stage.setMinHeight(650);

                            currentStage.close();
                            stage.show();
                        }catch(Exception e){
                            e.printStackTrace();
                        }


                    }else{ // not OK
                        //System.out.println(DBStudent.getStudents());  // Test

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        //alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Username or Password is incorrect");
                        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(
                                new Image(
                                        MainWindowController.class.getResourceAsStream( "Island.png" )));

                        alert.showAndWait();

                    }
                }
            }
        }
        else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter your Username & Password First");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image(
                            MainWindowController.class.getResourceAsStream( "Island.png" )));

            alert.showAndWait();
        }
    }

    @FXML
    public void onRegisterButtonClicked(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("registerWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register an Account");
            stage.setScene(new Scene(root, 600, 400));
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
    public void onAboutClicked(ActionEvent event){
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
    public void onClosedClicked(ActionEvent event){
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
    }

}
