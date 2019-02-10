package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCategoryDAOTest {

    @Test
    void shouldPopulateParentCategoryIds() {
        List<Category> categories = InMemoryCategoryDAO.getInstance().categories;

        Assertions.assertEquals(Integer.valueOf(4), categories.stream()
                .filter(s -> s.getId()
                        .equals(5))
                        .findFirst()
                        .get()
                        .getParentId());
    }
}