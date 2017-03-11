package swe;

import java.util.Scanner;
import java.util.Vector;

public class MCQ extends game implements MCQ_Interface{
	public MCQ(String name, String type, String category) {
		super(name, type, category,0);
	}
	public MCQ() {
		super();
	}
        int number = 0;
	Vector<String> Questions = new Vector<>();
	Vector<Vector<String>>SuggestAnswers= new Vector<>();
	Vector<String> Answer= new Vector<>();
	public Vector<String> getQuestions() {
		return Questions;
	}
	public void setQuestions(Vector<String> questions) {
		Questions = questions;
	}
	public Vector<Vector<String>> getSuggestAnswers() {
		return SuggestAnswers;
	}
	public void setSuggestAnswers(Vector<Vector<String>> suggestAnswers) {
		SuggestAnswers = suggestAnswers;
	}
	public Vector<String> getAnswer() {
		return Answer;
	}
	public void setAnswer(Vector<String> answer) {
		Answer = answer;
	}
        public void getdata(){
            Scanner x = new Scanner(System.in); 
            System.out.println("NO. of Questions");
            String z = x.nextLine();
            int z1 = Integer.parseInt(z);
            for(int i =0;i<z1;i++)
            {
                System.out.println("Enter question :");
                String f = x.nextLine();
                Questions.addElement(f);
                System.out.println("Enter Suggested Choises : ");
                Vector<String>stanswer = new Vector();
                for(int j =0;j<4;j++)
                {                   
                    String s = x.nextLine();
                    stanswer.addElement(s);
                }
                SuggestAnswers.addElement(stanswer);
                System.out.println("enter right Choise");
                String s = x.nextLine();
                Answer.addElement(s);
                //stanswer.clear();
            }
        }
        public void play()
        {
            for(int i =0;i<Questions.size();i++){
            System.out.println("question " + i + " " + Questions.elementAt(i));
            for(int j = 0; j<4 ;j++)
            {
                System.out.println(j+1 + ")" + SuggestAnswers.elementAt(i).get(j));
            }
                System.out.println("your answer ->");
                Scanner x = new Scanner(System.in);
                String s = x.nextLine();
                if(Answer.get(i).equals(s))
                {
                    number++;
                }
                
            }
            System.out.println("your Score = " + number);
        }
        
}
