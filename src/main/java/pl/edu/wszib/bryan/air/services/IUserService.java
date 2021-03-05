package pl.edu.wszib.bryan.air.services;

import pl.edu.wszib.bryan.air.model.User;
import pl.edu.wszib.bryan.air.model.view.UserRegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(UserRegistrationModel registrationModel);
}
