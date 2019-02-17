package pl.sda.intermediate.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String pesel;
    private String phone;
    private String passwordHash;
    private UserAddress userAdress;

}
