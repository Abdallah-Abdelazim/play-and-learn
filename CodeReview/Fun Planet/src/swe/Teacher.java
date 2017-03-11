package swe;

import java.util.Scanner;
import java.util.Vector;

public class Teacher extends Account{
	Scanner x = new Scanner(System.in);
	Vector<game> Games = new Vector<>();
	public Teacher(Vector<game> games) {
		super();
		Games = games;
	}
	public Teacher() {
		super();
	}
	public void AddGame(){
		game g = new game();
		System.out.print("Name -> ");
		String name = x.nextLine();
		g.setName(name);
		System.out.print("Type ( MCQ / Check ) -> ");
		String Type = x.nextLine();
		g.setType(Type);
		System.out.print("Category -> ");
		String Categ = x.nextLine();
		g.setCategory(Categ);
                if(g.getType().equals("MCQ"))
                {
                    MCQ M = new MCQ(g.Name,g.Type,g.Category);
                    M.getdata();
                    g.saveMCQ(M);
                }
                if(g.getType().equals("Check")){
                    Check C=new Check(g.Name,g.Type,g.Category);
                    C.getdata();
                    g.saveCheck(C);
                }
		g.save(g);
		Games.addElement(g);
                Show();
	}
	public void EditGame(){
		
	}
	public void DeleteGame(){
		
	}
	public void Show(){
		System.out.println("welcome Teacher........");
		System.out.println("1)Create Game");
		System.out.println("2)Edit Game");
		System.out.println("3)Delete Game");
                System.out.println("any no. else -> logout");
		
		System.out.print("Your Choise -> ");
		String z = x.nextLine();
		if(z.equals("1")){
			AddGame();
		}
                if(z.equals("2")){
                    EditGame();
                }
                if(z.equals("3")){
                    DeleteGame();
                }
	}
	
}
