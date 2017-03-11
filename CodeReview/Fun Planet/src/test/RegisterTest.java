package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swe.Register;

public class RegisterTest {
	@DataProvider(name="register")
	public static Object[][] checknumber(){
		
		return new Object[][] {{"name",  "email",  "userName",  "password",  "studyCateg", "role", 2},
			{"a",  "b",  "x",  "p",  "g", "r", 2},
			{"mohmed",  "l",  "ame",  "ord",  "sateg", "re", 2},
			{"zizo",  "el",  "ame",  "pord",  "sCateg", "ro", 2},
			{"abdo",  "il",  "usee",  "pd",  "steg", "le", 2}
		};
		
	}
	
  @Test(dataProvider="register")
  public void RegisterTestme(String name, String email, String userName, String password, String studyCateg, String role,
			int age) {
	 Register r=new Register( name,  email,  userName,  password,  studyCateg, role, age);
	 Assert.assertEquals( age,r.getAge()  );
	 Assert.assertEquals( name,r.getName() );
	 Assert.assertEquals( email,r.getEmail()  );
	 Assert.assertEquals( userName,r.getUserName()  );
	 Assert.assertEquals( password,r.getPassword()  );
	 Assert.assertEquals( role,r.getRole()  );
  }
}
