package ase.rest.activity;

import ase.activity.CreateActivitiesService;
import ase.activity.ActivityDTO;
import ase.activity.ActivityDTOToActivityMapper;
import ase.activity.ActivityToActivityDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityDTOToActivityMapper activityDTOToActivityMapper;
    private final ActivityToActivityDTOMapper activityToActivityDTOMapper;
    private final CreateActivitiesService createActivitiesService;
    @Autowired
    public ActivityController(ActivityDTOToActivityMapper activityDTOToActivityMapper,
                              ActivityToActivityDTOMapper activityToActivityDTOMapper,
                              CreateActivitiesService createActivitiesService) {
        this.activityDTOToActivityMapper = activityDTOToActivityMapper;
        this.activityToActivityDTOMapper = activityToActivityDTOMapper;
        this.createActivitiesService = createActivitiesService;
    }
    @PostMapping(value = "/create_activity")
    public void createActivity(@RequestBody ActivityDTO activityDTO) throws IllegalArgumentException
    {
        this.createActivitiesService.createActivity(this.activityDTOToActivityMapper.apply(activityDTO));
    }
}
