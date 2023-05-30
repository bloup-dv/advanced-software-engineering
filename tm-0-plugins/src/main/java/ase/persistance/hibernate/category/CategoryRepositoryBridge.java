package ase.persistance.hibernate.category;


import ase.category.Category;
import ase.category.CategoryRepository;
import ase.persistance.hibernate.timeframe.SpringDataTimeFrameRepository;
import ase.timeframe.TimeFrame;
import ase.timeframe.TrimeFrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryBridge implements CategoryRepository {
    private SpringDataCategoryRepository springDataCategoryRepository;

    @Autowired
    public CategoryRepositoryBridge(SpringDataCategoryRepository springDataCategoryRepository)
    {
        this.springDataCategoryRepository = springDataCategoryRepository;
    }

    @Override
    public void save(Category category) { this.springDataCategoryRepository.save(category);
    }


    @Override
    public List<Category> findAll() {
        return springDataCategoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
       if(springDataCategoryRepository.findById(id).isPresent()){
           return true;
        }
       else{
           return false;
       }
    }

    @Override
    public void delete(Category category) {
        springDataCategoryRepository.delete(category);
    }
}