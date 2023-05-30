package ase.activity;

import ase.category.Category;
import ase.category.CategoryRepository;
import ase.types.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ListActivitiesService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ListActivitiesService(ActivityRepository activityRepository,
                                 CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<Activity> listByCategory(Category category) throws IllegalArgumentException{
        if (category == null){
            throw new IllegalArgumentException("Category is invalid.");
        }
        if (!this.categoryRepository.existsById(category.getId())){
            throw new IllegalArgumentException("Category does not exist");
        }
        return this.activityRepository.findAllByCategory(category);

    }
    public List<Activity> listByStatus(Status status) throws IllegalArgumentException{
        if (status == null){
            throw new IllegalArgumentException("Category is invalid.");
        }
        List<Activity> activityList = this.activityRepository.findAllByStatus(status);
        Collections.sort(activityList, Comparator.comparing(Activity::getDueDate));
        return activityList;
    }

}
