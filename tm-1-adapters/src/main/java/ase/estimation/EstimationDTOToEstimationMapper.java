package ase.estimation;

import ase.activity.Activity;
import ase.types.Estimation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EstimationDTOToEstimationMapper implements Function<EstimationDTO, Estimation> {
    @Override
    public Estimation apply(EstimationDTO estimationDTO) { return map(estimationDTO);}
    private Estimation map(EstimationDTO estimationDTO){
        if(estimationDTO != null) {
            return new Estimation(estimationDTO.getId(), estimationDTO.getEstimation(), estimationDTO.getUnit());
        }
        else{
            return null;
        }
    }
}
