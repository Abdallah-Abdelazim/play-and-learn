package play_and_learn;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

import play_and_learn.model.User;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TestAccount extends AbstractTestNGSpringContextTests{
	@Autowired
    private UserService userService;
	@Autowired
	AuthenticationService authService;
	
	@DataProvider(name="accounts1")
	public static Object[][] accounts1(){
		return new Object [][] {{"ahmed", "12345"}, { "Mohammed", "23443"}
		, { "ziad", "146fg"} , { "yasser", "XxX58"}};
	}
	
	@Test(dataProvider="accounts1")
  public void f(String username, String password) {
	 
	  User user = new User(username, password);
	  long c=  userService.getNumberOfRecords();
	  userService.save(user);
	  c++;
	  long c1= userService.getNumberOfRecords();
	  Assert.assertEquals( c,c1  );
  }
	
	@DataProvider(name="accounts2")
	public static Object[][] accounts2(){
		return new Object [][] {{"tony", "12345"}, { "marko", "23443"}
		, { "henry", "146fg"} , { "lolo", "XxX58"}};
	}
	
	@Test(dataProvider="accounts2")
	  public void f1(String username, String password) {
		 
		  User user =new User(username, password);	
		  userService.save(user);
		  Assert.assertEquals( user.getUsername()
				  ,userService.findByUsername(username).getUsername() );
		  Assert.assertEquals( user.getPassword()
				  ,userService.findByUsername(username).getPassword());
	  }
	
	
	@DataProvider(name="account")
	public static Object[][] account() {
		return new Object [][] {{true ,"tony", "12345"}, {true, "marko", "23443"}
		, {false, "henry", "146"} , {false, "polo", "XxX"}, {true, "henry", "146fg"} , {false, "lolo", "XxX"}};
	}
	
	@Test(dataProvider="account")
	  public void f2(boolean t,String username, String password) {
		Assert.assertEquals( t ,authService.verifyUser(username, password) );
	  }
}



