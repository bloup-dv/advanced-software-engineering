package ase.category;

import ase.activity.Activity;

import java.util.List;

public interface CategoryRepository {
    void save(Category category);
    boolean existsById(Long id);
    List<Category> findAll();
    Category getByName(String name);
    void delete(Category category);
}
