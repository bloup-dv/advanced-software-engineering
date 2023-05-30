package ase.activity;

import ase.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
@Service
public class CreateActivitiesService {
    private final ActivityRepository activityRepository;
    @Autowired
    public CreateActivitiesService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void createActivity(Activity activity) throws IllegalArgumentException {
        if(activity == null || activity.getCategory()==null || activity.getEstimation()==null){
            throw new IllegalArgumentException("the Action is incomplete");
        }
        if(activity.getDueDate() == null){ throw new IllegalArgumentException("please assign a valid due date to the activity");}
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentTime.isAfter(activity.getDueDate())){
            throw new IllegalArgumentException("the due date must be in the future");
        }
        if(currentTime.plus(activity.getEstimation().getEstimation(), ChronoUnit.MINUTES).isAfter(activity.getDueDate())){
            throw new IllegalArgumentException("the Estimated duration may not take longer than the total time until the due date");
        }
        if(activity.getName() == null){ throw new IllegalArgumentException("the Activity must have a name");}
        activityRepository.save(new Activity(activity.getName(), activity.getDueDate(), activity.getEstimation(), activity.getCategory()));
    }






}
