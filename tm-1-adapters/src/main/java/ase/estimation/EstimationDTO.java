package ase.estimation;

import ase.types.Category;
import ase.types.Estimation;
import ase.types.Unit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "estimation",
        "unit"
})
@Generated("jsonschema2pojo")
public class EstimationDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("estimation")
    private Long estimation;
    @JsonProperty("unit")
    private Unit unit;


    public EstimationDTO(Long id, Long estimation, Unit unit){
        this.id = id;
        this.estimation = estimation;
        this.unit = unit;
    }
    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("estimation")
    public Long getEstimation() {return estimation;}
    @JsonProperty("unit")
    public Unit getUnit() {return unit;}
}
