package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.training.hashCode.CustomerConstantHashCode;
import pl.sda.intermediate.training.hashCode.CustomerRandomHashCode;

import java.util.HashSet;
import java.util.Set;

public class HashCodeTest {

    @Test
    public void dummy() {

        CustomerConstantHashCode const1 = new CustomerConstantHashCode("Ania");
        CustomerConstantHashCode const2 = new CustomerConstantHashCode("Ania");
        CustomerConstantHashCode const3 = new CustomerConstantHashCode("Ania");
        CustomerConstantHashCode const4 = new CustomerConstantHashCode("Ania");
        CustomerConstantHashCode const5 = new CustomerConstantHashCode("Ania");

        CustomerRandomHashCode random1 = new CustomerRandomHashCode("Beata");
        CustomerRandomHashCode random2 = new CustomerRandomHashCode("Beata");
        CustomerRandomHashCode random3 = new CustomerRandomHashCode("Beata");
        CustomerRandomHashCode random4 = new CustomerRandomHashCode("Beata");
        CustomerRandomHashCode random5 = new CustomerRandomHashCode("Beata");

        Set<CustomerConstantHashCode> constantHashCodeSet = new HashSet<>();
        Set<CustomerRandomHashCode> randomHashCodeSet = new HashSet<>();

        constantHashCodeSet.add(const1);
        constantHashCodeSet.add(const2);
        constantHashCodeSet.add(const3);
        constantHashCodeSet.add(const4);
        constantHashCodeSet.add(const5);

        randomHashCodeSet.add(random1);
        randomHashCodeSet.add(random2);
        randomHashCodeSet.add(random3);
        randomHashCodeSet.add(random4);
        randomHashCodeSet.add(random5);

        System.out.println(constantHashCodeSet.size());

        System.out.println(randomHashCodeSet.size());

        System.out.println(randomHashCodeSet.contains(random5));




    }

    
}
