package ase.session;

import ase.activity.ActivityDTO;
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
        "startTime",
        "endTime",
        "activity",
})
@Generated("jsonschema2pojo")
public class SessionDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("startTime")
    private LocalDateTime startTime;
    @JsonProperty("endTime")
    private LocalDateTime endTime;
    @JsonProperty("activity")
    private ActivityDTO activityDTO;

    public SessionDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, ActivityDTO activityDTO){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityDTO = activityDTO;
    }
    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("name")
    public LocalDateTime getStartTime() {
        return startTime;
    }
    @JsonProperty("dueDate")
    public LocalDateTime getEndTime() {
        return endTime;
    }
    @JsonProperty("activity")
    public ActivityDTO getActivityDTO(){return activityDTO;}
}
