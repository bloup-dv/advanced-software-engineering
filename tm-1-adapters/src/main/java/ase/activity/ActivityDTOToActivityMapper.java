package ase.activity;

import ase.category.CategoryDTOToCategoryMapper;
import ase.estimation.EstimationDTOToEstimationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActivityDTOToActivityMapper implements Function<ActivityDTO, Activity> {
    private EstimationDTOToEstimationMapper estimationDTOToEstimationMapper;
    private CategoryDTOToCategoryMapper categoryDTOToCategoryMapper;
    @Autowired
    public ActivityDTOToActivityMapper(EstimationDTOToEstimationMapper estimationDTOToEstimationMapper, CategoryDTOToCategoryMapper categoryDTOToCategoryMapper){
        this.estimationDTOToEstimationMapper = estimationDTOToEstimationMapper;
        this.categoryDTOToCategoryMapper = categoryDTOToCategoryMapper;
    }
    @Override
    public Activity apply(ActivityDTO activityDTO) { return map(activityDTO);}
    private Activity map(ActivityDTO activityDTO){
        if(activityDTO != null) {
            return new Activity(
                    activityDTO.getId(),
                    activityDTO.getName(),
                    activityDTO.getDueDate(),
                    estimationDTOToEstimationMapper.apply(activityDTO.getEstimationDTO()),
                    categoryDTOToCategoryMapper.apply(activityDTO.getCategoryDTO()));
        }
        else{
            return null;
        }
    }
}
