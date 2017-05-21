package play_and_learn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author abdal
 *
 */
@Entity
@Table(name = "questions")
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int q_id;
	
	private String qBody;
	
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	
	private String theRighAnswer;
	
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	public Question(String qBody, String answer1, String answer2, String answer3, String answer4,
			String theRighAnswer) {
		super();
		this.qBody = qBody;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.theRighAnswer = theRighAnswer;
	}
	
	public Question() {
		super();
		this.qBody = "";
		this.answer1 = "";
		this.answer2 = "";
		this.answer3 = "";
		this.answer4 = "";
		this.theRighAnswer = "";
	}	

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getqBody() {
		return qBody;
	}

	public void setqBody(String qBody) {
		this.qBody = qBody;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getTheRighAnswer() {
		return theRighAnswer;
	}

	public void setTheRighAnswer(String theRighAnswer) {
		this.theRighAnswer = theRighAnswer;
	}

	@Override
	public String toString() {
		return "Question [q_id=" + q_id + ", qBody=" + qBody + ", answer1=" + answer1 + ", answer2=" + answer2
				+ ", answer3=" + answer3 + ", answer4=" + answer4 + ", theRighAnswer=" + theRighAnswer
				+ "]";
	}
	
	
}
