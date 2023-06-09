package ase.rest.activity;

import ase.activity.*;
import ase.category.CategoryDTO;
import ase.category.CategoryDTOToCategoryMapper;
import ase.types.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityDTOToActivityMapper activityDTOToActivityMapper;
    private final ActivityToActivityDTOMapper activityToActivityDTOMapper;
    private final CategoryDTOToCategoryMapper categoryDTOToCategoryMapper;
    private final ActivitiesService activitiesService;
    @Autowired
    public ActivityController(ActivityDTOToActivityMapper activityDTOToActivityMapper,
                              ActivityToActivityDTOMapper activityToActivityDTOMapper,
                              CategoryDTOToCategoryMapper categoryDTOToCategoryMapper,
                              ActivitiesService activitiesService) {
        this.activityDTOToActivityMapper = activityDTOToActivityMapper;
        this.activityToActivityDTOMapper = activityToActivityDTOMapper;
        this.categoryDTOToCategoryMapper = categoryDTOToCategoryMapper;
        this.activitiesService = activitiesService;
    }

    @PostMapping(value = "/create_activity")
    public void createActivity(@RequestBody ActivityDTO activityDTO) throws IllegalArgumentException
    {
        this.activitiesService.createActivity(this.activityDTOToActivityMapper.apply(activityDTO));
    }
    @PutMapping(value = "/update", params = {"activityId"})
    public void updateActivity(@RequestBody ActivityDTO activityDTO, @RequestParam Long activityId) throws  IllegalArgumentException{
        this.activitiesService.editActivity(activityId , this.activityDTOToActivityMapper.apply(activityDTO));
    }

    @GetMapping(value = "/viewByCategory", params = {"category"})
    public List<Activity> filterByCategory(@RequestParam CategoryDTO categoryDTO) throws IllegalArgumentException{
        return this.activitiesService.listByCategory(this.categoryDTOToCategoryMapper.apply(categoryDTO));
    }

    @GetMapping(value = "/viewByStatus", params = {"status"})
    public List<Activity> filterByStatus(@RequestParam Status status) throws IllegalArgumentException {
        return this.activitiesService.listByStatus(status);
    }
    @DeleteMapping(value = "/delete")
    public void deleteActivity(@RequestParam ActivityDTO activityDTO) throws IllegalArgumentException {
        this.activitiesService.deleteActivity(this.activityDTOToActivityMapper.apply(activityDTO));
    }
}
