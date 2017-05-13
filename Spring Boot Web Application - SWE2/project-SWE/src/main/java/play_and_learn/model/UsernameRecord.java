package play_and_learn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "new_game_notification_list")
public class UsernameRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	public UsernameRecord() {
		username = "";
	}
	
	public UsernameRecord(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public boolean equals(Object usernameRecord) {
		if (usernameRecord == null) {
	        return false;
	    }
		else if (this.username.equals(((UsernameRecord)usernameRecord).username)) {
			return true;
		}
		return false;
	}
}
