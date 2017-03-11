package swe;

public interface Register_Interface {
	public String getName();
	public void setName(String name);
	public String getEmail();
	public void setEmail(String email);
	public String getUserName();
	public void setUserName(String userName);
	public String getPassword();
	public void setPassword(String password);
	public String getStudyCateg();
	public void setStudyCateg(String studyCateg);
	public String getRole();
	public void setRole(String role);
	public int getAge();
	public void setAge(int age);
	public void SaveAccount(Register r);
        public void Show();
}
