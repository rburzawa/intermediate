package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate.category.Category;
import pl.sda.intermediate.category.InMemoryCategoryDAO;

import java.util.List;

class InMemoryCategoryDAOTest {

    @Test
    void shouldPopulateParentCategoryIds() {
        List<Category> categories = InMemoryCategoryDAO.getInstance().getCategories();

        Assertions.assertEquals(Integer.valueOf(4), categories.stream()
                .filter(s -> s.getId()
                        .equals(5))
                        .findFirst()
                        .get()
                        .getParentId());
    }
}