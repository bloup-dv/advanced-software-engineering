package ase.persistance.hibernate.activity;

import ase.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataActivityRepository extends JpaRepository<Activity, Long> {
    Activity getByName(String name);
    boolean existsById(Long id);


}
