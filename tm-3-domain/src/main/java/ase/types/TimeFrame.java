package ase.types;
import java.util.Date;
import java.time.LocalTime;
public class TimeFrame {
    private Long id;
    private LocalTime start;
    private LocalTime end;

    public TimeFrame(LocalTime start, LocalTime end) {
        if(start.compareTo(end)<0) {
            this.start = start;
            this.end = end;
        }
        else {
            throw new IllegalArgumentException("End time should come after start time");
        }
    }


    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

}
