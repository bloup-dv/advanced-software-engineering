package ase.persistance.hibernate.activity;

import ase.activity.Activity;
import ase.category.Category;
import ase.types.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpringDataActivityRepository extends JpaRepository<Activity, Long> {
    Activity getByName(String name);
    boolean existsById(Long id);
    List<Activity> findAllByCategory(Category category);
    List<Activity> findAllByStatus(Status status);


}
