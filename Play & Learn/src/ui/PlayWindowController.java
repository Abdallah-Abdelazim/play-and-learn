package ui;


import database.DBFields;
import game.Game;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayWindowController {

    @FXML
    private Label questionBody = new Label();
    @FXML
    private Label ans1 = new Label();
    @FXML
    private Label ans2 = new Label();
    @FXML
    private Label ans3 = new Label();
    @FXML
    private Label ans4 = new Label();
    @FXML
    private TextField userAnswer = new TextField();
    @FXML
    private Button nextQuestionButton = new Button();


    private Game selectedGame = new Game();
    private int nextQuestionIndex;
    private float score = 0;

    @FXML
    public void initialize(){
        nextQuestionIndex = 0;
        // retrieve the game from file
        selectedGame = DBFields.getGame(Main.activeGameField , Main.activeGameName);

        // display First Question
        if (selectedGame.getGameType().equals("MCQ")){
            displayQuestionMCQ();
        }
        else if (selectedGame.getGameType().equals("True and False")){
            displayQuestionTF();
        }

    }


    @FXML
    public void onNextQuestionButtonClicked(){
        if (userAnswer.getText().equals("")){
            AlertBox.display("Please enter yor answer");
        }
        else{
            if (nextQuestionIndex > (selectedGame.getTemplate().getNumOfQuestions() - 1)){

                // calculate partial score (last question)
                if (userAnswer.getText().equalsIgnoreCase(
                        selectedGame.getTemplate().getQuestion(nextQuestionIndex-1).get(2))
                        ){
                    score++;
                }
                // calculate total score (always out of 10)
                score = (score/selectedGame.getTemplate().getNumOfQuestions()) ;



                // Display Result
                Stage stage = (Stage) nextQuestionButton.getScene().getWindow();
                stage.close();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Score");
                stage.setMinWidth(400);
                stage.setMinHeight(300);
                stage.getIcons().add(
                        new Image(
                                MainWindowController.class.getResourceAsStream( "Island.png" )));
                stage.setResizable(false);


                Label scoreMessageLabel = new Label("You Scored:");
                scoreMessageLabel.setStyle("-fx-font-size: 18");
                Label scoreLabel = new Label();
                scoreLabel.setText(Float.toString(score*10) + " / 10");
                scoreLabel.setTextFill(Color.RED);
                scoreLabel.setStyle("-fx-font-size: 18");
                HBox layout = new HBox();
                layout.getChildren().addAll(scoreMessageLabel , scoreLabel);
                layout.setSpacing(50);
                layout.setAlignment(Pos.CENTER);

                Scene scene = new Scene(layout);
                stage.setScene(scene);
                stage.showAndWait();
            }
            else{

                // calculate partial score
                if (userAnswer.getText().equalsIgnoreCase(
                        selectedGame.getTemplate().getQuestion(nextQuestionIndex-1).get(2))
                        ){
                    score++;
                }

                // change button text
                if (nextQuestionIndex == (selectedGame.getTemplate().getNumOfQuestions() - 1)){
                    nextQuestionButton.setText("Get Result");
                }


                // display next question
                userAnswer.clear();

                if (selectedGame.getGameType().equals("MCQ")){
                    displayQuestionMCQ();
                }
                else if (selectedGame.getGameType().equals("True and False")){
                    displayQuestionTF();
                }

            }
        }

    }


    private void displayQuestionMCQ(){  // for MCQs
        ArrayList<String> question = selectedGame.getTemplate().getQuestion(nextQuestionIndex);
        questionBody.setText(question.get(0));
        String [] answers = question.get(1).split(";");
        ans1.setText(answers[0]);
        ans2.setText(answers[1]);
        ans3.setText(answers[2]);
        ans4.setText(answers[3]);

        nextQuestionIndex++;

    }

    private void displayQuestionTF(){  // for true & false
        ArrayList<String> question = selectedGame.getTemplate().getQuestion(nextQuestionIndex);
        questionBody.setText(question.get(0));
        String [] answers = question.get(1).split(";");
        ans1.setText("");
        ans2.setText(answers[0]);
        ans3.setText(answers[1]);
        ans4.setText("");

        nextQuestionIndex++;

    }

}