package play_and_learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play_and_learn.model.Game;
import play_and_learn.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;
	
	/**
	 * @return All games in the system
	 */
	public List<Game> getAllGames(){
		return gameRepository.findAll();
	}
	
	public Game findByID(int gameID) {
		return gameRepository.findByGameID(gameID);
	}

}
