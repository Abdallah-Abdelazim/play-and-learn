package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe.Check;
import swe.MCQ;
import swe.game_DB;

public class gameTest {

@DataProvider(name="savegame")
public static Object[][]checksavemcq(){
	MCQ a=new MCQ(),b=new MCQ(),c=new MCQ(),d=new  MCQ() 
			,r= new MCQ(),m=new MCQ();
	return new Object[][] {{a},{b},{c},{d},{r},{m}};	
}
@Test(dataProvider="savegame")
public void SaveMCQGameTest(MCQ a){
	int c=game_DB.M.size();
	game_DB.M.addElement(a);
  
  c++;
  int c1= game_DB.M.size();
  Assert.assertEquals( c,c1  );
  
  
  
}
@DataProvider(name="savegamecheck")
public static Object[][] checksavecheck(){
	Check a=new Check(),b=new Check(),c=new Check(),d=new  Check() 
			,r= new Check(),m=new Check();
	return new Object[][] {{a},{b},{c},{d},{r},{m}};	
}
@Test(dataProvider="savegamecheck")
public void SaveCheckGameTest(Check a){
	int c=game_DB.C.size();
	game_DB.C.addElement(a);
  
  c++;
  int c1= game_DB.C.size();
  Assert.assertEquals( c,c1  );
  
  
  
} 
}
