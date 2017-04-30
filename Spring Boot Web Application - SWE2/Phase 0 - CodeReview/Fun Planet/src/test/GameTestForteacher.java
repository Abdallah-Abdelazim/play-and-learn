package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe.Teacher;
import swe.game;
import swe.game_DB;

public class GameTestForteacher  {
	  
	  @DataProvider(name="game")
		public static Object[][]checkgame(){
			game a=new game(),b=new game(),c=new game(),d=new game() 
					,r= new game(),m=new game();
			return new Object[][] {{a},{b},{c},{d},{r},{m}};	
		}
	  @Test(dataProvider="game")
	  public void checkEditTest(game a){
		String name ="math";
	     a.setName(name);
	     Teacher t =new Teacher();
	     t.EditGame();
	  	 Assert.assertNotEquals( a.getName(), name );
	  	  
	  	  
	  	  
	  }
	@Test(dataProvider="game")
	public void checkDeleteTest(game a){
		int c=game_DB.G.size();
		game_DB.G.addElement(a);
		Teacher t=new Teacher();
		t.DeleteGame();
		int c1=game_DB.G.size();
		Assert.assertEquals( c,c1  );
		  
		  
		  
	}
}
