package ase.timeframe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "start",
        "end"
})
@Generated("jsonschema2pojo")
public class TimeFrameDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("start")
    private LocalTime start;
    @JsonProperty("end")
    private LocalTime end;

    public TimeFrameDTO(Long id, LocalTime start, LocalTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
    @JsonProperty("id")
    public Long getId() {
        return id;
    }
    @JsonProperty("start")
    public LocalTime getStart() {
        return start;
    }
    @JsonProperty("end")
    public LocalTime getEnd() {
        return end;
    }
}

