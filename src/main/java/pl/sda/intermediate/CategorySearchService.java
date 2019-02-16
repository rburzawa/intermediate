package pl.sda.intermediate;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchService {

    private InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

    public List<CategoryDTO> filterCategories(String input) {

        List<Category> categories = inMemoryCategoryDAO.getCategories();

        List<CategoryDTO> resultList = new ArrayList<>();

        for (Category category : categories) {
            resultList.add(buildCategoryDTO(category));
        }
        for (CategoryDTO categoryDTO : resultList) { //TODO create Map
            categoryDTO.setParentCat(
                    resultList.stream()
                            .filter(s -> s.getId().equals(categoryDTO.getParentId()))
                            .findFirst()
                            .orElse(null));
        }
        for (CategoryDTO categoryDTO : resultList) {
            if (input != null && !input.trim().isEmpty() && categoryDTO.getName().equals(input.trim())) {
                categoryDTO.getCategoryState().setOpen(true);
                categoryDTO.getCategoryState().setSelected(true);
                openParent(categoryDTO);
            }
        }
        return resultList;

    }

    private void openParent(CategoryDTO child) {
        CategoryDTO parent = child.getParentCat();

        if (parent == null){
            return;
        }

        parent.getCategoryState().setOpen(true);
        openParent(parent);

    }

    private CategoryDTO buildCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName().trim());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setCategoryState(new CategoryState());
        return categoryDTO;
    }

}
