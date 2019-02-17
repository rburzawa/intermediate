package pl.sda.intermediate.customer;

import java.util.HashMap;
import java.util.Map;

public class UserValidationService {

    public Map<String, String> validateUser(UserRegistrationDTO dto){

        Map<String, String> errorsMap = new HashMap<>();

        if (!dto.getFirstName().matches("[A-Z][a-z]{2,}")){
            errorsMap.put("firstNameValRes", "Imię jest wymagane. Przynajmniej 3 znaki");
        }
        if (!dto.getLastName().matches("[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?")) {
            errorsMap.put("lastNameValRes", "Nazwisko jest wymagane. Przynajmniej 3 znaki");
        }
        if (!dto.getZipCode().matches("[0-9{2}-[0-9]{3}")) {
            errorsMap.put("zipCodeValRes", "Zły format. Kod pocztowy powinien mieć format 12-123");
        }
        if (!dto.getBirthDate().matches("(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
            errorsMap.put("birthDateValRes", "Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD");
        }
        if (!dto.getEMail().matches("^[\\w\\.]+@([A-Za-z0-9]+\\.){1,2}[A-Za-z]{2,4}$")) {
            errorsMap.put("loginValRes", "Zły format adresu e-mail");
        }
        if (!dto.getPhone().matches("^(\\+[0-9]{1,3} )?([0-9]{9}|[0-9]{3}-[0-9]{3}-[0-9]{3})$")) {
            errorsMap.put("phoneValRes", "Zły format. Numer telefonu powinien składać się z 9 cyfr");
        }


        return errorsMap;
    }
}
