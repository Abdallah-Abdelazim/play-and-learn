package user;

import java.io.Serializable;

public class Account implements Serializable{
    public static int  count=0;
    private String username;
    private String password;
    private int id;
    private String completeName;
    private int age;
    private String email;
    private String gender;

    @Override
    public String toString() {
        return "Account [username=" + username + ", password=" + password + ", id=" + id + ", completeName=" + completeName
                + ", age=" + age + ", email=" + email + ", gender=" + gender  + "]";
    }

    public Account(String username, String password, String completeName, String email, int age, String gender) {
        this.username = username;
        this.password = password;
        id=count++;
        this.completeName = completeName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public Account() {
        this.username = "";
        this.password = "";
        id=count++;
        this.completeName = "";
        this.age = 0;
        this.email = "";
        this.gender = "";
    }
    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Account.count = count;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompleteName() {
        return completeName;
    }
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
