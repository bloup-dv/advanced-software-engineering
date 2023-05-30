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
public class ActivitiesService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ActivitiesService(ActivityRepository activityRepository,
                             CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createActivity(Activity activity) throws IllegalArgumentException {
        if (activity == null || activity.getCategory() == null || activity.getEstimation() == null) {
            throw new IllegalArgumentException("the Action is incomplete");
        }
        if (activity.getDueDate() == null) {
            throw new IllegalArgumentException("please assign a valid due date to the activity");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(activity.getDueDate())) {
            throw new IllegalArgumentException("the due date must be in the future");
        }
        if (currentTime.plus(activity.getEstimation().getEstimation(), ChronoUnit.MINUTES)
                .isAfter(activity.getDueDate())) {
            throw new IllegalArgumentException(
                    "the Estimated duration may not take longer than the total time until the due date");
        }
        if (activity.getName() == null) {
            throw new IllegalArgumentException("the Activity must have a name");
        }
        activityRepository.save(new Activity(activity.getName(), activity.getDueDate(), activity.getEstimation(),
                activity.getCategory()));
    }

    public void editActivity(Long oldActivityId, Activity newActivity) throws IllegalArgumentException {
        if (!this.activityRepository.existsById(oldActivityId)) {
            throw new IllegalArgumentException("this Activity does not exist");
        }
        if (newActivity == null || newActivity.getCategory() == null || newActivity.getEstimation() == null) {
            throw new IllegalArgumentException("the Action is incomplete");
        }
        if (!this.categoryRepository.existsById(newActivity.getCategory().getId())) {
            throw new IllegalArgumentException("chosen Category does not exist");
        }
        if (newActivity.getDueDate() == null) {
            throw new IllegalArgumentException("please assign a valid due date to the activity");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(newActivity.getDueDate())) {
            throw new IllegalArgumentException("the due date must be in the future");
        }
        if (currentTime.plus(newActivity.getEstimation().getEstimation(), ChronoUnit.MINUTES)
                .isAfter(newActivity.getDueDate())) {
            throw new IllegalArgumentException(
                    "the Estimated duration may not take longer than the total time until the due date");
        }
        if (newActivity.getName() == null) {
            throw new IllegalArgumentException("the Activity must have a name");
        }
        activityRepository.save(new Activity(oldActivityId, newActivity.getName(), newActivity.getDueDate(),
                newActivity.getEstimation(), newActivity.getCategory(), newActivity.getStatus()));
    }

    public List<Activity> listByCategory(Category category) throws IllegalArgumentException {
        if (category == null) {
            throw new IllegalArgumentException("Category is invalid.");
        }
        if (!this.categoryRepository.existsById(category.getId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        return this.activityRepository.findAllByCategory(category);

    }

    public List<Activity> listByStatus(Status status) throws IllegalArgumentException {
        if (status == null) {
            throw new IllegalArgumentException("Category is invalid.");
        }
        List<Activity> activityList = this.activityRepository.findAllByStatus(status);
        Collections.sort(activityList, Comparator.comparing(Activity::getDueDate));
        return activityList;
    }

    public void deleteActivity(Activity activity) throws IllegalArgumentException {
        if (!this.activityRepository.existsById(activity.getId())) {
            throw new IllegalArgumentException("the Activity does not exist");
        }
        this.activityRepository.delete(activity);
    }
}
