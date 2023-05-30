package ase.estimation;


import ase.types.Estimation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EstimationToEstimationDTOMapper implements Function<Estimation, EstimationDTO> {
    @Override
    public EstimationDTO apply(Estimation estimation) {return map(estimation);}
    private EstimationDTO map(Estimation estimation) {
        return new EstimationDTO(estimation.getId(), estimation.getEstimation(), estimation.getUnit());
    }
}
