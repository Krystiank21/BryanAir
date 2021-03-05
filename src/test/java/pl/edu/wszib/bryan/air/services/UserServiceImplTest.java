package pl.edu.wszib.bryan.air.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.bryan.air.configuration.TestConfiguration;
import pl.edu.wszib.bryan.air.dao.IFlightDAO;
import pl.edu.wszib.bryan.air.dao.ITicketDAO;
import pl.edu.wszib.bryan.air.dao.IUserDAO;
import pl.edu.wszib.bryan.air.model.User;
import pl.edu.wszib.bryan.air.model.view.UserRegistrationModel;
import pl.edu.wszib.bryan.air.session.SessionObject;
import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IFlightDAO flightDAO;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    ITicketDAO ticketDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void registerTest() {
        UserRegistrationModel registrationModel = new UserRegistrationModel();
        registrationModel.setLogin("Michal");
        registrationModel.setPass("michal");
        registrationModel.setPass2("michal");
        Mockito.when(this.userDAO.getUserByLogin("michal")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);
        boolean result = userService.register(registrationModel);
        Assert.assertTrue(result);
    }

    @Test
    public void registerLoginIncorrectTest() {
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
        userRegistrationModel.setLogin("kamil");
        userRegistrationModel.setPass("kamil");
        userRegistrationModel.setPass2("kamil");
        Mockito.when(this.userDAO.getUserByLogin("kamil")).thenReturn(new User());
        boolean result = userService.register(userRegistrationModel);
        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest() {
        User user = new User();
        user.setLogin("Kasia");
        user.setPass("Kasia");
        Mockito.when(this.userDAO.getUserByLogin("Kasia")).thenReturn(generateUser());
        this.userService.authenticate(user);
        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    private User generateUser() {
        User user = new User();
        user.setId(5);
        user.setLogin("Kasia");
        user.setPass("Kasia");
        user.setRole(User.Role.USER);
        return user;
    }

    @Test
    public void incorrectAuthenticationTest() {
        User user = new User();
        user.setLogin("jan");
        user.setPass("jan");
        Mockito.when(this.userDAO.getUserByLogin("jan")).thenReturn(null);
        this.userService.authenticate(user);
        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectPassTest() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("pawel");
        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(generateUser());
        this.userService.authenticate(user);
        Assert.assertNull(this.sessionObject.getLoggedUser());
    }
}

