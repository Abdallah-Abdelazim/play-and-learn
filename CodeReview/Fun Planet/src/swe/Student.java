package swe;

import java.util.Scanner;

public class Student extends Account {
	int Score = 0;
	Scanner x =new Scanner(System.in);
	public Student() {
		super();
	}
	public Student(String name, String email, String userName, String password, String studyCateg, String role,
			int age) {
		super(name, email, userName, password, studyCateg, role, age);
	}
	public Student(String email, String password){
		super(email,password);
	}
        public void Show(){
		System.out.println("welcome Student........");
		System.out.println("1)Play Game");
		
		System.out.print("Your Choise -> ");
		int z = x.nextInt();
		if(z == 1){
			PlayGame();
                        
		}
	}
	public void PlayGame(){
		game g = new game();
		g.Show();
                Show();
	}
	
}
