 package swe;

import javax.security.auth.login.AccountNotFoundException;

public class Account {
	String Name ="";
	String Email ="";
	String UserName ="";
	String Password="";
	String StudyCateg="";
	String Role="";
	int Age=0;
	public Account() {
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
	public Account(String name, String email, String userName, String password, String studyCateg, String role,
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
	public Account(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}
	public void Save(Account b){
            Account_DB.a.add(b);
	}
	public Account Search(String username, String password){
		Account A = new Account();
		Account_DB aa = new Account_DB();
		for(int i =0;i<Account_DB.a.size();i++){
                    System.out.println(Account_DB.a.size());
                    //System.out.println(username);
                    //System.out.println(password);
                    System.out.println(Account_DB.a.get(i).UserName);
                    System.out.println(Account_DB.a.get(i).Password);
                    System.out.println(Account_DB.a.get(i).Role);
			if(Account_DB.a.get(i).UserName.equals(username) && Account_DB.a.get(i).Password.equals(password))
                        {
                            //System.err.println("FuckYou");
				A = Account_DB.a.get(i);
				break;
			}
		}
		return A;
	}
	
	/**
	 * method added to be used in testing	
	 * @param account
	 * @return
	 */
	@Override
	public boolean equals(Object account){
		return ( (this.UserName).equals(((Account)account).UserName)
				&&  (this.Password).equals(((Account)account).Password)
				&&  (this.Role).equals(((Account)account).Role)
				&&  (this.Name).equals(((Account)account).Name)
				&&  (this.Email).equals(((Account)account).Email)
				&&  (this.StudyCateg).equals(((Account)account).StudyCateg)
				&&  (this.Age) ==(((Account)account).Age)
				);
	}
}
