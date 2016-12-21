package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Template implements Serializable {
    /*
    * This class contains the main components of any game templates that wil be created in the future
    * This application Template supports only question-answer-based games
    */

    protected  int numOfQuestions = 0;
    protected ArrayList<ArrayList<String>> questions = new ArrayList<>(numOfQuestions);
    // example Question : ["questionBody" , "ans1;ans2;ans3;ans4" , "theRightAnswer"]

    public int getNumOfQuestions() {
        return numOfQuestions;
    }


    public void addQuestion(String questionBody, String answers , String theCorrectAnswer){
        ArrayList<String> question = new ArrayList<>(3);
        question.add(questionBody);
        question.add(answers);
        question.add(theCorrectAnswer);

        questions.add(question);
        numOfQuestions++;
    }

    public ArrayList<String> getQuestion(int index){
        return questions.get(index);
    }

    @Override
    public String toString() {
        return "Template{" +
                "numOfQuestions=" + numOfQuestions +
                ", questions=" + questions +
                '}';
    }
}



//class Question implements Serializable{
//    private String questionBody;
//    private ArrayList<String> answers=new ArrayList<>();
//    private String theCorrectAnswer;
//
//    public Question(String questionBody, ArrayList<String> answers, String theCorrectAnswer) {
//        this.questionBody = questionBody;
//        this.answers = answers;
//        this.theCorrectAnswer = theCorrectAnswer;
//    }
//
//    public String getQuestionBody() {
//        return questionBody;
//    }
//
//    public void setQuestionBody(String questionBody) {
//        this.questionBody = questionBody;
//    }
//
//    public ArrayList<String> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(ArrayList<String> answers) {
//        this.answers = answers;
//    }
//
//    public String getTheCorrectAnswer() {
//        return theCorrectAnswer;
//    }
//
//    public void setTheCorrectAnswer(String theCorrectAnswer) {
//        this.theCorrectAnswer = theCorrectAnswer;
//    }
//
//    @Override
//    public String toString() {
//        return "Question{" +
//                "questionBody='" + questionBody + '\'' +
//                ", answers=" + answers +
//                ", theCorrectAnswer='" + theCorrectAnswer + '\'' +
//                '}';
//    }
//}