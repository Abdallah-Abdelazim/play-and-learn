package play_and_learn.service;

public interface IAuthenticationService {
	boolean verifyUser(String username, String password);
}
