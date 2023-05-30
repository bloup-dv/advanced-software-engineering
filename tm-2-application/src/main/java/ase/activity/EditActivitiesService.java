package ase.activity;

import ase.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class EditActivitiesService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public EditActivitiesService(ActivityRepository activityRepository,
                                 CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
    }


    public void editActivity(Long oldActivityId, Activity newActivity) throws IllegalArgumentException {
        if(!this.activityRepository.existsById(oldActivityId)){
            throw new IllegalArgumentException("this Activity does not exist");
        }
        if(newActivity == null || newActivity.getCategory()==null || newActivity.getEstimation()==null){
            throw new IllegalArgumentException("the Action is incomplete");
        }
        if(!this.categoryRepository.existsById(newActivity.getCategory().getId())){
            throw new IllegalArgumentException("chosen Category does not exist");
        }
        if(newActivity.getDueDate() == null){ throw new IllegalArgumentException("please assign a valid due date to the activity");}
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentTime.isAfter(newActivity.getDueDate())){
            throw new IllegalArgumentException("the due date must be in the future");
        }
        if(currentTime.plus(newActivity.getEstimation().getEstimation(), ChronoUnit.MINUTES).isAfter(newActivity.getDueDate())){
            throw new IllegalArgumentException("the Estimated duration may not take longer than the total time until the due date");
        }
        if(newActivity.getName() == null){ throw new IllegalArgumentException("the Activity must have a name");}
        activityRepository.save(new Activity(oldActivityId, newActivity.getName(), newActivity.getDueDate(), newActivity.getEstimation(), newActivity.getCategory(),newActivity.getStatus()));
    }











}
