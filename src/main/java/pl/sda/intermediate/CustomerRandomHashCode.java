package pl.sda.intermediate;

import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
public class CustomerRandomHashCode {

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRandomHashCode that = (CustomerRandomHashCode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return new Random().nextInt(11);
    }
}
