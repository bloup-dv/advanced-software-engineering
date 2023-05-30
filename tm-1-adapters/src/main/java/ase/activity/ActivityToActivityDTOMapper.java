package ase.activity;

import ase.category.CategoryToCategoryDTOMapper;
import ase.estimation.EstimationToEstimationDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActivityToActivityDTOMapper implements Function<Activity, ActivityDTO> {
    private EstimationToEstimationDTOMapper estimationToEstimationDTOMapper;
    private CategoryToCategoryDTOMapper categoryToCategoryDTOMapper;
    @Autowired
    public ActivityToActivityDTOMapper(EstimationToEstimationDTOMapper estimationToEstimationDTOMapper, CategoryToCategoryDTOMapper categoryToCategoryDTOMapper){
        this.estimationToEstimationDTOMapper = estimationToEstimationDTOMapper;
        this.categoryToCategoryDTOMapper = categoryToCategoryDTOMapper;
    }
    @Override
    public ActivityDTO apply(Activity activity) {return map(activity);}
    private ActivityDTO map(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDueDate(),
                estimationToEstimationDTOMapper.apply(activity.getEstimation()),
                categoryToCategoryDTOMapper.apply(activity.getCategory()));
    }
}
