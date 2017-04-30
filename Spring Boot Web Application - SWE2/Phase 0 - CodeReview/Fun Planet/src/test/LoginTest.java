package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe.Account;
import swe.Account_DB;
import swe.Login;

public class LoginTest {
	
	@DataProvider(name="accounts")
	public static Object[][] accounts(){
		return new Object [][] {{true ,"ahmed", "1234"}, {false, "Mohammed", "23443"}
		, {true, "ziad", "146"} , {false, "yasser", "XxX"}};
	}

  @Test(dataProvider="accounts")
  public void Check(boolean result, String username, String password) {
	  
	  Account_DB.a.add(new Account("Ahmed Yassin", "ahmed@gmail.com", "ahmed", "1234", "Programming", "Teacher", 40 ));
	  Account_DB.a.add(new Account("Ziad Osama", "ziad@gmail.com", "ziad", "146", "Science", "Student", 19 ));
	  
	  Login login = new Login();
	  
	  
	  Assert.assertEquals(result, login.Check(username, password));
	  
  }
}
