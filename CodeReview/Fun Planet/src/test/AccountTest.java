package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe.Account;
import swe.Account_DB;

public class AccountTest {
	Account account = new Account();
	
	@DataProvider(name="testData")
	public static Object[][] testData(){
		return new Object [][] {{"Ahmed Yassin", "ahmed@gmail.com", "ahmed", "1234", "Programming", "Teacher", 40}
		, {"Emad Nasser", "omda@gmail.com", "emad", "aaaa", "Physics", "Student", 20 }
		, {"Ziad Osama", "ziad@gmail.com", "ziad", "146", "Science", "Student", 19} };
	}
	
	@Test(dataProvider = "testData")
	public void accountTest(String name, String email, String userName, String password,
			String studyCateg, String role, int age){
		account.setAge(age);
		account.setEmail(email);
		account.setName(name);
		account.setPassword(password);
		account.setRole(role);
		account.setStudyCateg(studyCateg);
		account.setUserName(userName);
		
		Assert.assertEquals(name, account.getName());
		Assert.assertEquals(email, account.getEmail());
		Assert.assertEquals(userName, account.getUserName());
		Assert.assertEquals(password, account.getPassword());
		Assert.assertEquals(studyCateg, account.getStudyCateg());
		Assert.assertEquals(role, account.getRole());
		Assert.assertEquals(age, account.getAge());
		
	}
	  @DataProvider(name="save")
		public static Object[][]checksave(){
			Account a=new Account(),b=new Account(),c=new Account(),d=new Account() 
					,r= new Account(),m=new Account();
			return new Object[][] {{a},{b},{c},{d},{r},{m}};	
		}
	  @Test(dataProvider="save")
	  public void AccountSaveTest(Account a){
		  int c= Account_DB.a.size();
		  a.Save(a);
		  c++;
		  int c1= Account_DB.a.size();
		  Assert.assertEquals( c,c1  );
		  
		  
		  
	  }
}
