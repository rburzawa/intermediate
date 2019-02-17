package pl.sda.intermediate;

import pl.sda.intermediate.customer.UserRegistrationDTO;
import pl.sda.intermediate.customer.UserValidationService;

import java.util.Map;

public class OnlyOneController {

    UserValidationService userValidationService = new UserValidationService();

    public String registerEffects(UserRegistrationDTO userRegistrationDTO) {

        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);

        if (errorsMap.isEmpty()) {
            return "ok";
        } else {
            return "nie ok";
        }

    }
}
