package pl.edu.wszib.bryan.air.dao;

import pl.edu.wszib.bryan.air.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
