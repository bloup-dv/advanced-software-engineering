package ase.category;

import ase.timeframe.TimeFrameDTO;
import ase.types.Category;
import ase.types.Estimation;
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
        "timeFrame_id"
})
@Generated("jsonschema2pojo")
public class CategoryDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("timeFrame")
    private TimeFrameDTO timeFrameDTO;

    public CategoryDTO(Long id, String name, TimeFrameDTO timeFrameDTO){
        this.id = id;
        this.name = name;
        this.timeFrameDTO = timeFrameDTO;
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
    public TimeFrameDTO getTimeFrameDTO() {return timeFrameDTO;}
}
