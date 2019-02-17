package pl.sda.intermediate.customer;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    Map<String, User> userMap = new HashMap<>();

    public boolean userExist(String email) {
        return userMap.containsKey(email);
    }

}
