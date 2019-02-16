package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategorySearchServiceTest {

    @Test
    void filterCategories() {

        CategorySearchService categorySearchService = new CategorySearchService();

        List<CategoryDTO> categories = categorySearchService.filterCategories("Lektury");
        CategoryDTO c1 = categories.stream()
                .filter(categoryDTO -> categoryDTO.getId().equals(1))
                .findFirst()
                .get();

        CategoryDTO c4 = categories.stream()
                .filter(categoryDTO -> categoryDTO.getId().equals(4))
                .findFirst()
                .get();

        CategoryDTO c5 = categories.stream()
                .filter(categoryDTO -> categoryDTO.getId().equals(5))
                .findFirst()
                .get();

        assertTrue(c1.getCategoryState().isOpen());
        assertTrue(c4.getCategoryState().isOpen());
        assertTrue(c4.getCategoryState().isSelected());

        assertFalse(c1.getCategoryState().isSelected());
        assertFalse(c5.getCategoryState().isOpen());
        assertFalse(c5.getCategoryState().isSelected());

    }
}