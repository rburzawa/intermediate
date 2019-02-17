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

}
