package play_and_learn.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int notificationID;
	
	private String notificationText;
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private User user;
	
	public Notification() {
		notificationText="";
		date="";
	}
	
	public Notification(String notificationText) {
		this.notificationText = notificationText;
		this.date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
	}
	
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public String getNotificationText() {
		return notificationText;
	}
	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
}
