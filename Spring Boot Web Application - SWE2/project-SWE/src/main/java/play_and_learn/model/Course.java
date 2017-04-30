package play_and_learn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int courseId;
	private String courseName;
	private String courseDescription;
	private String creatorTeacherUsername;
	
	@OneToMany(targetEntity = Game.class, mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Game> courseGames;
	
	public Course(String courseName, String courseDescription
			, String creatorTeacherUsername, List<Game> courseGames) {
		super();
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.creatorTeacherUsername = creatorTeacherUsername;
		this.courseGames = courseGames;
	}
	
	public Course(String courseName, String courseDescription, String creatorTeacherUsername) {
		super();
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.creatorTeacherUsername = creatorTeacherUsername;
		this.courseGames = new ArrayList<Game>();
	}
	
	public Course(String courseName, String description) {
		super();
		this.courseName = courseName;
		this.courseDescription = description;
		this.creatorTeacherUsername = "";
		this.courseGames = new ArrayList<Game>();
	}
	
	public Course () {
		super();
		this.courseName = "";
		this.courseDescription = "";
		this.courseGames = new ArrayList<>();
	}
	
	public void addGame(Game game) {
		courseGames.add(game);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCreatorTeacherUsername() {
		return creatorTeacherUsername;
	}

	public void setCreatorTeacherUsername(String creatorTeacherUsername) {
		this.creatorTeacherUsername = creatorTeacherUsername;
	}

	public List<Game> getCourseGames() {
		return courseGames;
	}

	public void setCourseGames(List<Game> courseGames) {
		this.courseGames = courseGames;
	}
	
	public Game getGameByID(int gameID) {
		for (Game game : courseGames) {
			if (game.getGameId() == gameID) {
				return game;
			}
		}
		return null;
	}
	
	

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + ", courseGames=" + courseGames + "]";
	}
	
	
}
