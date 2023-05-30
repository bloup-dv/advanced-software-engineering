package ase.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteActivitiesService {
    private final ActivityRepository activityRepository;
    @Autowired
    public DeleteActivitiesService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void deleteActivity(Activity activity) throws IllegalArgumentException {
        if(!this.activityRepository.existsById(activity.getId())){
            throw new IllegalArgumentException("the Activity does not exist");
        }
        this.activityRepository.delete(activity);
    }











}
