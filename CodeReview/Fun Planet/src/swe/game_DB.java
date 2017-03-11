package swe;

import java.util.Vector;

public class game_DB {
	//this class shows the Account database used in our game platform
	static public Vector<game> G = new Vector<>();
        static public Vector<MCQ> M = new Vector<>();
        static public Vector<Check> C = new Vector();
	game g;
	public game returnGame(){
		return g;
	}
	public game_DB() {
		super();
	}
	
}
