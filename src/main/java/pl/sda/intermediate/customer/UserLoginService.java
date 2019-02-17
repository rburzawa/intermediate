package pl.sda.intermediate.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.DigestUtils.*;

@Service
public class UserLoginService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserContextHolder userContextHolder;

    public boolean loginUser(UserLoginDTO userLoginDTO) {

        boolean isAbletoLogIn = userDAO.findUserByEmail(userLoginDTO.getLogin())
                .map(user -> compareHashes(userLoginDTO, user))
                .orElse(false);
        if (isAbletoLogIn) {
            userContextHolder.logUserIn(userLoginDTO.getLogin());
        }
        return isAbletoLogIn;

    }

    private boolean compareHashes(UserLoginDTO userLoginDTO, User user) {
        return sha512Hex(userLoginDTO.getPassword()).equals(user.getPasswordHash());
    }
}
