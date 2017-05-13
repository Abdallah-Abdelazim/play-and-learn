package play_and_learn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_changes")
public class GameChange {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int gameChangeID;
	
	private String gameChangeType; // edit game, add new collaborator, cancel game and un-cancel game
	private String changerTeacherUsername;
	
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;
	
	public GameChange() {
		gameChangeType = "";
		changerTeacherUsername = "";
	}
	
	public GameChange(String gameChangeType, String changerTeacherUsername) {
		this.gameChangeType = gameChangeType;
		this.changerTeacherUsername = changerTeacherUsername;
	}
	
	public int getGameChangeID() {
		return gameChangeID;
	}
	public void setGameChangeID(int gameChangeID) {
		this.gameChangeID = gameChangeID;
	}
	public String getGameChangeType() {
		return gameChangeType;
	}
	public void setGameChangeType(String gameChangeType) {
		this.gameChangeType = gameChangeType;
	}
	public String getChangerTeacherUsername() {
		return changerTeacherUsername;
	}
	public void setChangerTeacherUsername(String changerTeacherUsername) {
		this.changerTeacherUsername = changerTeacherUsername;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
