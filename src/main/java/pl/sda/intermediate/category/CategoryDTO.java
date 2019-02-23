package pl.sda.intermediate.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Integer id;
    private Integer parentId;
    private String name;
    private CategoryState categoryState;
    private CategoryDTO parentCat;

    public String getParent() {
        return parentId == null ? "#" : parentId.toString();
        //kiedy parentID jest nullem wyświetl # w przeciwnym razie...
    }

    public String getText() {
        return name;
    }

    public CategoryState getState() {
        return categoryState;
    }

}
