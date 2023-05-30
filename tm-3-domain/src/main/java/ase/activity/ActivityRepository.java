package ase.activity;

import java.util.List;

public interface ActivityRepository {
    void save(Activity activity);
    boolean existsById(Long id);
    List<Activity> findAll();
    Activity getByName(String name);
    void delete(Activity activity);
}
