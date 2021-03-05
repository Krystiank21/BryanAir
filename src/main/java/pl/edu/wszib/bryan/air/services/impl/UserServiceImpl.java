package pl.edu.wszib.bryan.air.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bryan.air.dao.IUserDAO;
import pl.edu.wszib.bryan.air.model.User;
import pl.edu.wszib.bryan.air.model.view.UserRegistrationModel;
import pl.edu.wszib.bryan.air.services.IUserService;
import pl.edu.wszib.bryan.air.session.SessionObject;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void authenticate(User user) {
        User userFromDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDatabase == null) {
            return;
        }

        if(user.getPass().equals(userFromDatabase.getPass())) {
            this.sessionObject.setLoggedUser(userFromDatabase);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(UserRegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null) {
            return false;
        }

        User newUser = new User(0 ,registrationModel.getLogin(),
                registrationModel.getPass(), registrationModel.getName(),
                registrationModel.getSurname(), registrationModel.getPassportNumber(),
                registrationModel.getPersonalIdentityNumber(),
                User.Role.USER);

        return this.userDAO.persistUser(newUser);
    }
}
