package play_and_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import play_and_learn.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUsername(String username);
}
