package play_and_learn.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	protected String username;  // unique
	protected String name;
	protected String password;
	protected String email;
	protected Integer age ;
	protected String gender;
	protected String role; // student or teacher
	
	@OneToMany(targetEntity = Notification.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Notification> notifications;
	
	
	public User(String username, String name
			, String password, String email, int age, String gender, String role) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.role = role;
	}
	
	public User() {
		super();
		this.username = "";
		this.name = "";
		this.password = "";
		this.email = "";
		this.age = 0;
		this.gender = "";
		this.role = "";
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.name = "";
		this.password = password;
		this.email = "";
		this.age = 0;
		this.gender = "";
		this.role = "";
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
}
