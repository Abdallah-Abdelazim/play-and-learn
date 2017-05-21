package swe;

import java.util.Scanner;

import swe.game_DB;
public class game implements game_Interface{
        game_DB DB = new game_DB();
	String Name;
	String Type;
	String Category;
	int Score;
	int HighScore;
	private Scanner x;
	public game(String name, String type, String category, int highScore) {
		super();
		Name = name;
		Type = type;
		Category = category;
		HighScore = highScore;
	}
	
	public game() {
		super();
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getHighScore() {
		return HighScore;
	}
	public void setHighScore(int highScore) {
		HighScore = highScore;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public void save(game g){
		DB.G.addElement(g);
	}
        public void saveMCQ(MCQ g){
		DB.M.addElement(g);
        }
        public void saveCheck(Check g){
		DB.C.addElement(g);
        }
        public void Show(){
		for(int i = 0 ;i < DB.G.size();i++){
			System.out.println(i + ")" + DB.G.get(i).Category);
		}
		System.out.print("your Choise -> ");
		x = new Scanner(System.in);
		String s = x.nextLine();
		for(int i = 0 , j =0;i<DB.G.size();i++){
			if(DB.G.get(i).Category.equals(s))
			{
				System.out.println( j + ")" + DB.G.get(i).Name);
				j++;
			}
		}
                System.out.print("your Choise -> ");
                String q = x.nextLine();
                for(int i = 0;i<DB.G.size();i++)
                {
                    if(DB.G.get(i).Name.equals(q))
                    {
                        if(DB.G.get(i).Type.equals("MCQ")){
                            for(int x=0;x<DB.M.size();x++){
                                if(DB.M.get(x).Name.equals(q)){
                                    MCQ M =  DB.M.get(x);
                                    M.play();
                                }
                            }
                        }
                        if(DB.G.get(i).Type.equals("Check")){
                            for(int x=0;x<DB.C.size();x++){
                                if(DB.C.get(x).Name.equals(q)){
                                    Check C =  DB.C.get(x);
                                    C.play();
                                }
                            }
                        }
                    }
                }
		
	}
}
