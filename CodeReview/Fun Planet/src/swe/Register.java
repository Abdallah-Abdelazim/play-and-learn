package swe;

import java.util.Scanner;

public class Register implements Register_Interface{
        private Scanner x = new Scanner(System.in);
	String Name;
	String Email;
	String UserName;
	String Password;
	String StudyCateg;
	String Role;
	int Age;
	public Register(String name, String email, String userName, String password, String studyCateg, String role,
			int age) {
		super();
		Name = name;
		Email = email;
		UserName = userName;
		Password = password;
		StudyCateg = studyCateg;
		Role = role;
		Age = age;
	}
	public void Show(){
		          System.out.println("your Name -> ");
                          String n = x.next();
                          System.out.println("Your Email -> ");
                          String e = x.next();
                          System.out.println("Your Userame -> ");
                          String u = x.next();
                          System.out.println("Your Password -> ");
                          String p = x.next();
                          System.out.println("Your Study Category -> ");
                          String s = x.next();
                          System.out.println("Your Role ->");
                          String r = x.next();
                          System.out.println("your Age -> ");
                          int a = x.nextInt();
                          Register A = new Register(n, e, u, p, r, s, a);
                          SaveAccount(A);
	}
	public Register() {
		super();
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getStudyCateg() {
		return StudyCateg;
	}
	public void setStudyCateg(String studyCateg) {
		StudyCateg = studyCateg;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public void SaveAccount(Register r){
		Account a = new Account(r.getName(),r.getEmail(),r.getUserName(),r.getPassword(),r.getRole(),r.getStudyCateg(),r.getAge());
		a.Save(a);
	}

}
