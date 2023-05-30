package ase.timeframe;

import ase.activity.Activity;
import ase.types.TimeFrame;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TimeFrameToTimeFrameDTOMapper implements Function<TimeFrame, TimeFrameDTO> {
    @Override
    public TimeFrameDTO apply(TimeFrame timeFrame) {return map(timeFrame);}
    private TimeFrameDTO map(TimeFrame timeFrame) {
        return new TimeFrameDTO(timeFrame.getId(), timeFrame.getStart(), timeFrame.getEnd());
    }
}
