package pl.edu.wszib.bryan.air.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.bryan.air.model.User;
import pl.edu.wszib.bryan.air.model.view.UserRegistrationModel;
import pl.edu.wszib.bryan.air.services.IUserService;
import pl.edu.wszib.bryan.air.session.SessionObject;
import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.userService.authenticate(user);
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        this.sessionObject.getBasket().clear();
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("registrationModel", new UserRegistrationModel());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute UserRegistrationModel userRegistrationModel) {
        Pattern regexp = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher loginMatcher = regexp.matcher(userRegistrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(userRegistrationModel.getPass());
        Matcher pass2Matcher = regexp.matcher(userRegistrationModel.getPass2());

        if(!loginMatcher.matches() || !passMatcher.matches() || !pass2Matcher.matches() || !userRegistrationModel.getPass().equals(userRegistrationModel.getPass2())) {
            this.sessionObject.setInfo("validation error !!");
            return "redirect:/register";
        }

        if(this.userService.register(userRegistrationModel)) {
            return "redirect:/login";
        } else {
            this.sessionObject.setInfo("login zajęty !!");
            return "redirect:/register";
        }
    }
}
