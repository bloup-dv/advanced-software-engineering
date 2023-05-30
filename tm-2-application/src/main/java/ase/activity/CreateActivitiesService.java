package ase.activity;

import ase.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreateActivitiesService {
    private final ActivityRepository activityRepository;
    @Autowired
    public CreateActivitiesService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void createActivity(Activity activity) throws IllegalArgumentException {
        if(false){
            //TODO
        }
        activityRepository.save(new Activity(activity.getName(), activity.getDueDate(), activity.getEstimation(), activity.getCategory()));
    }

    //TODO start session





}
