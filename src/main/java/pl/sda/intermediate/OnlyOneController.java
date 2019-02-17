package pl.sda.intermediate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.intermediate.category.CategoryDTO;
import pl.sda.intermediate.category.CategorySearchService;
import pl.sda.intermediate.customer.*;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private CategorySearchService categorySearchService;

    @RequestMapping(value = "/login", method = RequestMethod.GET) //oznaczamy metody reagujące na requesty
    public String loginForm(Model model) { //model to element przekazujacy dane miedzy frontem a aplikacją
        model.addAttribute("form", new UserLoginDTO());

        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(UserLoginDTO userLoginDTO, Model model) {

        boolean logged = userLoginService.loginUser(userLoginDTO);
        if (logged) {
            return "index"; //podajemy nazwę html'a, który ma się wyswietlic
        }
        model.addAttribute("form", new UserLoginDTO());
        model.addAttribute("error", "Błąd logowania");
        return "loginForm";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regigisterForm(Model model) {
        model.addAttribute("countries", Countries.values());
        model.addAttribute("form", new UserRegistrationDTO());
        return "registerForm";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffects(UserRegistrationDTO userRegistrationDTO, Model model) {

        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);

        if (errorsMap.isEmpty()) {
            userRegistrationService.registerUser(userRegistrationDTO);
            return "registerEffect";
        } else {
            model.addAllAttributes(errorsMap);
            model.addAttribute("countries", Countries.values());
            model.addAttribute("form", new UserRegistrationDTO());
            return "registerForm";
        }
    }

    public String categories(String searchText) {

        List<CategoryDTO> categories = categorySearchService.filterCategories(searchText);
        return "catspage";
    }
}
