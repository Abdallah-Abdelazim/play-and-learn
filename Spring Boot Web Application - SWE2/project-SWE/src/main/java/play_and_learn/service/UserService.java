package play_and_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play_and_learn.ProjectSweApplication;
import play_and_learn.model.User;
import play_and_learn.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public long getNumberOfRecords() {  // number of users
    	return userRepository.count();
    }
    
    public String getLoggedInUser() {
    	return ProjectSweApplication.activeUsername;
    }

	public void setActiveUsername(String activeUsername) {
		ProjectSweApplication.activeUsername = activeUsername;
	}

}