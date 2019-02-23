package pl.sda.intermediate.customer;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 7294500612408601029L;
    private String city;
    private String country;
    private String zipCode;
    private String street;
}
