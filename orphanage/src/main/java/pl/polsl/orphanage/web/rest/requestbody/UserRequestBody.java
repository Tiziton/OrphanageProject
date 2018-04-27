package pl.polsl.orphanage.web.rest.requestbody;

import java.util.List;

/**
 *
 * @author osveron
 */
public class UserRequestBody {

    private String login;
    private String password;
    private List<String> roles;

    public UserRequestBody() {

    }

    public UserRequestBody(String login, String password, List<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

}
