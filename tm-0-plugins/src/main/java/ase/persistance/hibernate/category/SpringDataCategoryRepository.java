package ase.persistance.hibernate.category;

import ase.category.Category;
import ase.timeframe.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataCategoryRepository extends JpaRepository<Category, Long> {
    Category getById(Long id);


}