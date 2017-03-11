package swe;

import java.util.Scanner;
import java.util.Vector;

public class Check extends game implements Check_Interface  {
        int number = 0;
	public Check(String name, String type, String category) {
		super(name, type, category, 0);
		// TODO Auto-generated constructor stub
	}
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}
	Vector<String>Quesions = new Vector<>();
	Vector<String> answers = new Vector<>();
	Student s = new Student();
	public Vector<String> getQuesions() {
		return Quesions;
	}
	public void setQuesions(Vector<String> quesions) {
		Quesions = quesions;
	}
	public Vector<String> getAnswers() {
		return answers;
	}
	public void setAnswers(Vector<String> answers) {
		this.answers = answers;
	}
	public void Score(int i , String Answer){
		if(answers.elementAt(i-1) == Answer)
		{
			Score++;
		}
	}
        public void getdata(){
            Scanner x = new Scanner(System.in); 
            System.out.println("NO. of Questions");
            int z = Integer.parseInt(x.next());
            for(int i =0;i<z;i++)
            {
                System.out.println("Enter question :");
                String f = x.nextLine();
                Quesions.addElement(f);
                //System.out.println("Enter Suggested Choises : ");
                System.out.println("enter right Choise");
                String s = x.nextLine();
                answers.addElement(s);
                //stanswer.clear();
            }
        }
        public void play()
        {
            for(int i =0;i<Quesions.size();i++){
            System.out.println("question " + i + " " + Quesions.elementAt(i));
                System.out.println("your answer ->");
                Scanner x = new Scanner(System.in);
                String s = x.nextLine();
                if(answers.get(i).equals(s))
                {
                    number++;
                }
                
            }
            System.out.println("your Score = " + number);
        }
}
