package pl.sda.intermediate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//DAO Data Access Object
public class InMemoryCategoryDAO {

    private static InMemoryCategoryDAO instance;
    List<Category> categories = new ArrayList<>();

    private InMemoryCategoryDAO() {
        initializeCategories();
    }

    public static InMemoryCategoryDAO getInstance() {
        if (instance == null) {
            synchronized (InMemoryCategoryDAO.class) {
                if (instance == null) {
                    instance = new InMemoryCategoryDAO();
                }
            }
        }
        return instance;
    }

    private void initializeCategories() {
        List<String> lines = loadCategoriesFromFile();
        List<Category> categoryList = new ArrayList<>();

        for (int i = 1; i <= lines.size(); i++) {

            categoryList.add(Category
                    .builder()
                    .id(i)
                    .name(lines.get(i - 1))
                    .build());
        }

        Map<Integer, List<Category>> categoriesMap = new HashMap<>();
        for (Category category : categoryList) {
            int depth;
            if (category.getName().startsWith(" ")) {
                String[] split = category.getName().split("\\S");
                depth = split[0].length();
            } else {
                depth = 0;
            }
            if (categoriesMap.containsKey(depth)) {
                categoriesMap.get(depth).add(category);
            } else {
                List<Category> innerList = new ArrayList<>();
                innerList.add(category);
                categoriesMap.put(depth, innerList);
            }
        }

        populateParentId(categoriesMap, 0);

        this.categories = categoryList;

    }

    private void populateParentId(Map<Integer, List<Category>> categoriesMap, int depth) {
        if (!categoriesMap.containsKey(depth)) {
            return;
        }
        List<Category> children = categoriesMap.get(depth);
        for (Category child : children) {
            if (depth == 0) {
                child.setParentId(0);
            } else {
                List<Category> potentialParents = categoriesMap.get(depth - 1);
                Integer parentId = potentialParents.stream()
                        .map(s -> s.getId())
                        .filter(id -> id < child.getId())
                        .sorted(Comparator.reverseOrder())
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Nie znaleziono rodzica"));

                child.setParentId(parentId);
            }
        }
        populateParentId(categoriesMap, depth + 1);
    }

    private List<String> loadCategoriesFromFile() {
        ClassLoader loader = this
                .getClass()
                .getClassLoader();

        try {
            Path path = Paths.get(loader
                    .getResource("kategorie.txt")
                    .toURI());
            return Files.readAllLines(path, Charset.forName("UNICODE"));
        } catch (URISyntaxException | IOException e) { // Laczenie dwóch Exceptionów przy pomocy " | "
            e.printStackTrace();
        }
        // return new ArrayList<>(); dobre rozwiązanie, ale nie potrzebne, bo zajmuje pamięć
        return Collections.emptyList();
    }
}
