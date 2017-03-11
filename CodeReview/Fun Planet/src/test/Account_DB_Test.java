package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.testng.Assert;
import org.testng.annotations.Test;

import swe.Account;
import swe.Account_DB;

public class Account_DB_Test {
	/**
	 * This function tests the read/write operations done by the Account_DB class
	 * First Scenario checks if the class stores all the Accounts data correctly, not part of them,.
	 */
	@Test(testName="First_Scenario_Test")
	public void TestReadWrite1(){
		
		Vector<Account> accountsVector = new Vector<>();
		accountsVector.addElement(new Account("Ahmed Yassin", "ahmed@gmail.com", "ahmed", "1234", "Programming", "Teacher", 40 ));
		accountsVector.addElement(new Account("Emad Nasser", "omda@gmail.com", "emad", "aaaa", "Physics", "Student", 20 ));
		accountsVector.addElement(new Account("Ziad Osama", "ziad@gmail.com", "ziad", "146", "Science", "Student", 19 ));
		
		Account_DB accountDB = new Account_DB();
		
		// perform a write operation to the file
		Account_DB.a.removeAllElements();
		Account_DB.a.addAll(accountsVector);
		try {
			accountDB.writeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// perform a read operation from the file
		Account_DB.a.clear();
		try {
			accountDB.readfile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// compare what is read with what was written
		Assert.assertEquals(accountsVector, Account_DB.a);
		
	}
	
	/**
	 * This function tests the read/write operations done by the Account_DB class
	 * Second Scenario checks if the class stores username, password & role in the accounts correctly, not all of the accounts data,.
	 */
	@Test(testName="Second_Scenario_Test")
	public void TestReadWrite2(){
		
		Vector<Account> accountsVector = new Vector<>();
		Account a = new Account();
		a.setUserName("ahmed");
		a.setPassword("1234");
		a.setRole("Teacher");
		accountsVector.addElement(a);
		
		Account b = new Account();
		b.setUserName("emad");
		b.setPassword("aaaa");
		b.setRole("Student");
		accountsVector.addElement(b);
		
		Account c = new Account();
		c.setUserName("ziad");
		c.setPassword("146");
		c.setRole("Student");
		accountsVector.addElement(c);
		
		Account_DB accountDB = new Account_DB();
		
		// perform a write operation to the file
		Account_DB.a.removeAllElements();
		Account_DB.a.addAll(accountsVector);
		try {
			accountDB.writeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// perform a read operation from the file
		Account_DB.a.removeAllElements();
		try {
			accountDB.readfile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		
		// compare what is read with what was written
		Assert.assertEquals(accountsVector, Account_DB.a);
		
		
	}

}
