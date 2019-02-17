package pl.sda.intermediate.customer;

import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {

    private String login;

    public void logUserIn(String eMail){
        login=eMail;
    }
}
