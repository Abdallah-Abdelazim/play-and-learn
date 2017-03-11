package swe;

import java.util.Vector;

public interface MCQ_Interface {
	public Vector<String> getQuestions();
	public void setQuestions(Vector<String> questions);
	public Vector<Vector<String>> getSuggestAnswers();
	public void setSuggestAnswers(Vector<Vector<String>> suggestAnswers);
	public Vector<String> getAnswer();
	public void setAnswer(Vector<String> answer) ;
	//public void Score(int i , String answer);
}
