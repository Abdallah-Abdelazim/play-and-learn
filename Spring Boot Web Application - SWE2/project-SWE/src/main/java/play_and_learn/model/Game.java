package play_and_learn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int gameID;
    private String name;
    private String description;
    private String creatorTeacherUsername;
    private String gameType; // MCQ , True & False , etc.
    
    protected  int numOfQuestions = 0;
    
    @ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
    
    @OneToMany(targetEntity = Question.class, mappedBy = "q_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Question> questions;

    public Game() {
        name="";
        description="";
        creatorTeacherUsername = "";
        gameType = "";
        numOfQuestions = 0;
        questions = new ArrayList<>();
    }

    public Game(String name, String description, String creatorTeacherUsername 
    		, String gameType, int numOfQuestions) {
        this.name = name;
        this.description = description;
        this.creatorTeacherUsername = creatorTeacherUsername;
        this.gameType = gameType;
        this.numOfQuestions = numOfQuestions;
        questions = new ArrayList<>();
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


	@Override
	public String toString() {
		return "Game [gameID=" + gameID + ", name=" + name + ", description=" + description
				+ ", creatorTeacherUsername=" + creatorTeacherUsername + ", gameType=" + gameType + ", numOfQuestions="
				+ numOfQuestions + ", questions=" + questions + "]";
	}


}
