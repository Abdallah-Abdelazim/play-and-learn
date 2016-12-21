package ui;

import database.DBStudent;
import database.DBTeacher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.Account;
import user.Student;
import user.Teacher;

public class RegisterWindowController {

    @FXML
    private TextField username = new TextField();

    @FXML
    private TextField password = new TextField();

    @FXML
    private TextField name = new TextField();

    @FXML
    private TextField email = new TextField();

    @FXML
    private TextField age = new TextField();

    @FXML
    private ToggleGroup gender = new ToggleGroup();

    @FXML
    private RadioButton maleBtn = new RadioButton("male");

    @FXML
    private RadioButton femaleBtn = new RadioButton("female");

    @FXML
    private ToggleGroup accountType = new ToggleGroup();

    @FXML
    private RadioButton studentBtn = new RadioButton("male");

    @FXML
    private RadioButton teacherBtn = new RadioButton("female");

    @FXML
    private Button registerBtn = new Button();


    @FXML
    public void initialize(){
        maleBtn.setUserData("male");
        femaleBtn.setUserData("female");

        studentBtn.setUserData("student");
        teacherBtn.setUserData("teacher");
    }

    @FXML
    public void onRegisterButtonClicked(){
        if (username.getText().equals("") || password.getText().equals("") || name.getText().equals("")
                || email.getText().equals("") || age.getText().equals("") || gender.getSelectedToggle() == null || accountType.getSelectedToggle() == null){

            AlertBox.display("Please fill all the fields first");
        }
        else{
            String usernameStr = username.getText();
            String passwordStr = password.getText();
            String nameStr = name.getText();
            String emailStr = email.getText();
            int ageInt = -1;
            try{
                ageInt = Integer.parseInt(age.getText()); // handle invalid age
            }catch (Exception e){
                //e.printStackTrace();
            }
            String genderStr = gender.getSelectedToggle().getUserData().toString();
            String accountTypeStr = accountType.getSelectedToggle().getUserData().toString();

            if (usernameStr.contains(" ") || passwordStr.contains(" ")|| ageInt ==-1){
                if (ageInt ==-1){
                    AlertBox.display("Enter a valid age");
                }else{
                    AlertBox.display("username and password must not contain spaces");
                }
            }
            else{
                /** Test **/
                //System.out.println(usernameStr);
                //System.out.println(passwordStr);
                //System.out.println(nameStr);
                //System.out.println(emailStr);
                //System.out.println(ageInt);
                //System.out.println(genderStr);
                //System.out.println(accountTypeStr);

                Account account = new Account(usernameStr, passwordStr, nameStr, emailStr, ageInt, genderStr);

                if (accountTypeStr.equals("student")){
                    Student student = new Student();
                    student.setAccount(account);

                    // save in student file
                    DBStudent.addStudent(student);
                }
                else if(accountTypeStr.equals("teacher")){
                    Teacher teacher = new Teacher();
                    teacher.setAccount(account);

                    // save in teacher file
                    DBTeacher.addTeacher(teacher);
                }

                // close window after saving
                Stage stage = (Stage) registerBtn.getScene().getWindow(); // any object in the stage is good

                AlertBox.display("Registered Successfully");

                stage.close();

                }
        }
    }
}
