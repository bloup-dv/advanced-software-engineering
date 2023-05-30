package ase.timeframe;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TimeFrameDTOToTimeFrameMapper implements Function<TimeFrameDTO, TimeFrame> {
    @Override
    public TimeFrame apply(TimeFrameDTO timeFrameDTO) { return map(timeFrameDTO);}
    private TimeFrame map(TimeFrameDTO timeFrameDTO){
        if(timeFrameDTO != null) {
            return new TimeFrame(timeFrameDTO.getId(), timeFrameDTO.getStart(), timeFrameDTO.getEnd());
        }
        else{
            return null;
        }
    }
}
