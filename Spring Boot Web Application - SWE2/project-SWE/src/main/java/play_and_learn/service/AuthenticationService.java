package play_and_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play_and_learn.model.User;

@Service
public class AuthenticationService implements IAuthenticationService{	
	@Autowired
    private IUserService userService;
	
	@Override
	public boolean verifyUser(String username, String password) {
		User user = userService.findByUsername(username);
		
		if (user!=null && user.getPassword().equals(password)){
			return true;
		} else {
			return false;
		}
	}	
	
}
