package ase.persistance.hibernate.activity;


import ase.activity.Activity;
import ase.activity.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityRepositoryBridge implements ActivityRepository {
    private SpringDataActivityRepository springDataActivityRepository;

    @Autowired
    public ActivityRepositoryBridge(SpringDataActivityRepository springDataActivityRepository) {
        this.springDataActivityRepository = springDataActivityRepository;
    }

    @Override
    public void save(Activity activity) {
        this.springDataActivityRepository.save(activity);
    }

    @Override
    public Activity getByName(String name) {
        return springDataActivityRepository.getByName(name);
    }

    @Override
    public List<Activity> findAll() {
        return springDataActivityRepository.findAll();
    }
    @Override
    public boolean existsById(Long id) {
       if(springDataActivityRepository.findById(id).isPresent()){
           return true;
        }
       else{
           return false;
       }
    }

    @Override
    public void delete(Activity activity) {
        springDataActivityRepository.delete(activity);
    }
}