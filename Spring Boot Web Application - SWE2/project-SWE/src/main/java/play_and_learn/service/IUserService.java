package play_and_learn.service;

import play_and_learn.model.User;

public interface IUserService {
    void save(User user);

    User findByUsername(String username);
    
    long getNumberOfRecords();
}