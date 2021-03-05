package pl.edu.wszib.bryan.air.model.view;

public class UserRegistrationModel {
    private String login;
    private String pass;
    private String pass2;
    private String name;
    private String surname;
    private String passportNumber;
    private String personalIdentityNumber;

    public UserRegistrationModel(String login, String pass, String pass2, String name, String surname, String passportNumber, String personalIdentityNumber) {
        this.login = login;
        this.pass = pass;
        this.pass2 = pass2;
        this.name = name;
        this.surname = surname;
        this.passportNumber = passportNumber;
        this.personalIdentityNumber = personalIdentityNumber;
    }

    public UserRegistrationModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public void setPersonalIdentityNumber(String personalIdentityNumber) {
        this.personalIdentityNumber = personalIdentityNumber;
    }
}
