package ase.activity;

import ase.category.CategoryDTO;
import ase.estimation.EstimationDTO;
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
        "name",
        "due_date",
        "estimation",
        "category"
})
@Generated("jsonschema2pojo")
public class ActivityDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("estimation")
    private EstimationDTO estimationDTO;
    @JsonProperty("category")
    private CategoryDTO categoryDTO;

    public ActivityDTO(Long id, String name, LocalDateTime dueDate, EstimationDTO estimationDTO, CategoryDTO categoryDTO){
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.estimationDTO = estimationDTO;
        this.categoryDTO = categoryDTO;
    }
    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("dueDate")
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    @JsonProperty("estimation")
    public EstimationDTO getEstimationDTO() {
        return estimationDTO;
    }
    @JsonProperty("category")
    public CategoryDTO getCategoryDTO(){
        return categoryDTO;
    }
}
