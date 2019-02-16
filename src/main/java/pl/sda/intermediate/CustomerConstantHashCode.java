package pl.sda.intermediate;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class CustomerConstantHashCode {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerConstantHashCode that = (CustomerConstantHashCode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return 8;
    }


}
