package play_and_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import play_and_learn.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
	Game findByGameID(int gameID);
	List<Game> findAll();
}
