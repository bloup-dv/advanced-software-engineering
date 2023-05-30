package ase.activity;

import ase.category.Category;
import ase.types.Status;

import java.util.List;

public interface ActivityRepository {
    void save(Activity activity);
    boolean existsById(Long id);
    List<Activity> findAll();
    List<Activity> findAllByCategory(Category category);
    List<Activity> findAllByStatus(Status status);

    Activity getByName(String name);
    void delete(Activity activity);
}
