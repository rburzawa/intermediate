package pl.sda.intermediate.customer;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDAO {

    private final static String USERS_DATA_TXT = "C:/Users/admin/Programowanie/intermediate/src/main/resources/users_data.txt";
    private Map<String, User> userMap = new HashMap<>();

    {
        try (FileInputStream fis = new FileInputStream(USERS_DATA_TXT);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            userMap = (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean userExist(String email) {
        return userMap.containsKey(email);
    }

    public void saveUser(User user) {
        userMap.put(user.getEMail(), user);
        try (FileOutputStream fos = new FileOutputStream(USERS_DATA_TXT);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> findUserByEmail(String eMail){

        return Optional.ofNullable(userMap.get(eMail));
    }


}
