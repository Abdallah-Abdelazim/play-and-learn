package play_and_learn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game implements IGame{  // QAGame
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int gameID;
    private String name;
    private String description;
    private String creatorTeacherUsername;
    private String gameType; // MCQ , True & False
    private String gameLevel;  // easy, hard, challenging
    private String gameCollaboratorTeacherUsername;  // another teacher who can edit this game
    
    private  int numOfQuestions = 0;
    
    private boolean cancelled;   // cancel flag
    
    @ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
    
    @OneToMany(targetEntity = Question.class, mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions;
    
    @OneToMany(targetEntity = Comment.class, mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(targetEntity = GameChange.class, mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GameChange> gameChanges;

    public Game() {
        name="";
        description="";
        creatorTeacherUsername = "";
        gameType = "";
        numOfQuestions = 0;
        questions = new ArrayList<>();
        cancelled = false;
    }

    public Game(String name, String description, String creatorTeacherUsername 
    		, String gameType, int numOfQuestions) {
        this.name = name;
        this.description = description;
        this.creatorTeacherUsername = creatorTeacherUsername;
        this.gameType = gameType;
        this.numOfQuestions = numOfQuestions;
        questions = new ArrayList<>();
        cancelled = false;
    }
    
    public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGameId() {
        return gameID;
    }

    public void setGameId(int id) {
        this.gameID = id;
    }

    public String getCreatorTeacherUsername() {
        return creatorTeacherUsername;
    }

    public void setCreatorTeacherUsername(String creatorTeacherUsername) {
        this.creatorTeacherUsername = creatorTeacherUsername;
    } 

    public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(String qBody, String theRightAnswer, String ... answers) {
		Question q = new Question();
		
		q.setqBody(qBody);
		q.setAnswer1(answers[0]);
		q.setAnswer2(answers[1]);
		q.setAnswer3(answers[2]);
		q.setAnswer4(answers[3]);
		q.setTheRighAnswer(theRightAnswer);
		
		questions.add(q);		
	}
	
	public void addQuestion(Question question) {		
		questions.add(question);		
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public String getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(String gameLevel) {
		this.gameLevel = gameLevel;
	}

	public String getGameCollaboratorTeacherUsername() {
		return gameCollaboratorTeacherUsername;
	}

	public void setGameCollaboratorTeacherUsername(String gameCollaboratorTeacherUsername) {
		this.gameCollaboratorTeacherUsername = gameCollaboratorTeacherUsername;
	}
	
	public List<GameChange> getGameChanges() {
		return gameChanges;
	}

	public void setGameChanges(List<GameChange> gameChanges) {
		this.gameChanges = gameChanges;
	}

	public void addGameChange(GameChange gameChange) {
		this.gameChanges.add(gameChange);
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public String toString() {
		return "Game [gameID=" + gameID + ", name=" + name + ", description=" + description
				+ ", creatorTeacherUsername=" + creatorTeacherUsername + ", gameType=" + gameType + ", numOfQuestions="
				+ numOfQuestions + ", questions=" + questions + "]";
	}


}
