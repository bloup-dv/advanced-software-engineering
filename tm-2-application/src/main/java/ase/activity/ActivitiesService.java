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
        isActivityValid(activity);
        checkActivityDates(activity);
        checkActivityName(activity);
        activityRepository.save(new Activity(activity.getName(), activity.getDueDate(), activity.getEstimation(),
                activity.getCategory()));
    }

    public void editActivity(Long oldActivityId, Activity newActivity) throws IllegalArgumentException {
        checkIfActivityExists(oldActivityId);
        isActivityValid(newActivity);
        isCategoryValid(newActivity.getCategory());
        checkActivityDates(newActivity);
        checkActivityName(newActivity);
        activityRepository.save(new Activity(oldActivityId, newActivity.getName(), newActivity.getDueDate(),
                newActivity.getEstimation(), newActivity.getCategory(), newActivity.getStatus()));
    }

    public List<Activity> listByCategory(Category category) throws IllegalArgumentException {
        isCategoryValid(category);
        return this.activityRepository.findAllByCategory(category);

    }

    private void isCategoryValid(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is invalid.");
        }
        if (!this.categoryRepository.existsById(category.getId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
    }

    public List<Activity> listByStatus(Status status) throws IllegalArgumentException {
        if (status == null) {
            throw new IllegalArgumentException("Status is invalid.");
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

    private void isActivityValid(Activity activity) {
        if (activity == null || activity.getCategory() == null || activity.getEstimation() == null) {
            throw new IllegalArgumentException("the activity is incomplete");
        }
        if (activity.getDueDate() == null) {
            throw new IllegalArgumentException("please assign a valid due date to the activity");
        }
    }

    private void checkActivityName(Activity activity) {
        if (activity.getName() == null) {
            throw new IllegalArgumentException("the Activity must have a name");
        }
    }

    private void checkActivityDates(Activity activity) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(activity.getDueDate())) {
            throw new IllegalArgumentException("the due date must be in the future");
        }
        if (currentTime.plus(activity.getEstimation().getEstimation(), ChronoUnit.MINUTES)
                .isAfter(activity.getDueDate())) {
            throw new IllegalArgumentException(
                    "the Estimated duration may not take longer than the total time until the due date");
        }
    }

    private void checkIfActivityExists(Long oldActivityId) {
        if (!this.activityRepository.existsById(oldActivityId)) {
            throw new IllegalArgumentException("the activity does not exist");
        }
    }
}
