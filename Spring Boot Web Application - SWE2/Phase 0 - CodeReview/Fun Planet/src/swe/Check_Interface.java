package swe;

import java.util.Vector;

public interface Check_Interface {
	public Vector<String> getQuesions();
	public void setQuesions(Vector<String> quesions);
	public Vector<String> getAnswers();
	public void setAnswers(Vector<String> answers);
	public void Score(int i , String Answer);
}
